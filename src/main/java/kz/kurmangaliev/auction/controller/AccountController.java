package kz.kurmangaliev.auction.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.kurmangaliev.auction.db.model.Product;
import kz.kurmangaliev.auction.dto.ProductBetRequest;
import kz.kurmangaliev.auction.dto.ProductRequest;
import kz.kurmangaliev.auction.dto.ResultMessage;
import kz.kurmangaliev.auction.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/account")
@RequiredArgsConstructor
@Tag(name = "AccountController", description = "Личный кабинет")
public class AccountController extends AbstractController {
    private final ProductService productService;

    @Operation(summary = "Метод добавления нового лота")
    @PostMapping(value = "/addProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResultMessage> add(
            @RequestParam("name") String name,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("description") String description,
            @RequestParam("startPrice") BigDecimal startPrice,
            @RequestParam("duration") Long duration,
            @RequestPart("files") MultipartFile[] files
    ) {
        Long userId = getUserId();
        log.info("Пользователь {} добавляет объект {}", userId, name);
        ProductRequest productRequest = ProductRequest.builder()
                .name(name)
                .categoryId(categoryId)
                .description(description)
                .price(startPrice)
                .duration(duration)
                .build();

        ResultMessage resultMessage = productService.createProduct(productRequest, userId, files);
        return ResponseEntity.ok(resultMessage);
    }

    @GetMapping("/bid")
    public ResponseEntity<ResultMessage> bid(@RequestBody ProductBetRequest request) {
        Long userId = getUserId();
        log.info("Пользователь {} повышает ставку на товар:{}. Цена: {}", userId, request.getProductId(), request.getPrice());
        ResultMessage resultMessage = productService.bidProduct(request, userId);
        return ResponseEntity.ok(resultMessage);
    }

    @PostMapping("/{productId}/changeStatus")
    public ResponseEntity<ResultMessage> changeStatus(@PathVariable Long productId) {
        Long userId = getUserId();
        log.info("Пользователь {} делает товар {} неактивным", userId, productId);
        ResultMessage resultMessage = productService.changeStatus(productId, userId);
        return ResponseEntity.ok(resultMessage);
    }

    @GetMapping("/getByUser")
    private ResponseEntity<List<Product>> getByUser() {
        return ResponseEntity.ok(productService.getByUserId(getUserId()));
    }
}
