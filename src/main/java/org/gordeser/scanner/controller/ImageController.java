package org.gordeser.scanner.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.ImageDTO;
import org.gordeser.scanner.dao.entity.Image;
import org.gordeser.scanner.facade.ImageFacade;
import org.gordeser.scanner.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Image> createImage(@RequestBody @Valid ImageDTO imageDTO) {
        Image image = imageFacade.createImage(imageDTO);
        return ResponseEntity.ok(image);
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
