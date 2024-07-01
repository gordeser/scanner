package org.gordeser.scanner.service;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.entity.Category;
import org.gordeser.scanner.dao.entity.Goods;
import org.gordeser.scanner.dao.repository.CategoryRepository;
import org.gordeser.scanner.dao.repository.GoodsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final CategoryRepository categoryRepository;

    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    public Goods findById(Long id) {
        return goodsRepository.findById(id).orElse(null);
    }

    public Goods save(Goods goods) {
        return goodsRepository.save(goods);
    }

    public Goods update(Goods goods) {
        return goodsRepository.save(goods);
    }

    public void deleteById(Long id) {
        Goods goods = findById(id);
        if (goods != null) {
            goodsRepository.delete(goods);
        }
    }

    public List<Goods> getAllGoodsByUsername(String username) {
        return goodsRepository.getGoodsByCreatedByUsername(username);
    }

    public List<Category> getGoodsCategories(Goods goods) {
        return categoryRepository.findCategoriesByGoodsInCategoryContaining(goods);
    }
}
