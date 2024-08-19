package com.yeoboge.recommendation.application.personal;

import com.yeoboge.recommendation.core.boardgame.BoardGameRepository;
import com.yeoboge.recommendation.core.boardgame.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFavoriteGenreService {
    private final BoardGameRepository repository;

    public List<Genre> getUserFavoriteGenreWithIds(long userId) {
        return repository.findUserFavoriteGenre(userId);
    }
}
