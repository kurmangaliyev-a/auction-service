package kz.kurmangaliev.auction.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.kurmangaliev.auction.enums.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Schema(name = "Товар", description = "Свойства и описание товара ")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "Ид товара")
    private Long id;

    @Schema(description = "Ид продавца")
    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Schema(description = "ИД покупателя с самой высокой ставкой")
    @Column(name = "customer_id")
    private Long customerId;

    @Schema(description = "ИД категории")
    @Column(name = "category_id")
    private Long categoryId;

    @Schema(description = "Статус продукта. Активный/Неактивный")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Schema(description = "Название продукта")
    @Column(name = "name")
    private String name;

    @Schema(description = "Описание продукта")
    @Column(name = "description")
    private String description;

    @Schema(description = "Стартовая цена продукта")
    @Column(name = "start_price")
    private BigDecimal startPrice;

    @Schema(description = "Текущая цена продукта")
    @Column(name = "price")
    private BigDecimal price;

    @Schema(description = "Длительность аукциона. Время запускается при первой ставке. Задаётся в милисекундах.")
    @Column(name = "duration")
    private Long duration;

    @Schema(description = "Время завершения аукциона, Время завершения акции.")
    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    @JsonIgnore
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JsonIgnore
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}