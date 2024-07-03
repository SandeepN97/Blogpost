package com.sandeep.blog.blogapplication.controller;

import com.sandeep.blog.blogapplication.model.Post;
import com.sandeep.blog.blogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> Stashed changes

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/getAllPost")
    public ResponseEntity<List<Post>> getAllPost(){
        return postService.getAllPost();
    }

<<<<<<< Updated upstream

=======
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
>>>>>>> Stashed changes
}
