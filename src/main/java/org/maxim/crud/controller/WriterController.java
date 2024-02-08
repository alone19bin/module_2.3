package org.maxim.crud.controller;

import lombok.RequiredArgsConstructor;
import org.maxim.crud.model.Writer;
import org.maxim.crud.service.PostService;
import org.maxim.crud.service.WriterService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class WriterController {

    private final WriterService writerService;
    private final PostService postService;

    public Optional<Writer> get(Integer id) {
        return writerService.getById(id);
    }

    public void deleteById(Integer id) {
        writerService.deleteById(id);
    }

    public Optional<Writer> save(Writer writer, List<Long> postsId) {
        return writerService.save(writer, postsId);
    }

    public Optional<Writer> update(Writer w, List<Long> postsId) {
        return writerService.update(w, postsId);
    }

    public Optional<List<Writer>> getAll() {
        return writerService.getAll();
    }
}
