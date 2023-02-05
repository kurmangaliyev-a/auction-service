package kz.kurmangaliev.auction.db.repo;

import kz.kurmangaliev.auction.db.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByAuthorId(Long authorId);

    @Query(nativeQuery = true, value = "SELECT * FROM products WHERE status = 'ACTIVE' AND finished_at IS NOT NULL")
    List<Product> findAllActiveProducts();

    @Query(nativeQuery = true, value = "SELECT * FROM products WHERE status = 'ACTIVE' ORDER BY updated_at DESC")
    List<Product> findAllActiveProductsOrderByUpdatedAt();
}