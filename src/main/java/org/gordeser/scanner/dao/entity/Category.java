package org.gordeser.scanner.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @LastModifiedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @CreatedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "last_updated_by")
    private User lastUpdatedBy;

    @ManyToOne
    @JoinColumn(name = "parent")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    @JsonIgnore
    private List<Category> childCategories;

    @ManyToMany(mappedBy = "categoriesIn")
    @JsonIgnore
    private List<Goods> goodsInCategory = new ArrayList<>();
}
