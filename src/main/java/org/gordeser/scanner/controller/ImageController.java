package org.gordeser.scanner.controller;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.entity.Image;
import org.gordeser.scanner.dao.entity.User;
import org.gordeser.scanner.facade.ImageFacade;
import org.gordeser.scanner.service.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/image")
@Validated
public class ImageController {
    private final ImageService imageService;
    private final ImageFacade imageFacade;

    @GetMapping("/{imageId}")
    public ResponseEntity<Image> getImage(@PathVariable Long imageId) {
        Image image = imageService.getById(imageId);
        return ResponseEntity.ok(image);
    }

    @PostMapping
    public ResponseEntity<List<Image>> getImagesByProductId(@RequestParam Long goodsId) {
        List<Image> images = imageFacade.getImagesByGoodsId(goodsId);
        return ResponseEntity.ok(images);
    }

    @PutMapping("/{imageId}")
    public ResponseEntity<Image> updateImage(@PathVariable Long imageId, @RequestBody @Valid ImageDTO imageDTO) throws Exception {
        Image image = imageFacade.updateImageById(imageId, imageDTO);
        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable Long imageId) {
        imageService.deleteById(imageId);
        return ResponseEntity.noContent().build();
    }
}
