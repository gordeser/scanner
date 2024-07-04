package org.gordeser.scanner.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    @OneToMany(mappedBy = "createdBy")
    @JsonIgnore
    private List<Goods> goodsCreated = new ArrayList<>();

    @OneToMany(mappedBy = "lastUpdatedBy")
    @JsonIgnore
    private List<Goods> goodsLastUpdated = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    @JsonIgnore
    private List<Review> reviewsCreated = new ArrayList<>();

    @OneToMany(mappedBy = "updatedBy")
    @JsonIgnore
    private List<Review> reviewsLastUpdated = new ArrayList<>();

    @OneToMany(mappedBy = "lastUpdatedBy")
    @JsonIgnore
    private List<Category> categoriesLastUpdated = new ArrayList<>();

    @OneToMany(mappedBy = "uploadedBy")
    private List<Image> uploadedImages;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isEnabled() {
        return !this.isDeleted;
    }
}