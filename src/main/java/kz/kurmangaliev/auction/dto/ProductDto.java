package kz.kurmangaliev.auction.dto;

import kz.kurmangaliev.auction.db.model.Photo;
import kz.kurmangaliev.auction.db.model.Product;
import lombok.Builder;
import lombok.Data;
import org.springframework.core.io.InputStreamResource;

import java.util.List;

@Data
@Builder
public class ProductDto {
    private Product product;
    private List<String> files;
}
