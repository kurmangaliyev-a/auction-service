package kz.kurmangaliev.auction.db.repo;

import kz.kurmangaliev.auction.db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}