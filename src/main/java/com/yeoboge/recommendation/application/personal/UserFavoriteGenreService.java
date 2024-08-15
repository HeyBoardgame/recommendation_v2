package com.yeoboge.recommendation.application.personal;

import com.yeoboge.recommendation.core.user.FavoriteGenre;
import com.yeoboge.recommendation.core.user.User;
import com.yeoboge.recommendation.core.user.UserRepository;
import com.yeoboge.recommendation.core.user.dto.FavoriteGenreIdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFavoriteGenreService {
    private final UserRepository repository;

    public List<FavoriteGenreIdDto> getUserFavoriteGenreWithIds(long userId) {
        User user = repository.findByIdWithFavoriteGenre(userId).get();
        List<FavoriteGenre> favoriteGenres = user.getFavoriteGenres();
        return favoriteGenres.stream()
                .map(o -> new FavoriteGenreIdDto(user.getId(), o.getGenreCode().getCode()))
                .toList();
    }
}
