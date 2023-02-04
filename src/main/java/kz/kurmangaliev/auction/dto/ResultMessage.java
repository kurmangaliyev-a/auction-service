package kz.kurmangaliev.auction.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultMessage {
    boolean success;
    Object message;
    public static ResultMessage failure(Object message){
        return ResultMessage.builder().message(message).build();
    }
    public static ResultMessage success(Object message){
        return ResultMessage.builder().message(message).success(true).build();

    }
}
