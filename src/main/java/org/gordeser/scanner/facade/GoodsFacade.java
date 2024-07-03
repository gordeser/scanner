package org.gordeser.scanner.facade;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.GoodsDTO;
import org.gordeser.scanner.dao.entity.Category;
import org.gordeser.scanner.dao.entity.Goods;
import org.gordeser.scanner.dao.entity.User;
import org.gordeser.scanner.service.CategoryService;
import org.gordeser.scanner.service.GoodsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsFacade {
    private final GoodsService goodsService;
    private final CategoryService categoryService;
    private final CategoryFacade categoryFacade;

    public Goods createGoods(GoodsDTO goodsDTO, User createdBy) throws Exception {
        Goods newGoods = new Goods();

        newGoods.setName(goodsDTO.getName());
        newGoods.setCreatedAt(LocalDateTime.now());
        newGoods.setUpdatedAt(LocalDateTime.now());
        newGoods.setCreatedBy(createdBy);
        newGoods.setLastUpdatedBy(createdBy);
        addGoodsToCategories(newGoods, goodsDTO.getCategories(), createdBy);
        
        return goodsService.save(newGoods);
    }

    public Goods updateGoodsById(Long goodsId, GoodsDTO goodsDTO, User updatedBy) throws Exception {
        Goods updatedGoods = goodsService.findById(goodsId);
        if (updatedGoods == null) {
            throw new Exception("Goods is not found");
        }

        updatedGoods.setName(goodsDTO.getName());
        updatedGoods.setUpdatedAt(LocalDateTime.now());
        updatedGoods.setLastUpdatedBy(updatedBy);

        // TODO: make set sub operation on updating categories
        removeGoodsFromCategories(updatedGoods, updatedGoods.getCategories(), updatedBy);
        addGoodsToCategories(updatedGoods, goodsDTO.getCategories(), updatedBy);

        return goodsService.save(updatedGoods);
    }

    public List<Category> getGoodsCategories(Long goodsId) throws Exception {
        Goods goods = goodsService.findById(goodsId);
        if (goods == null) {
            throw new Exception("Goods is not found");
        }

        return goodsService.getGoodsCategories(goods);
    }

    public void deleteGoods(Long goodsId, User deletedBy) throws Exception {
        Goods deletedGoods = goodsService.findById(goodsId);
        if (deletedGoods == null) {
            throw new Exception("Goods is not found");
        }

        removeGoodsFromCategories(deletedGoods, deletedGoods.getCategories(), deletedBy);

        deletedGoods.setName("DeletedGoods");
        deletedGoods.setLastUpdatedBy(deletedBy);
        deletedGoods.setUpdatedAt(LocalDateTime.now());
        goodsService.update(deletedGoods);
    }

    private void addGoodsToCategories(Goods goods, List<String> categoryList, User user) throws Exception {
        for (String categoryName : categoryList) {
            Category category = categoryFacade.findCategory(categoryName);
            goods.getCategories().add(category);
            category.getGoodsInCategory().add(goods);
            category.setLastUpdatedBy(user);
            category.setUpdatedAt(LocalDateTime.now());

            categoryService.update(category);
        }
    }

    private void removeGoodsFromCategories(Goods goods, List<Category> categoryList, User user) {
        for (Category category : categoryList) {
            goods.getCategories().remove(category);
            category.getGoodsInCategory().remove(goods);
            category.setLastUpdatedBy(user);
            category.setUpdatedAt(LocalDateTime.now());
            categoryService.update(category);
        }
    }
}
