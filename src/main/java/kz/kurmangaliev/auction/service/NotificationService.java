package kz.kurmangaliev.auction.service;

import kz.kurmangaliev.auction.db.model.Product;
import kz.kurmangaliev.auction.db.model.User;
import kz.kurmangaliev.auction.db.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final UserRepository userRepository;

    public void soldNotifications(Product product) {
        User author = userRepository.findById(product.getAuthorId()).orElseGet(() -> User.builder().firstname("Test")
                .lastname("Testov")
                .build());
        log.info("Уважаемый(ая) {} {}! Ваш товар {} был куплен по цене: {}.", author.getLastname(), author.getFirstname(), product.getName(), product.getPrice());

        User customer = userRepository.findById(product.getCustomerId()).orElseGet(() -> User.builder().firstname("Test")
                .lastname("Testov")
                .build());
        log.info("Уважаемый(ая) {} {}! Поздравляем с покупкой товара: {}.", customer.getLastname(), customer.getFirstname(), product.getName());
    }

    public void outbid(Product product) {
        User author = userRepository.findById(product.getAuthorId()).orElseGet(() -> User.builder().firstname("Test")
                .lastname("Testov")
                .build());
        log.info("Уважаемый(ая) {} {}! Новая ставка на ваш товар: {}.Текущая цена: {}.", author.getLastname(), author.getFirstname(), product.getName(), product.getPrice());
        if (product.getCustomerId() != null) {
            User customer = userRepository.findById(product.getCustomerId()).orElseGet(() -> User.builder()
                    .id(1L)
                    .firstname("Test")
                    .lastname("Testov")
                    .build());
            if (customer.getId() == null) {
                log.warn("Предудущий покупатель не найден");
            } else {
                log.info("Уважаемый(ая) {} {}! Ваша ставка на товар {} перебита.", customer.getLastname(), customer.getFirstname(), product.getName());
            }
        }
    }
}
