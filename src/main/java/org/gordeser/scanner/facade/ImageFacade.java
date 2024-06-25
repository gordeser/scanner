package org.gordeser.scanner.facade;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.ImageDTO;
import org.gordeser.scanner.dao.entity.Image;
import org.gordeser.scanner.service.ImageService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageFacade {
    private final ImageService imageService;

    public Image createImage(ImageDTO imageDTO) {
        Image newImage = new Image();

        newImage.setPath(imageDTO.getPath());
        newImage.setHash(imageDTO.getHash());
        return imageService.save(newImage);
    }

    public Image updateImageById(Long imageId, ImageDTO imageDTO) throws Exception {
        Image updatedImage = imageService.getById(imageId);
        if (updatedImage == null) {
            throw new Exception("Image not found");
        }

        updatedImage.setPath(imageDTO.getPath());
        updatedImage.setHash(imageDTO.getHash());
        return imageService.save(updatedImage);
    }
}
