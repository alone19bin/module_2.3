package org.maxim.crud.service;

import lombok.RequiredArgsConstructor;
import org.maxim.crud.repository.PostRepository;
import org.maxim.crud.repository.WriterRepository;
import org.maxim.crud.repository.hiber.WriterHib;
import org.maxim.crud.model.Writer;


import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class WriterService {
    private final WriterRepository writerRepository;
    private final PostRepository postRepository;

    public Optional<Writer> getById(Integer id) {
        return writerRepository.getId(id);
    }

    public void deleteById(Integer id) {
        writerRepository.deleteById(id);
    }

    public Optional<List<Writer>> getAll() {
        return writerRepository.getAll();
    }
    public Optional<Writer> save(Writer writer, List<Long> postsId) {
        save(writer, postsId);
        return writerRepository.save(writer);
    }

    public Optional<Writer> update(Writer writer, List<Long> postsId) {
        save(writer, postsId);
        return writerRepository.update(writer);
    }
}
