package org.maxim.crud.controller;

import lombok.RequiredArgsConstructor;
import org.maxim.crud.model.Post;
import org.maxim.crud.model.Status;
import org.maxim.crud.model.Writer;
import org.maxim.crud.service.PostService;
import org.maxim.crud.service.WriterService;

import java.util.List;
import java.util.Optional;


public class WriterController {
    private final WriterService writerService = new WriterService();

    public Writer add(String lastName, String firstName, List<Post> posts) {
        Writer newWriter = new Writer();
        newWriter.setLastName(lastName);
        newWriter.setFirstName(firstName);
        newWriter.setPosts(posts);
        newWriter.setStatus(Status.ACTIVE);
        return writerService.save(newWriter);
    }

    public List<Writer> getAll() {
        return writerService.getAll();
    }

    public Writer getById(int id) {
        return writerService.getById(id);
    }

    public Writer update(Writer writer, String newLastName, String newFirstName, List<Post> newPosts, boolean changeStatus) {
        boolean changeLastName = (newLastName != null && !newLastName.isEmpty());
        boolean changeFirstName = (newFirstName != null && !newFirstName.isEmpty());
        boolean changePosts = newPosts != null;
        if (!changeLastName && !changeFirstName && !changePosts && !changeStatus) return writer;
        if (changeLastName) writer.setLastName(newLastName);
        if (changeFirstName) writer.setFirstName(newFirstName);
        if (changePosts) writer.setPosts(newPosts);
        if (changeStatus) {
            Status newStatus = (writer.getStatus() == Status.DELETED) ? Status.ACTIVE : Status.DELETED;
            writer.setStatus(newStatus);
        }
        return writerService.update(writer);
    }

    public boolean deleteById(int id) {
        return writerService.deleteById(id);
    }
}
