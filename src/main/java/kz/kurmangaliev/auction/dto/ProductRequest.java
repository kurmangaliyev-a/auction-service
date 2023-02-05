package kz.kurmangaliev.auction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@Schema(name = "Товар - создание", description = "/addProduct - Request. Список параметров для создания продуктов ")
public class ProductRequest {
    @Schema(name = "ИД категории")
    private Long categoryId;
    @Schema(name = "Название продукта")
    private String name;
    @Schema(name = "Описание продукта")
    private String description;
    @Schema(name = "Стартовая цена продукта")
    private BigDecimal price;
    @Schema(name = "Длительность аукциона", description = "Время запускается при первой ставке. Задаётся в милисекундах.")
    private Long duration; //ms
}
