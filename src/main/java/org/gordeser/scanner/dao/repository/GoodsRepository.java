package org.gordeser.scanner.dao.repository;

import org.gordeser.scanner.dao.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    List<Goods> getGoodsByCreatedByUsername(String createdByUsername);
}
