package com.sandeep.blog.blogapplication.service;

import com.sandeep.blog.blogapplication.model.Comment;
import com.sandeep.blog.blogapplication.repository.CommentRepository;
import com.sandeep.blog.blogapplication.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    public ResponseEntity<List<Comment>> getAllComment() {
        try {
            List<Comment> comment = commentRepository.findAll();
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Comment> getCommentById(long id) {
        try {
            Optional<Comment> optionalComment = commentRepository.findById(id);
            if (optionalComment.isPresent()) {
                Comment commentToBeFound = optionalComment.get();
                return new ResponseEntity<>(commentToBeFound, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Comment> addComment(Comment comment) {
        try {
            Comment commentToBeAdded = commentRepository.save(comment);
            return new ResponseEntity<>(commentToBeAdded, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Comment> updateComment(long id, Comment comment) {
        try {
            Optional<Comment> optionalComment = commentRepository.findById(id);
            if (optionalComment.isPresent()) {
                Comment commentToBeUpdated = optionalComment.get();
                commentToBeUpdated.setContent(comment.getContent());
                commentToBeUpdated.setPost(comment.getPost());
                commentToBeUpdated.setUser(comment.getUser());
                Comment updatedComment = commentRepository.save(commentToBeUpdated);
                return new ResponseEntity<>(updatedComment, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Comment> updateCommentByPost(long postId, Comment comment) {
        try {
            List<Comment> comments = commentRepository.findByPostId(postId);
            for (Comment commentToBeUpdated : comments) {
                commentToBeUpdated.setContent(comment.getContent());
                commentToBeUpdated.setPost(comment.getPost());
                commentToBeUpdated.setUser(comment.getUser());
                Comment updatedComment = commentRepository.save(commentToBeUpdated);
                return new ResponseEntity<>(updatedComment, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Comment> deleteComment(long id) {
        try {
            Optional<Comment> optionalComment = commentRepository.findById(id);
            if (optionalComment.isPresent()) {
                Comment commentToBeDeleted = optionalComment.get();
                commentRepository.delete(commentToBeDeleted);
                return new ResponseEntity<>(commentToBeDeleted, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Comment>> getCommentsByPost(long postId) {
        try {
            List<Comment> comments = commentRepository.findByPostId(postId);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Comment> addCommentToPost(long postId, Comment comment) {
        try {
            Optional<com.sandeep.blog.blogapplication.model.Post> postOpt = postRepository.findById(postId);
            if (postOpt.isPresent()) {
                comment.setPost(postOpt.get());
                Comment saved = commentRepository.save(comment);
                return new ResponseEntity<>(saved, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
