package com.example.blogging.service;


import com.example.blogging.entity.Post;
import com.example.blogging.payloads.PostDto;

import java.util.List;

public interface PostService {


    PostDto createPost(PostDto postDto, int userId, int categoryId);

    PostDto updatePost(PostDto postDto);

    void deletePost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(int postId);

    List<PostDto> getPostByCategory(int categoryId);


    List<PostDto> getPostByUser(int userId);

    List<PostDto> searchPosts(String keyword);
}
