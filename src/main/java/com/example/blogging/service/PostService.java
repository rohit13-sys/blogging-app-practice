package com.example.blogging.service;


import com.example.blogging.entity.Post;
import com.example.blogging.payloads.PostDto;

import java.util.List;

public interface PostService {


    PostDto createPost(PostDto postDto, int userId, int categoryId);

    Post updatePost(PostDto postDto);

    void deletePost(PostDto postDto);

    List<Post> getAllPosts();

    Post getPostById(int postId);

    List<Post> getPostByCategory(int categoryId);


    List<Post> getPostByUser(int userId);

    List<Post> searchPosts(String keyword);
}
