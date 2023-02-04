package kz.kurmangaliev.auction.db.repo;

import kz.kurmangaliev.auction.db.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByAuthorId(Long authorId);
}