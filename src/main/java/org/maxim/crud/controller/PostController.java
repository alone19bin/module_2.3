package org.maxim.crud.controller;

import lombok.RequiredArgsConstructor;

import org.maxim.crud.model.Post;
import org.maxim.crud.service.PostService;

import java.util.Optional;

@RequiredArgsConstructor
public class PostController {
    private final PostService postService = new PostService();

    public Post createPost(Post post){
        return postService.savePost(post);
    }

    public Optional<Post> getPost(Integer postId){
        return postService.getPost(postId);
    }

    public Post updatePost(Post post){
        return postService.update(post);
    }

    public void deleteById(Integer postId){
        postService.deleteById(postId);
    }
}
