package com.example.blogging.controller;


import com.example.blogging.config.JWTUtil;
import com.example.blogging.payloads.JwtAuthRequest;
import com.example.blogging.payloads.JwtAuthResponse;
import com.example.blogging.payloads.UserDto;
import com.example.blogging.service.AuthService;
import com.example.blogging.service.UserService;
import org.apache.catalina.session.StandardSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {


    @Autowired
    private AuthService authService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtAuthResponse> generateToken(@RequestBody JwtAuthRequest request,HttpSession session) throws Exception {


        UserDetails userDetails= authService.isvalidUser(request);
        String userName= userDetails.getUsername();
        session.setAttribute("userName",userName);
        final String token = jwtUtil.generateToken(userDetails);
        JwtAuthResponse response=new JwtAuthResponse(token);
        System.out.println(session.getAttribute("userName"));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto){

        userDto=service.createUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);


    }

}