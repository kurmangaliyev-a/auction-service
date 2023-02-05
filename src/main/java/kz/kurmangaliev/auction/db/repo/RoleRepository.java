package kz.kurmangaliev.auction.db.repo;

import kz.kurmangaliev.auction.db.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}