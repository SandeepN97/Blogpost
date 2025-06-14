package com.sandeep.blog.blogapplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns =  @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany(mappedBy = "bookmarks")
    private Set<User> bookmarkedBy = new HashSet<>();

    @Column(nullable = false)
    private int likes = 0;

    //using PrePersist and PreUpdate to automatically set timestamps
    @PrePersist
    protected void onCreate(){
        createDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        updateDate = LocalDateTime.now();
    }

}
