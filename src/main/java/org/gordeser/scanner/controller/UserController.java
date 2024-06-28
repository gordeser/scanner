package org.gordeser.scanner.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.UserDTO;
import org.gordeser.scanner.dao.dto.UserUpdateDTO;
import org.gordeser.scanner.dao.entity.Goods;
import org.gordeser.scanner.dao.entity.Review;
import org.gordeser.scanner.dao.entity.User;
import org.gordeser.scanner.facade.UserFacade;
import org.gordeser.scanner.response.UserUpdateResponse;
import org.gordeser.scanner.service.GoodsService;
import org.gordeser.scanner.service.JwtService;
import org.gordeser.scanner.service.ReviewService;
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

    private final GoodsService goodsService;
    private final JwtService jwtService;
    private final ReviewService reviewService;


    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/goods")
    public ResponseEntity<List<Goods>> getUserCreatedGoods() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        List<Goods> goods = goodsService.getAllGoodsByUsername(currentUser.getUsername());

        return ResponseEntity.ok(goods);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getUserReviews() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        List<Review> reviews = reviewService.getReviewsByUsername(currentUser.getUsername());

        return ResponseEntity.ok(reviews);
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

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        userFacade.deleteUser(currentUser);
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }
}
