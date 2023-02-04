package kz.kurmangaliev.auction.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductBetRequest {
    private BigDecimal price;
    private Long productId;
}
