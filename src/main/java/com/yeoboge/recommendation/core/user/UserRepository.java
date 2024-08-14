package com.yeoboge.recommendation.core.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN FETCH u.favoriteGenres WHERE u.id = :id")
    User findByIdWithFavoriteGenre(long id);
}
