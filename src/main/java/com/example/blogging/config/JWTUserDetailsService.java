package com.example.blogging.config;

import com.example.blogging.entity.Users;
import com.example.blogging.repository.UserReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private UserReposiory userReposiory;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user=userReposiory.findByEmail(userName).orElseThrow(()->new UsernameNotFoundException("UserName Not Found"));
            return new User(user.getEmail(),user.getPassword(),new ArrayList<>());

    }
}