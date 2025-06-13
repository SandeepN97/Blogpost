package com.sandeep.blog.blogapplication.controller;

import com.sandeep.blog.blogapplication.model.Post;
import com.sandeep.blog.blogapplication.model.Comment;
import com.sandeep.blog.blogapplication.service.PostService;
import com.sandeep.blog.blogapplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @GetMapping("/getAllPost")
    public ResponseEntity<List<Post>> getAllPost(){
        return postService.getAllPost();
    }

    @GetMapping("/getPost/{id}")
    public  ResponseEntity<Post> getPostById (@PathVariable long id){
        return postService.getPostById(id);
    }

    @GetMapping("/getPostByTitle/{title}")
    public ResponseEntity<Post> getPostByTitle(@PathVariable String title){
        return postService.getPostByTitle(title);
    }

    @GetMapping("/getPostByUser/{userId}")
    public ResponseEntity<List<Post>> getPostByUser(@PathVariable long userId){
        return postService.getPostByUser(userId);
    }

    @GetMapping("/tag/{tagName}")
    public ResponseEntity<List<Post>> getPostsByTag(@PathVariable String tagName) {
        return postService.getPostsByTag(tagName);
    }

    @GetMapping("/top/{count}")
    public ResponseEntity<List<Post>> getTopPosts(@PathVariable int count) {
        return postService.getTopPosts(count);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam("q") String query) {
        return postService.searchPosts(query);
    }

    @PostMapping("/addPost")
    public ResponseEntity<Post> addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    @PutMapping("/updatePost/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable long id, @RequestBody Post post){
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable long id){
        return postService.deletePost(id);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Post> likePost(@PathVariable long id){
        return postService.likePost(id);
    }

    @DeleteMapping("/{id}/like")
    public ResponseEntity<Post> unlikePost(@PathVariable long id){
        return postService.unlikePost(id);
    }
  
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable long id) {
        return commentService.getCommentsByPost(id);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable long id, @RequestBody Comment comment) {
        return commentService.addCommentToPost(id, comment);
    }
}
