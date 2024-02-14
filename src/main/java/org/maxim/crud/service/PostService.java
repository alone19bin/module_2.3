package org.maxim.crud.service;

import org.maxim.crud.repository.PostRepository;
import org.maxim.crud.repository.hiber.PostHib;
import org.maxim.crud.model.Post;

import java.util.List;
import java.util.Optional;

public class PostService {
    private final PostRepository postRepository;

    public PostService() {
        postRepository = new PostHib();
    }

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post postToSave) {
        return postRepository.save(postToSave);
    }

    public List<Post> getAll() {
        return postRepository.getAll();
    }

    public Post getById(int id) {
        return postRepository.getById(id);
    }

    public Post update(Post post) {
        return postRepository.update(post);
    }

    public boolean deleteById(int id) {
        return postRepository.deleteById(id);
    }
}
