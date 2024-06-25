package org.gordeser.scanner.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.GoodsDTO;
import org.gordeser.scanner.dao.entity.Goods;
import org.gordeser.scanner.facade.GoodsFacade;
import org.gordeser.scanner.service.GoodsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/goods")
public class GoodsController {
    private final GoodsService goodsService;
    private final GoodsFacade goodsFacade;

    @GetMapping
    public ResponseEntity<List<Goods>> getAllGoods() {
        List<Goods> allGoods = goodsService.findAll();
        return ResponseEntity.ok(allGoods);
    }

    @GetMapping("/{goodsId}")
    public ResponseEntity<Goods> getGoods(@PathVariable Long goodsId) {
        Goods goods = goodsService.findById(goodsId);
        return ResponseEntity.ok(goods);
    }

    @PostMapping
    public ResponseEntity<Goods> createGoods(@RequestBody @Valid GoodsDTO goodsDTO) {
        Goods goods = goodsFacade.createGoods(goodsDTO);
        return ResponseEntity.ok(goods);
    }

    @PutMapping("/{goodsId}")
    public ResponseEntity<Goods> updateGoods(@PathVariable Long goodsId, @RequestBody @Valid GoodsDTO goodsDTO) throws Exception {
        Goods goods = goodsFacade.updateGoodsById(goodsId, goodsDTO);
        return ResponseEntity.ok(goods);
    }

    @DeleteMapping("/{goodsId}")
    public ResponseEntity<?> deleteGoods(@PathVariable Long goodsId) {
        goodsService.deleteById(goodsId);
        return ResponseEntity.noContent().build();
    }
}
