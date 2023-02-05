package kz.kurmangaliev.auction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.kurmangaliev.auction.db.model.Category;
import kz.kurmangaliev.auction.db.model.Product;
import kz.kurmangaliev.auction.dto.ProductDto;
import kz.kurmangaliev.auction.service.CategoryService;
import kz.kurmangaliev.auction.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.util.List;

@Tag(name = "ProductController", description = "Работа с товаром")
@RestController
@Slf4j
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Operation(summary = "Получить список всех активных продуктов")
    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @Operation(summary = "Получить список категроий")
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @Operation(summary = "Получить определённый продукт")
    @GetMapping(value = "/get/{productId}")
    public ResponseEntity<ProductDto> get(@PathVariable Long productId) {

        ProductDto product = productService.getProduct(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(product);
    }

}


