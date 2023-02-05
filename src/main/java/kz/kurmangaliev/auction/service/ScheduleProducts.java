package kz.kurmangaliev.auction.service;

import kz.kurmangaliev.auction.db.model.Product;
import kz.kurmangaliev.auction.db.repo.ProductRepository;
import kz.kurmangaliev.auction.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleProducts {
    private final ProductRepository productRepository;
    private final NotificationService notificationService;

    @Scheduled(cron = "0 * * * *")
    public void checkProducts() {
        List<Product> products = productRepository.findAllActiveProducts();
        for (Product product : products) {
            if (product.getFinishedAt().compareTo(LocalDateTime.now()) <= 0) {
                product.setStatus(Status.INACTIVE);
                log.info("Продукт {} продан пользователю {}", product.getId(), product.getCustomerId());
                notificationService.soldNotifications(product);
            }
        }
    }
}
