package com.yeoboge.recommendation.application.personal.recommender;

import com.yeoboge.recommendation.application.personal.dto.RecommendationContextDto;
import com.yeoboge.recommendation.core.boardgame.dto.BoardGameThumbnailDto;

import java.util.List;

public interface RecommendService {
    String getCategory(RecommendationContextDto context);

    List<BoardGameThumbnailDto> getRecommendations(RecommendationContextDto context);
}
