package org.maxim.crud.service;

import lombok.RequiredArgsConstructor;
import org.maxim.crud.repository.PostRepository;
import org.maxim.crud.repository.WriterRepository;
import org.maxim.crud.repository.hiber.WriterHib;
import org.maxim.crud.model.Writer;


import java.util.List;
import java.util.Optional;

public class WriterService {
    WriterRepository writerRepository;

    public WriterService() {
        writerRepository = new WriterHib();
    }

    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public Writer save(Writer writer) {
        return writerRepository.save(writer);
    }

    public List<Writer> getAll() {
        return writerRepository.getAll();
    }

    public Writer getById(int id) {
        return writerRepository.getById(id);
    }

    public Writer update(Writer writer) {
        return writerRepository.update(writer);
    }

    public boolean deleteById(int id) {
        return writerRepository.deleteById(id);
    }
}
