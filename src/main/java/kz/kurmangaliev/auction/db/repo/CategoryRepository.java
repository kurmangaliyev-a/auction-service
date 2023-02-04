package kz.kurmangaliev.auction.db.repo;

import kz.kurmangaliev.auction.db.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}