package com.yeoboge.recommendation.application.personal.dto;

import com.yeoboge.recommendation.core.boardgame.dto.BoardGameThumbnailDto;

import java.util.List;
import java.util.Map;

public record PersonalRecommendationDto(Map<String, List<BoardGameThumbnailDto>> boardGames) {
}
