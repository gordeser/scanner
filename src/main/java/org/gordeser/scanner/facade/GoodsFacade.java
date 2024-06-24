package org.gordeser.scanner.facade;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.GoodsDTO;
import org.gordeser.scanner.dao.entity.Goods;
import org.gordeser.scanner.service.GoodsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GoodsFacade {
    private final GoodsService goodsService;

    public Goods createGoods(GoodsDTO goodsDTO) {
        Goods newGoods = new Goods();

        newGoods.setName(goodsDTO.getName());
        newGoods.setCreatedAt(LocalDateTime.now());
        newGoods.setUpdatedAt(LocalDateTime.now());
        
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
}
