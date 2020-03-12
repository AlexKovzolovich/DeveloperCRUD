package ua.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.annotation.Logging;
import ua.epam.model.User;
import ua.epam.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Logging
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Logging
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
