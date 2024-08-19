package com.yeoboge.recommendation.application.personal;

import com.yeoboge.recommendation.core.boardgame.dto.BoardGameThumbnailDto;

import java.util.List;

public interface RecommendService {
    String getCategory(RecommendationContext context);

    List<BoardGameThumbnailDto> getRecommendations(RecommendationContext context);
}
