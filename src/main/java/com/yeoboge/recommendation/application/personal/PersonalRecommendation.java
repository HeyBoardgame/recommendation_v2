package com.yeoboge.recommendation.application.personal;

import com.yeoboge.recommendation.core.boardgame.dto.BoardGameThumbnailDto;

import java.util.List;
import java.util.Map;

public record PersonalRecommendation(Map<String, List<BoardGameThumbnailDto>> boardGames) {
}
