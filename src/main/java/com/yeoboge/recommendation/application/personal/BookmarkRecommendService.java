package com.yeoboge.recommendation.application.personal;

import com.yeoboge.recommendation.application.personal.annotation.RecommendService;
import com.yeoboge.recommendation.core.boardgame.BoardGame;
import com.yeoboge.recommendation.core.boardgame.BoardGameRepository;
import com.yeoboge.recommendation.core.boardgame.dto.BoardGameThumbnailDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RecommendService
@RequiredArgsConstructor
public class BookmarkRecommendService {
    private final BoardGameRepository repository;

    public List<BoardGameThumbnailDto> getMostBookmarkedBoardGames() {
        List<BoardGame> boardGames = repository.findMostBookmarkedBoardGames();
        return BoardGameThumbnailDto.fromList(boardGames);
    }

    public List<BoardGameThumbnailDto> getUserBookmarkedBoardGames(long userId) {
        List<BoardGame> boardGames = repository.findUserBookmarkedBoardGames(userId);
        return BoardGameThumbnailDto.fromList(boardGames);
    }
}
