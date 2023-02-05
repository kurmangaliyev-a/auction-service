package kz.kurmangaliev.auction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "Вовзращает результат операции", name = "Response (Результат запроса апи.)")
@Data
@Builder
public class ResultMessage {
    @Schema(description = "true - программа отработала без ошибок.")
    boolean success;
    @Schema(description = "Объект. Описание результата.")
    Object message;

    public static ResultMessage failure(Object message) {
        return ResultMessage.builder().message(message).build();
    }

    public static ResultMessage success(Object message) {
        return ResultMessage.builder().message(message).success(true).build();

    }

    public static ResultMessage success() {
        return ResultMessage.builder().success(true).build();

    }
}
