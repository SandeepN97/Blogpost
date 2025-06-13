package com.sandeep.blog.blogapplication.service;

import com.sandeep.blog.blogapplication.model.User;
import com.sandeep.blog.blogapplication.repository.UserRepository;
import com.sandeep.blog.blogapplication.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PostRepository postRepository;

    public ResponseEntity<String> addUser(User user) {
        try {
            if (null != userRepository.findByUsername(user.getUsername())) {
                return new ResponseEntity<>("Username already exists.", HttpStatus.CONFLICT);
            }
            if (null != userRepository.findByEmail(user.getEmail())) {
                return new ResponseEntity<>("Email already exists.", HttpStatus.CONFLICT);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return new ResponseEntity<>("User has been added.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<User>> getAllUsers() {
        try{
            List<User> users = userRepository.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> getUserById(long id) {
        try{
            Optional<User> usersOptional = userRepository.findById(id);
            if(usersOptional.isPresent()){
                User userToBeAdded = usersOptional.get();
                return new ResponseEntity<>(userToBeAdded, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> updateUser(User newUser) {
        try{
            Optional<User> userOptional = userRepository.findById(newUser.getId());
            if(userOptional.isPresent()){
                User oldUser = userOptional.get();
                oldUser.setUsername(newUser.getUsername());
                oldUser.setEmail(newUser.getEmail());

                // Encode the password if it has been changed
                if (!newUser.getPassword().equals(oldUser.getPassword())) {
                    oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
                } else {
                    oldUser.setPassword(newUser.getPassword());
                }

                User updatedUser = (User) userRepository.save(oldUser);

                return new ResponseEntity<>(updatedUser,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    public ResponseEntity<String> deleteUser(long id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if(userOptional.isPresent()){
                User userToBeDelete = userOptional.get();
                userRepository.delete(userToBeDelete);
                return new ResponseEntity<>("user deleted.", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("user not found.", HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>("Failed to delete user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> bookmarkPost(long userId, long postId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            Optional<com.sandeep.blog.blogapplication.model.Post> postOptional = postRepository.findById(postId);
            if (userOptional.isPresent() && postOptional.isPresent()) {
                User user = userOptional.get();
                user.getBookmarks().add(postOptional.get());
                userRepository.save(user);
                return new ResponseEntity<>("Post bookmarked", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User or post not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to bookmark post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> removeBookmark(long userId, long postId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            Optional<com.sandeep.blog.blogapplication.model.Post> postOptional = postRepository.findById(postId);
            if (userOptional.isPresent() && postOptional.isPresent()) {
                User user = userOptional.get();
                user.getBookmarks().remove(postOptional.get());
                userRepository.save(user);
                return new ResponseEntity<>("Bookmark removed", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User or post not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to remove bookmark", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<java.util.Set<com.sandeep.blog.blogapplication.model.Post>> getBookmarkedPosts(long userId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return new ResponseEntity<>(user.getBookmarks(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
