package com.sandeep.blog.blogapplication.service;

import com.sandeep.blog.blogapplication.model.User;
import com.sandeep.blog.blogapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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
                oldUser.setPassword(newUser.getPassword());
                User updatedUser = (User) userRepository.save(oldUser);
                return new ResponseEntity<>(updatedUser,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addUser(User user) {
        try{
            userRepository.save(user);
            return new ResponseEntity<>("User has been added.",HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to add question.",HttpStatus.NOT_FOUND);
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
}
