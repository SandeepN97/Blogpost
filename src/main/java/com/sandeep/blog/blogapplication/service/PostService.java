package com.sandeep.blog.blogapplication.service;

import com.sandeep.blog.blogapplication.model.Post;
import com.sandeep.blog.blogapplication.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

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

}
