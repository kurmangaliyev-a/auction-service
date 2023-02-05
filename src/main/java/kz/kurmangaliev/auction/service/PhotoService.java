package kz.kurmangaliev.auction.service;

import kz.kurmangaliev.auction.db.model.Photo;
import kz.kurmangaliev.auction.db.model.Product;
import kz.kurmangaliev.auction.db.repo.PhotoRepository;
import kz.kurmangaliev.auction.db.repo.ProductRepository;
import kz.kurmangaliev.auction.dto.ResultMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final ProductRepository productRepository;

    @Value("${app.environment.fileDirectory}")
    private String directory;

    public InputStreamResource getFile(String id) throws FileNotFoundException {
        Optional<Photo> photo = photoRepository.findById(UUID.fromString(id));
        if (photo.isEmpty()) {
            log.error("file {} not found in dataBase", id);
            return null;
        }
        String destination = String.format("%s%s", directory, photo.get().getFilename());
        log.info("filename {}", destination);
        InputStream in = new FileInputStream(destination);
        return new InputStreamResource(in);
    }

    public ResultMessage deleteFile(String id, Long userId) {
        Optional<Photo> photoOptional = photoRepository.findById(UUID.fromString(id));
        if (photoOptional.isEmpty() || photoOptional.get().getDeletedAt() != null) {
            log.error("file {} not found in dataBase", id);
            return ResultMessage.failure("Файл не найден");
        }
        Optional<Product> product = productRepository.findById(photoOptional.get().getProductId());
        if (product.isEmpty()) {
            log.error("product {} not found in dataBase", id);
            return ResultMessage.failure("Товар не найден");
        }
        if (!userId.equals(product.get().getAuthorId())) {
            log.error("User is not owner for this file. Product_id: {}; User_id: {}", product.get().getId(), userId);
            return ResultMessage.failure("Вы не владелец данного объявления.");
        }
        Photo photo = photoOptional.get();
        photo.setDeletedAt(LocalDateTime.now());
        photoRepository.save(photo);
        return ResultMessage.success();

    }
}
