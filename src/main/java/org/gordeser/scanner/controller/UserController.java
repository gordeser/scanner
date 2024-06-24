package org.gordeser.scanner.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.UserDTO;
import org.gordeser.scanner.dao.entity.Users;
import org.gordeser.scanner.facade.UserFacade;
import org.gordeser.scanner.service.UserService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<Users>> getUsers() {
        List<Users> users = userService.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable Long userId) {
        Users user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody @Valid UserDTO userDTO) {
        Users newUser = userFacade.createUser(userDTO);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable Long userId, @RequestBody @Valid UserDTO userDTO) throws Exception {
        Users updatedUser = userFacade.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
