package org.gordeser.scanner.facade;

import com.amazonaws.services.s3.model.S3Object;
import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.entity.Goods;
import org.gordeser.scanner.dao.entity.Image;
import org.gordeser.scanner.dao.entity.User;
import org.gordeser.scanner.service.GoodsService;
import org.gordeser.scanner.service.ImageService;
import org.gordeser.scanner.service.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageFacade {
    private final ImageService imageService;
    private final S3Service s3Service;
    private final GoodsService goodsService;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;

        newImage.setPath(imageDTO.getPath());
        newImage.setHash(imageDTO.getHash());
        return imageService.save(newImage);
    }

    public List<Image> getImagesByGoodsId(Long goodsId) {
        return imageService.getImagesByGoodsId(goodsId);
    }

            throw new Exception("Image not found");
        }

        updatedImage.setPath(imageDTO.getPath());
        updatedImage.setHash(imageDTO.getHash());
        return imageService.save(updatedImage);
    }
}
