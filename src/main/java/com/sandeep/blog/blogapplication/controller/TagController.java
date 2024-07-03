package com.sandeep.blog.blogapplication.controller;

import com.sandeep.blog.blogapplication.model.Tag;
import com.sandeep.blog.blogapplication.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping("/addTag")
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag ) {
        return tagService.addTag(tag);
    }

    @RequestMapping("/getAllTag")
    public ResponseEntity<List<Tag>> getAllTag(){
        return tagService.getAllTag();
    }

    @RequestMapping("/getTag/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable long id){
        return tagService.getTagById(id);
    }

    @RequestMapping("/deleteTag/{id}")
    public ResponseEntity<Tag> deleteTag(@PathVariable long id){
        return tagService.deleteTag(id);
    }

    @RequestMapping("/updateTag/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable long id, @RequestBody Tag tag){
        return tagService.updateTag(id, tag);
    }
    @RequestMapping("/getTagByPost/{postId}")
    public ResponseEntity<List<Tag>> getTagByPost(@PathVariable long postId){
        return tagService.getTagByPost(postId);
    }

}
