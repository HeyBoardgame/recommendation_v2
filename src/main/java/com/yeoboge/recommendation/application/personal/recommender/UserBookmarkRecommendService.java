package com.yeoboge.recommendation.application.personal.recommender;

import com.yeoboge.recommendation.application.personal.annotation.Recommender;
import com.yeoboge.recommendation.application.personal.dto.RecommendationContextDto;
import com.yeoboge.recommendation.core.boardgame.BoardGame;
import com.yeoboge.recommendation.core.boardgame.BoardGameRepository;
import com.yeoboge.recommendation.core.boardgame.dto.BoardGameThumbnailDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Recommender
@RequiredArgsConstructor
public class UserBookmarkRecommendService implements RecommendService {
    public static final String CATEGORY = "내가 북마크한 보드게임 🔖";
    private final BoardGameRepository repository;

    @Override
    public String getCategory(RecommendationContextDto context) {
        return CATEGORY;
    }

    @Override
    public List<BoardGameThumbnailDto> getRecommendations(RecommendationContextDto context) {
        List<BoardGame> boardGames = repository.findUserBookmarkedBoardGames(context.userId());
        return BoardGameThumbnailDto.fromList(boardGames);
    }
}
