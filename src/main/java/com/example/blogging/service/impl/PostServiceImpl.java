package com.example.blogging.service.impl;

import com.example.blogging.entity.Category;
import com.example.blogging.entity.Post;
import com.example.blogging.entity.Users;
import com.example.blogging.exceptions.CategoryNotFound;
import com.example.blogging.exceptions.UserNotFound;
import com.example.blogging.payloads.PostDto;
import com.example.blogging.repository.CategoryRepository;
import com.example.blogging.repository.PostRepository;
import com.example.blogging.repository.UserReposiory;
import com.example.blogging.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserReposiory userReposiory;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public PostDto createPost(PostDto postDto, int userId, int categoryId) {
        Users user=userReposiory.findById(userId).orElseThrow(()->new UserNotFound("User Not Found!!"));
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFound("User Not Found!!"));
        Post post=mapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        post=postRepository.save(post);
        return mapper.map(post,PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto) {
        return null;
    }

    @Override
    public void deletePost(PostDto postDto) {

    }

    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    @Override
    public Post getPostById(int postId) {
        return null;
    }

    @Override
    public List<Post> getPostByCategory(int categoryId) {
        return null;
    }

    @Override
    public List<Post> getPostByUser(int userId) {
        return null;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
