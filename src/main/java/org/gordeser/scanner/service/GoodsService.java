package org.gordeser.scanner.service;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.entity.Goods;
import org.gordeser.scanner.dao.repository.GoodsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;

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
}
