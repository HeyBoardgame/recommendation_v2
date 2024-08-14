package com.yeoboge.recommendation.core.boardgame.dto;

import com.yeoboge.recommendation.core.boardgame.BoardGame;

public record BoardGameThumbnailDto(String name, String genre) {
    public static BoardGameThumbnailDto from(BoardGame boardGame) {
        return new BoardGameThumbnailDto(boardGame.getName(), boardGame.getGenre().getName());
    }
}
