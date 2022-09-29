package com.deloitte.hackathon.service;

import com.deloitte.hackathon.dto.UsernamePasswordRequest;
import com.deloitte.hackathon.entity.User;
import com.deloitte.hackathon.exception.AppException;
import com.deloitte.hackathon.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new AppException("User not found"));
    }

    public User getCurrentUser(UsernamePasswordRequest usernamePasswordRequest) {
        return userRepository
                .findUserByUsernameAndPassword(usernamePasswordRequest.getUsername(),
                        usernamePasswordRequest.getPassword());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Long userId, User userRequest) {
        User user = getUser(userId);
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setCity(userRequest.getCity());
        userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        if (user != null) {
            return user;
        } else {
            throw new AppException("Invalid credentials");
        }
    }

}
