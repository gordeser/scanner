package org.gordeser.scanner.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users createdBy;

    @ManyToOne
    @JoinColumn(name = "last_updated_by")
    private Users lastUpdatedBy;

    @ManyToMany
    @JoinTable(
            name = "goods_category",
            joinColumns = @JoinColumn(name = "goods_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoriesIn = new ArrayList<>();

    @OneToMany(mappedBy = "goods")
    private List<Image> goodsImages = new ArrayList<>();

    @OneToMany(mappedBy = "goods")
    private List<Review> goodsReviews = new ArrayList<>();
}

