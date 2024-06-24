package org.gordeser.scanner.service;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.entity.Users;
import org.gordeser.scanner.dao.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Users findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users save(Users user) {
        return userRepository.save(user);
    }

    public Users update(Users user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        Users user = findById(id);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
