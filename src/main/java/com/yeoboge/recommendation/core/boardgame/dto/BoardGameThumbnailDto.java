package com.yeoboge.recommendation.core.boardgame.dto;

import com.yeoboge.recommendation.core.boardgame.BoardGame;

import java.util.List;

public record BoardGameThumbnailDto(String name, String genre) {
    public static List<BoardGameThumbnailDto> fromList(List<BoardGame> boardGames) {
        return boardGames.stream()
                .map(BoardGameThumbnailDto::from)
                .toList();
    }

    public static BoardGameThumbnailDto from(BoardGame boardGame) {
        return new BoardGameThumbnailDto(boardGame.getName(), boardGame.getGenre().getName());
    }
}
