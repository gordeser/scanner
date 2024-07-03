package org.gordeser.scanner.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @Column(name = "original_filename")
    private String originalFilename;

    @Column(name = "hash")
    private String hash;

    @Column(name = "upload_date", updatable = false)
    private LocalDateTime uploadDate;

    @Column(name = "is_deleted")
    private boolean isDeleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    @JsonIgnore
    private Goods goods;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    @JsonIgnore
    private User uploadedBy;
}
