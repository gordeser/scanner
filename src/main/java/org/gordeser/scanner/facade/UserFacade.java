package org.gordeser.scanner.facade;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.UserDTO;
import org.gordeser.scanner.dao.entity.User;
import org.gordeser.scanner.dao.repository.UserRepository;
import org.gordeser.scanner.service.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserFacade {
    private final UserService userService;
    private final UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());

        return userService.save(newUser);
    }

    public User updateUser(Long userId, UserDTO userDTO) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new Exception("Not found");
        }

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        if (userDTO.getFirstName() != null) {
            user.setFirstName(userDTO.getFirstName());
        }
        if (userDTO.getLastName() != null) {
            user.setLastName(userDTO.getLastName());
        }

        return userService.update(user);
    }
}
