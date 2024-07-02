package com.sandeep.blog.blogapplication.service;

import com.sandeep.blog.blogapplication.model.Post;
import com.sandeep.blog.blogapplication.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
