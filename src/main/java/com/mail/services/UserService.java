package com.mail.services;

import com.mail.models.User;
import com.mail.models.dto.UserCreateDto;
import com.mail.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    private final User user;

    @Autowired
    public UserService(UserRepository userRepository, User user) {
        this.userRepository = userRepository;
        this.user = user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(userRepository.findUserById(id));
    }

    public Boolean deleteUserById(Long id) {
        return userRepository.deleteUser(id);
    }

    public Boolean createUser(UserCreateDto userFromDto) {
        user.setUsername(userFromDto.getUsername());
        user.setUserLocation(userFromDto.getUserLocation());
        user.setRegistrationDate(Timestamp.valueOf(LocalDateTime.now()));
        return userRepository.createUser(user);
    }

    public Boolean updateUser(Long id, String username, Boolean isDeleted, String userLocation) {
        Optional<User> userFromDbOptional = Optional.ofNullable(userRepository.findUserById(id));
        if (userFromDbOptional.isPresent()) {
            User userFromDb = userFromDbOptional.get();
            if (username != null) {
                userFromDb.setUsername(username);
            }
            if (userLocation != null) {
                userFromDb.setUserLocation(userLocation);
            }
            if (isDeleted != null) {
                userFromDb.setIsDeleted(isDeleted);
            }
            userFromDb.setChangeDate(Timestamp.valueOf(LocalDateTime.now()));
            return userRepository.updateUser(userFromDb);
        }
        return false;
    }
}
