package com.yeoboge.recommendation.core;

import com.yeoboge.recommendation.core.user.User;
import com.yeoboge.recommendation.core.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    @DisplayName("사용자 단일 조회 테스트")
    public void select_user_by_id() {
        // given
        long userId = 1;

        // when
        Optional<User> user = repository.findById(userId);

        // then
        assert user.isPresent();
    }

    @Test
    @DisplayName("사용자 선호 장르 Join 조회 테스트")
    public void select_user_by_id_with_favorite_genre() {
        // given
        long userId = 1L;

        // when
        Optional<User> user = repository.findByIdWithFavoriteGenre(userId);

        // then
        assertTrue(user.isPresent());
        assertFalse(user.get().getFavoriteGenres().isEmpty());
    }
}
