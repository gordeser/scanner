package org.gordeser.scanner.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @OneToMany(mappedBy = "createdBy")
    private List<Goods> goodsCreated = new ArrayList<>();

    @OneToMany(mappedBy = "lastUpdatedBy")
    private List<Goods> goodsLastUpdated = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    private List<Goods> reviewsCreated = new ArrayList<>();

    @OneToMany(mappedBy = "lastUpdatedBy")
    private List<Category> categoriesLastUpdated = new ArrayList<>();
}
