package org.gordeser.scanner.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.UserDTO;
import org.gordeser.scanner.dao.dto.UserUpdateDTO;
import org.gordeser.scanner.dao.entity.User;
import org.gordeser.scanner.facade.UserFacade;
import org.gordeser.scanner.response.UserUpdateResponse;
import org.gordeser.scanner.service.JwtService;
import org.gordeser.scanner.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserFacade userFacade;
    private final JwtService jwtService;


    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) {
        User newUser = userFacade.createUser(userDTO);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping
    public ResponseEntity<UserUpdateResponse> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        User updatedUser = userFacade.updateUser(currentUser, userUpdateDTO);
        String newToken = jwtService.generateToken(updatedUser);
        Long expiresIn = jwtService.getExpirationTime();

        UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
        userUpdateResponse.setUsername(updatedUser.getUsername());
        userUpdateResponse.setEmail(updatedUser.getEmail());
        userUpdateResponse.setFirstName(updatedUser.getFirstName());
        userUpdateResponse.setLastName(updatedUser.getLastName());
        userUpdateResponse.setToken(newToken);
        userUpdateResponse.setExpiresIn(expiresIn);

        return ResponseEntity.ok(userUpdateResponse);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
