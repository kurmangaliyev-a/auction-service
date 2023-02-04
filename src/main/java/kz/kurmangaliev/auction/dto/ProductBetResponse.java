package kz.kurmangaliev.auction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductBetResponse {
    private Long productId;
    private String name;
    @JsonFormat(pattern = "dd-MM-YYYY hh-mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime finishedAt;
    private BigDecimal price;
}
