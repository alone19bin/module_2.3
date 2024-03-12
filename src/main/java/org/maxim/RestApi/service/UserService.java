package org.maxim.RestApi.service;


import org.maxim.RestApi.model.User;
import org.maxim.RestApi.repository.UserRepository;
import org.maxim.RestApi.repository.hiber.UserHib;

import java.util.List;

public class UserService {
private UserRepository userRepository = new UserHib();

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public  User update(User user) {
        return userRepository.update(user);
    }

    public boolean deleteById(Integer id) {
        return userRepository.deleteById(id);
    }

}
