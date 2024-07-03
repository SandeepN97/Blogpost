package com.sandeep.blog.blogapplication.service;

import com.sandeep.blog.blogapplication.BlogapplicationApplication;
import com.sandeep.blog.blogapplication.model.Tag;
import com.sandeep.blog.blogapplication.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;
    @Autowired
    private BlogapplicationApplication blogapplicationApplication;

    public ResponseEntity<Tag> addTag(Tag tag) {
        try {
            Tag tagsToBeAdded = tagRepository.save(tag);
            return new ResponseEntity<>(tagsToBeAdded, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Tag>> getAllTag() {
        try {
            List<Tag> tags = tagRepository.findAll();
            return new ResponseEntity<>(tags, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Tag> getTagById(long id) {
        try {
            Tag tag = tagRepository.findById(id).get();
            return new ResponseEntity<>(tag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Tag> deleteTag(long id) {
        try {
            tagRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Tag> updateTag(long id, Tag tag) {
        try{
            Optional<Tag> optionalTag = tagRepository.findById(id);
            if(optionalTag.isPresent()){
                Tag tagToBeUpdated = optionalTag.get();
                tagToBeUpdated.setName(tag.getName());
                tagRepository.save(tagToBeUpdated);
                return new ResponseEntity<>(tagToBeUpdated, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }catch (Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    public ResponseEntity<List<Tag>> getTagByPost(long postId) {
        try {
            List<Tag> tags = tagRepository.findByPostsId(postId);
            return new ResponseEntity<>(tags, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
