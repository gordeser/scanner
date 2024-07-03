package org.gordeser.scanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
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

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Image> createImage(@RequestParam MultipartFile file, @RequestParam Long goodsId) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Image image = imageFacade.createImage(file, goodsId, user);
        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable Long imageId) throws Exception {
        imageFacade.deleteImageById(imageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws Exception {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(imageFacade.getImageFile(imageId).getObjectContent()));
    }

    @GetMapping("/view/{imageId}")
    public ResponseEntity<Resource> viewImage(@PathVariable Long imageId) throws Exception {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imageFacade.getImageName(imageId) + "\"")
                .body(new InputStreamResource(imageFacade.getImageFile(imageId).getObjectContent()));
    }
}
