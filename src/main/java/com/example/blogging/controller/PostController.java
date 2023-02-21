package com.example.blogging.controller;

import com.example.blogging.entity.Post;
import com.example.blogging.payloads.PostDto;
import com.example.blogging.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/user/{user-id}/category/{category-id}/posts")
    public ResponseEntity<Object> createPost(@RequestBody PostDto postDto,
    @PathVariable("user-id") int userId,@PathVariable("category-id") int categoryId){
        PostDto post=postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

}
