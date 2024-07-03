package com.sandeep.blog.blogapplication.repository;

import com.sandeep.blog.blogapplication.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);
    List<Post> findByUserId(long userId);
}
