package kz.kurmangaliev.auction.db.repo;

import kz.kurmangaliev.auction.db.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotoRepository extends JpaRepository<Photo, UUID> {
}