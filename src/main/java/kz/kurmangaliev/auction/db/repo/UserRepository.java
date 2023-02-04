package kz.kurmangaliev.auction.db.repo;

import kz.kurmangaliev.auction.db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}