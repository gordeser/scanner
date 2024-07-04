package org.gordeser.scanner.dao.repository;

import org.gordeser.scanner.dao.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findImagesByGoodsId(Long goodsId);
}
