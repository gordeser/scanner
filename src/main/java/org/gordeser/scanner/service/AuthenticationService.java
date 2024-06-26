package org.gordeser.scanner.service;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.UserLoginDTO;
import org.gordeser.scanner.dao.dto.UserRegisterDTO;
import org.gordeser.scanner.dao.entity.User;
import org.gordeser.scanner.dao.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User signup(UserRegisterDTO userRegisterDTO) throws Exception {
        if (userRepository.findByUsername(userRegisterDTO.getUsername()).isPresent()
                || userRepository.findByEmail(userRegisterDTO.getEmail()).isPresent()) {
            throw new Exception("User already exists");
        }


        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        return userRepository.save(user);
    }

    public User authenticate(UserLoginDTO userLoginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getUsername(),
                        userLoginDTO.getPassword()
                )
        );

        return userRepository.findByUsername(userLoginDTO.getUsername()).orElse(null);
    }
}
