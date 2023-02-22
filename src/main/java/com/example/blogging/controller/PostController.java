package com.example.blogging.controller;

import com.example.blogging.entity.Post;
import com.example.blogging.payloads.PostDto;
import com.example.blogging.payloads.PostResponse;
import com.example.blogging.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/user/{user-id}/posts")
    public ResponseEntity<Object> getPostsByUser(@PathVariable("user-id") int userId){
        List<PostDto> postDtoList=postService.getPostByUser(userId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("/category/{category-id}/posts")
    public ResponseEntity<Object> getPostsByCategory(@PathVariable("category-id") int categoryId){
        List<PostDto> postDtoList=postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("all-posts")
    public ResponseEntity<Object> getAllPosts(@RequestParam(value = "pageNo",defaultValue = "0",required = false) Integer pageNumber,
                                              @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
                                              @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
                                              @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){
        PostResponse postResponse=postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("/delete-posts/{post-id}")
    public ResponseEntity<Object> deletePostById(@PathVariable("post-id") int id){
        PostDto postDto= postService.getPostById(id);
        postService.deletePost(postDto);
        return new ResponseEntity<>("Post : "+postDto.getTitle()+" is deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/update-posts/{post-id}")
    public ResponseEntity<Object> updatePostById(@RequestBody PostDto postDto,@PathVariable("post-id") int id){
        postDto=postService.updatePost(postDto,id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

}
