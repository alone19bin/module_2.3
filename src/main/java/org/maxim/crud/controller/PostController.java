package org.maxim.crud.controller;

import lombok.RequiredArgsConstructor;

import org.maxim.crud.model.Label;
import org.maxim.crud.model.Post;
import org.maxim.crud.model.Status;
import org.maxim.crud.repository.hiber.LabelHib;
import org.maxim.crud.service.LabelService;
import org.maxim.crud.service.PostService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PostController {
    private final PostService postService = new PostService();

    public Post add(String content, String created, String updated, List<Label> labels) {
        Post newPost = new Post();
        newPost.setContent(content);
        newPost.setContent(created);
        newPost.setContent(updated);
        newPost.setLabels(labels);
        newPost.setStatus(Status.ACTIVE);
        return postService.save(newPost);
    }

    public List<Post> getAll() {
        return postService.getAll();
    }

    public Post getById(int id) {
        return postService.getById(id);
    }

    public Post update(Post post, String newTitle, String newContent, List<Label> newPostLabels, boolean changeStatus) {
        boolean changeTitle = (newTitle != null && !newTitle.isEmpty());
        boolean changeContent = (newContent != null && !newContent.isEmpty());
        boolean changeLabels = newPostLabels != null;

        if (!changeTitle && !changeContent && !changeLabels && !changeStatus) return post;
        if (changeTitle) post.setContent(newTitle);
        if (changeContent) post.setContent(newContent);
        if (changeLabels) post.setLabels(newPostLabels);
        if (changeStatus) {
            Status newStatus = (post.getStatus() == Status.DELETED) ? Status.ACTIVE : Status.DELETED;
            post.setStatus(newStatus);
        }
        return postService.update(post);
    }

    public boolean deleteById(int id) {
        return postService.deleteById(id);
    }
}
