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

    public Goods updateGoodsById(Long goodsId, GoodsDTO goodsDTO) throws Exception {
        Goods updatedGoods = goodsService.findById(goodsId);
        if (updatedGoods == null) {
            throw new Exception("Goods not found");
        }

        updatedGoods.setName(goodsDTO.getName());
        updatedGoods.setUpdatedAt(LocalDateTime.now());
        return goodsService.save(updatedGoods);
    }

    public List<Category> getGoodsCategories(Long goodsId) throws Exception {
        Goods goods = goodsService.findById(goodsId);
        if (goods == null) {
            throw new Exception("Goods is not found");
        }

        return goodsService.getGoodsCategories(goods);
    }

}
