package org.maxim.crud.service;

import org.maxim.crud.repository.hiber.PostHib;
import org.maxim.crud.model.Post;

import java.util.Optional;

public class PostService {
    private final PostHib postHib;

    public PostService(PostHib postDao){
        this.postHib = postDao;
    }
    public PostService(){this.postHib = new PostHib();}

    public Optional<Post> getPost(Integer postId){
        return postHib.getId(postId);
    }

    public Post savePost(Post post){
        return postHib.savePost(post);
    }
    public Post update(Post post){
        return postHib.update(post);
    }

    public void deleteById(Integer integer){
        postHib.deleteById(integer);
    }
}
