package org.gordeser.scanner.facade;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.UserDTO;
import org.gordeser.scanner.dao.dto.UserUpdateDTO;
import org.gordeser.scanner.dao.entity.User;
import org.gordeser.scanner.dao.repository.UserRepository;
import org.gordeser.scanner.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserFacade {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());

        return userService.save(newUser);
    }

    public User updateUser(User currentUser, UserUpdateDTO userUpdateDTO) throws Exception {
        String updateEmail = userUpdateDTO.getEmail();
        if (updateEmail != null) {

            if (userRepository.findByEmail(updateEmail).isPresent() &&
                    !currentUser.getEmail().equals(updateEmail)) {
                throw new Exception("Email already exists");
            }

            currentUser.setEmail(updateEmail);
        }

        String updateUsername = userUpdateDTO.getUsername();
        if (updateUsername != null) {

            if (userRepository.findByUsername(updateUsername).isPresent() &&
                    !currentUser.getUsername().equals(updateUsername)) {
                throw new Exception("Username already exists");
            }

            currentUser.setUsername(updateUsername);
        }

        String updatePassword = userUpdateDTO.getPassword();
        if (updatePassword != null) {
            currentUser.setPassword(passwordEncoder.encode(updatePassword));
        }

        String firstName = userUpdateDTO.getFirstName();
        if (firstName != null) {
            currentUser.setFirstName(firstName);
        }

        String lastName = userUpdateDTO.getLastName();
        if (lastName != null) {
            currentUser.setLastName(lastName);
        }

        return userService.update(currentUser);
    }
}
