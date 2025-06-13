package com.sandeep.blog.blogapplication.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import com.sandeep.blog.blogapplication.jwt.AuthTokenFilter;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringConfig {

    @Autowired
    DataSource dataSource;

    @Autowired
    private AuthTokenFilter authTokenFilter;


    // This is the configuration for the security of the application
    // It is used to define the roles and permissions for the users
    // The defaultSecurityFilterChain method is used to define the security filter chain
    // The userDetailsService method is used to define the user details service

    //@Bean - This annotation is used to define a bean in the application context
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // The http.authorizeRequests method is used to define the authorization rules for the application
        // The http.sessionManagement method is used to define the session management policy for the application
        // The http.httpBasic method is used to define the basic authentication for the application
        // The http.build method is used to build the security filter chain
        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/auth/login", "/user/add").permitAll()
                                .requestMatchers("/user/allusers", "/user/*").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/user/update", "/user/removeuser/*").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // This method is used to define the user details service
    // The InMemoryUserDetailsManager class is used to define the user details in memory
    // The UserDetails class is used to define the user details
    // The org.springframework.security.core.userdetails.User class is used to define the user details
    // The org.springframework.security.core.userdetails.User.withUsername method is used to define the username
    // The org.springframework.security.core.userdetails.User.password method is used to define the password
    // The org.springframework.security.core.userdetails.User.roles method is used to define the roles
    // The org.springframework.security.core.userdetails.User.build method is used to build the user details
    // The InMemoryUserDetailsManager class is used to define the user details manager

    //@Bean - This annotation is used to define a bean in the application context
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user1 = org.springframework.security.core.userdetails.User.withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

        UserDetails admin1 = org.springframework.security.core.userdetails.User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        if (!userDetailsManager.userExists("user")) {
            userDetailsManager.createUser(user1);
        }

        if (!userDetailsManager.userExists("admin")) {
            userDetailsManager.createUser(admin1);
        }
        return userDetailsManager;
        //return new InMemoryUserDetailsManager(user1, admin1);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
