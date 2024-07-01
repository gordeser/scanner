package org.gordeser.scanner.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.GoodsDTO;
import org.gordeser.scanner.dao.entity.Category;
import org.gordeser.scanner.dao.entity.Goods;
import org.gordeser.scanner.dao.entity.User;
import org.gordeser.scanner.facade.GoodsFacade;
import org.gordeser.scanner.service.GoodsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/{goodsId}/categories")
    public ResponseEntity<List<Category>> getGoodsCategories(@PathVariable Long goodsId) throws Exception {
        List<Category> categories = goodsFacade.getGoodsCategories(goodsId);
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Goods> createGoods(@RequestBody @Valid GoodsDTO goodsDTO) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Goods goods = goodsFacade.createGoods(goodsDTO, user);
        return ResponseEntity.ok(goods);
    }

    @PutMapping("/{goodsId}")
    public ResponseEntity<Goods> updateGoods(@PathVariable Long goodsId, @RequestBody @Valid GoodsDTO goodsDTO) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Goods goods = goodsFacade.updateGoodsById(goodsId, goodsDTO, user);
        return ResponseEntity.ok(goods);
    }

    @DeleteMapping("/{goodsId}")
    public ResponseEntity<?> deleteGoods(@PathVariable Long goodsId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        goodsFacade.deleteGoods(goodsId, user);
        return ResponseEntity.noContent().build();
    }
}
