package com.sandeep.blog.blogapplication.service;

import com.sandeep.blog.blogapplication.model.Post;
import com.sandeep.blog.blogapplication.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    public ResponseEntity<List<Post>> getAllPost() {
        try {
            List<Post> post= postRepository.findAll();
            return new ResponseEntity<>(post,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Post> getPostById(long id) {
        try {
            Optional<Post> optionalPost = postRepository.findById(id);
            if(optionalPost.isPresent()){
                Post postToBeFound = optionalPost.get();
                return  new ResponseEntity<>(postToBeFound, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Post> getPostByTitle(String title) {
        try {
            Optional<Post> optionalPost = postRepository.findByTitle(title);
            if(optionalPost.isPresent()){
                Post postToBeFound = optionalPost.get();
                return  new ResponseEntity<>(postToBeFound, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Post>> getPostByUser(long userId) {
        try {
            List<Post> post= postRepository.findByUserId(userId);
            return new ResponseEntity<>(post,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Post>> getTopPosts(int count) {
        try {
            Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "likes"));
            List<Post> posts = postRepository.findAll(pageable).getContent();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving top posts", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Post> addPost(Post post) {
        try {
            Post postToBeAdded = postRepository.save(post);
            return new ResponseEntity<>(postToBeAdded,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Post> updatePost(long id, Post post) {
        try {
            Optional<Post> optionalPost = postRepository.findById(id);
            if(optionalPost.isPresent()){
                Post postToBeUpdated = optionalPost.get();
                postToBeUpdated.setTitle(post.getTitle());
                postToBeUpdated.setContent(post.getContent());
                postToBeUpdated.setTags(post.getTags());
                postToBeUpdated.setUser(post.getUser());
                postToBeUpdated.setComments(post.getComments());
                postToBeUpdated = postRepository.save(postToBeUpdated);
                return new ResponseEntity<>(postToBeUpdated, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Post> deletePost(long id) {
        try {
            Optional<Post> optionalPost = postRepository.findById(id);
            if(optionalPost.isPresent()){
                Post postToBeDeleted = optionalPost.get();
                postRepository.delete(postToBeDeleted);
                return new ResponseEntity<>(postToBeDeleted, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Post> likePost(long id) {
        try {
            Optional<Post> optionalPost = postRepository.findById(id);
            if(optionalPost.isPresent()){
                Post post = optionalPost.get();
                post.setLikes(post.getLikes() + 1);
                postRepository.save(post);
                return new ResponseEntity<>(post, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Post> unlikePost(long id) {
        try {
            Optional<Post> optionalPost = postRepository.findById(id);
            if(optionalPost.isPresent()){
                Post post = optionalPost.get();
                if(post.getLikes() > 0){
                    post.setLikes(post.getLikes() - 1);
                    postRepository.save(post);
                }
                return new ResponseEntity<>(post, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
