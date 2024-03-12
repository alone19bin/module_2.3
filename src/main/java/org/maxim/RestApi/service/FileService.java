package org.maxim.RestApi.service;


import org.maxim.RestApi.model.Event;
import org.maxim.RestApi.model.File;
import org.maxim.RestApi.model.User;
import org.maxim.RestApi.repository.FileRepository;
import org.maxim.RestApi.repository.hiber.FileHib;
import org.maxim.RestApi.utils.FileUtil;

import java.io.InputStream;
import java.util.List;

public class FileService {
    private UserService userService;
    private EventService eventService;
    private FileRepository fileRepository = new FileHib();

    public List<File> getAll() {
        return fileRepository.getAll();
    }

    public File getById(Integer id) {
        return fileRepository.getById(id);
    }

    public File save(File file) {
        return fileRepository.save(file);
    }

    public  File update(File file) {
        return fileRepository.update(file);
    }

    public boolean deleteById(Integer id) {
        return fileRepository.deleteById(id);
    }


}
