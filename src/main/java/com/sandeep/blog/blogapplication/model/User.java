package com.sandeep.blog.blogapplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false,unique = true)
    private String username;

    @Column(length = 500,nullable = false)
    private String password;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean enabled;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"))
    @Column(name = "authority")
    private Set<String> authorities = new HashSet<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_bookmarks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private Set<Post> bookmarks = new HashSet<>();
}
