package org.gordeser.scanner.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "path")
    private String path;

    @Column(name = "hash")
    private String hash;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;
}
