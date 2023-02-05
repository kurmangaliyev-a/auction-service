package kz.kurmangaliev.auction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Возвращает результат в случае успешной ставки на лот", name = "bid - Response при ставке")
@Data
public class ProductBetResponse {
    @Schema(description = "ИД продукта")
    private Long productId;
    @Schema(description = "Название продукта")

    private String name;
    @Schema(description = "Время завершение акции")
    @JsonFormat(pattern = "dd-MM-YYYY hh-mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime finishedAt;
    @Schema(description = "Текущая цена продукта")
    private BigDecimal price;
}
