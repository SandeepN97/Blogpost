package com.sandeep.blog.blogapplication.controller;

import com.sandeep.blog.blogapplication.model.Comment;
import com.sandeep.blog.blogapplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/getAllComment")
    public ResponseEntity<List<Comment>> getAllComment(){
        return commentService.getAllComment();
    }

    @GetMapping("/getComment/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable long id){
        return commentService.getCommentById(id);
    }

    @PostMapping("/addComment")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @PutMapping("/updateComment/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable long id, @RequestBody Comment comment){
        return commentService.updateComment(id, comment);
    }
    @PutMapping("/updateCommentByPost/{postId}")
    public ResponseEntity<Comment> updateCommentByPost(@PathVariable long postId, @RequestBody Comment comment){
        return commentService.updateCommentByPost(postId, comment);
    }

    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable long id){
        return commentService.deleteComment(id);
    }

}
