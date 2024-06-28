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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // pattern:
    // 1) cannot be only numbers
    @Column(name = "name", unique = true)
    private String name;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
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
    private Set<Category> childCategories = new HashSet<>();

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Goods> goodsInCategory = new ArrayList<>();
}
