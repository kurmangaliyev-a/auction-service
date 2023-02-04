package kz.kurmangaliev.auction.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductRequest {
    private Long categoryId;
    private String name;
    private String description;
    private BigDecimal price;
    private Long duration; //ms
}
