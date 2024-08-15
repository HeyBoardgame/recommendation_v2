package com.yeoboge.recommendation.core.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.favoriteGenres WHERE u.id = :id")
    Optional<User> findByIdWithFavoriteGenre(long id);
}
