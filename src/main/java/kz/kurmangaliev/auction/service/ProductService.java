package kz.kurmangaliev.auction.service;

import kz.kurmangaliev.auction.db.model.Photo;
import kz.kurmangaliev.auction.db.model.Product;
import kz.kurmangaliev.auction.db.repo.PhotoRepository;
import kz.kurmangaliev.auction.db.repo.ProductRepository;
import kz.kurmangaliev.auction.dto.ProductBetRequest;
import kz.kurmangaliev.auction.dto.ProductRequest;
import kz.kurmangaliev.auction.dto.ResultMessage;
import kz.kurmangaliev.auction.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    @Value("${app.environment.fileDirectory}")
    private String directory;
    private final ProductRepository productRepository;
    private final NotificationService notificationService;
    private final PhotoRepository photoRepository;

    @Transactional
    public ResultMessage createProduct(ProductRequest productRequest, Long userId, MultipartFile[] files) {
        try {
            Product product = productRepository.save(Product.builder()
                    .name(productRequest.getName())
                    .authorId(userId)
                    .categoryId(productRequest.getCategoryId())
                    .status(Status.ACTIVE)
                    .description(productRequest.getDescription())
                    .startPrice(productRequest.getPrice())
                    .duration(productRequest.getDuration())
                    .build());
            for (MultipartFile file : files) {
                UUID id = UUID.randomUUID();
                String fileName = String.format("%s%s", id, file.getOriginalFilename());
                File dest = new File(String.format("%s%s", directory, fileName));
                file.transferTo(dest);
                photoRepository.save(Photo.builder().id(id).filename(fileName).productId(product.getId()).build());
            }
            log.info("Пользователь {} добавил новый объект: {}", userId, product);
            return ResultMessage.success(product);
        } catch (IOException e) {
            log.warn("Ошибка загрузки файлов: {}", e.getMessage());
            e.printStackTrace();
            return ResultMessage.failure("При загрузке, фотографии что то пошло не так!");
        }
    }

    public ResultMessage bidProduct(ProductBetRequest request, Long userId) {
        Product product = productRepository.findById(userId).orElse(null);
        if (product == null) {
            log.warn("Продукт {} не найден", request.getProductId());
            return ResultMessage.failure("Продукт не найден.");
        }
        if (Status.INACTIVE.equals(product.getStatus())) {
            String error = String.format("Лот %s закрыт.", product.getId());
            log.warn(error);
            return ResultMessage.failure(error);
        }
        if (product.getFinishedAt() != null && product.getFinishedAt().compareTo(LocalDateTime.now()) <= 0) {
            product.setStatus(Status.INACTIVE);
            productRepository.save(product);
            String error = String.format("Лот %s закрыт.", product.getId());
            log.warn(error);
            notificationService.soldNotifications(product);
            return ResultMessage.failure(error);
        }

        if (product.getPrice() == null && product.getStartPrice().compareTo(request.getPrice()) > 0) {
            String error = String.format("Цена меньше начальной цены (%s).", product.getStartPrice());
            log.warn(error);
            return ResultMessage.failure(error);
        }
        if (product.getPrice() != null && product.getPrice().compareTo(request.getPrice()) >= 0) {
            String error = String.format("Цена меньше либо равна предыдущей ставке (%s).", product.getPrice());
            log.warn(error);
            return ResultMessage.failure(error);
        }
        product.setPrice(request.getPrice());
        notificationService.outbid(product);
        product.setCustomerId(userId);
        product.setFinishedAt(LocalDateTime.now().plus(product.getDuration(), ChronoUnit.MILLIS));
        log.info("Пользователь {} сделал ставку на товар {}, новая цена: {}", userId, product.getId(), request.getPrice());
        return ResultMessage.success("Поздравляем! Ваша ставка была принята.");
    }

    public ResultMessage changeStatus(Long productId, Long userId) {
        Product product = productRepository.findById(userId).orElse(null);
        if (product == null) {
            log.warn("Продукт {} не найден", productId);
            return ResultMessage.failure("Продукт не найден.");
        }
        if (!product.getAuthorId().equals(userId)) {
            String error = String.format("Вы не можете снять лот %s с публикации, потому что он вам не принадлежит.", product.getId());
            log.warn("Пользователь {} пытался поменять статус лота {}.", userId, product.getId());
            return ResultMessage.failure(error);
        }
        product.setStatus(Status.INACTIVE);
        productRepository.save(product);
        log.info("Пользователь {} изменил статус лота {} на неактивный", userId, product.getId());
        return ResultMessage.success("Статус изменён на неактивный");
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public List<Product> getByUserId(Long userId) {
        return productRepository.findAllByAuthorId(userId);
    }
}