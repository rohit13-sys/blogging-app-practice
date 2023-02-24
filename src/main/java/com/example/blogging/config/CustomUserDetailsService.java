package com.example.blogging.config;

import com.example.blogging.entity.Users;
import com.example.blogging.repository.UserReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserReposiory userReposiory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=userReposiory.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        return user;
    }
}
