package kz.kurmangaliev.auction.db.repo;

import kz.kurmangaliev.auction.db.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PhotoRepository extends JpaRepository<Photo, UUID> {
    @Query(nativeQuery = true, value = "SELECT * FROM photos WHERE product_id = :productId AND deleted_at IS NULL")
    List<Photo> findAllByProductId(Long productId);
}