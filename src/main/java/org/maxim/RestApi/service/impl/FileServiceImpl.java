package org.maxim.RestApi.service.impl;


import org.maxim.RestApi.model.File;
import org.maxim.RestApi.repository.FileRepository;
import org.maxim.RestApi.repository.hiber.FileRepositoryImpl;

import java.util.List;



public class FileServiceImpl {
    private final FileRepository fileRepository = new FileRepositoryImpl();

    public List<File> getAll() {
        return fileRepository.getAll();
    }

    public File getById(Integer id) {
        return fileRepository.getById(id);
    }

    public File save(File file) {
        return fileRepository.save(file);
    }

    public boolean deleteById(Integer id) {
        return fileRepository.deleteById(id);
    }

    public File update(File file) {
        return fileRepository.update(file);
    }
}

