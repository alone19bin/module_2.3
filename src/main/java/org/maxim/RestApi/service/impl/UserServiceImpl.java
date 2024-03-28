package org.maxim.RestApi.service.impl;


import org.maxim.RestApi.model.User;
import org.maxim.RestApi.repository.UserRepository;
import org.maxim.RestApi.repository.hiber.UserRepositoryImpl;

import java.util.List;

public class UserServiceImpl {

    private UserRepository userRepository = new UserRepositoryImpl();

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean deleteById(Integer id) {
        return userRepository.deleteById(id);
    }

    public User update(User user) {
        return userRepository.update(user);
    }
}
