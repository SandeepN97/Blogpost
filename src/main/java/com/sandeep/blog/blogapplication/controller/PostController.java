package com.sandeep.blog.blogapplication.controller;

import com.sandeep.blog.blogapplication.model.Post;
import com.sandeep.blog.blogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
