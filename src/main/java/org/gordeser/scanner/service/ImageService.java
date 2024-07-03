package org.gordeser.scanner.service;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.entity.Image;
import org.gordeser.scanner.dao.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image getById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public List<Image> getImagesByGoodsId(Long goodsId) {
        return imageRepository.findImagesByGoodsId(goodsId);
    }
}
