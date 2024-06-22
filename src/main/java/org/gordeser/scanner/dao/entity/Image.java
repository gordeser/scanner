package org.gordeser.scanner.dao.entity;

import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "path")
    private String path;

    @Column(name = "hash")
    private String hash;
}
