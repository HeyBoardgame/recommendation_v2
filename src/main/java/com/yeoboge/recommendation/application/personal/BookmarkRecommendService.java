package com.yeoboge.recommendation.application.personal;

import com.yeoboge.recommendation.application.personal.annotation.Recommender;
import com.yeoboge.recommendation.core.boardgame.BoardGame;
import com.yeoboge.recommendation.core.boardgame.BoardGameRepository;
import com.yeoboge.recommendation.core.boardgame.dto.BoardGameThumbnailDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Recommender
@RequiredArgsConstructor
public class BookmarkRecommendService implements RecommendService {
    public static final String CATEGORY = "많은 북마크를 받은 보드게임 👍";
    private final BoardGameRepository repository;

    @Override
    public String getCategory(RecommendationContext context) {
        return CATEGORY;
    }

    @Override
    public List<BoardGameThumbnailDto> getRecommendations(RecommendationContext context) {
        List<BoardGame> boardGames = repository.findMostBookmarkedBoardGames();
        return BoardGameThumbnailDto.fromList(boardGames);
    }
}
