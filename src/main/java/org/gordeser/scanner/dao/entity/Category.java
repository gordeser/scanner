package org.gordeser.scanner.dao.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @LastModifiedDate
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @CreatedDate
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
}
