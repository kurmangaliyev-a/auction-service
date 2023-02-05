package kz.kurmangaliev.auction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "Данные для изменения стаимвки", name = "bid - Request (сделать ставку на товар)")
@Data
public class ProductBetRequest {
    @Schema(description = "Новая ставка (должна быть больше либо равна начальной ставки и больше предыдущей ставки")
    private BigDecimal price;
    @Schema(description = "Новая ставка (должна быть больше либо равна начальной ставки и больше предыдущей ставки")
    private Long productId;
}
