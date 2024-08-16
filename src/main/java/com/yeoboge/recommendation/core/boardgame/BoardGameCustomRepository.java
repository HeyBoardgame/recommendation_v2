package com.yeoboge.recommendation.core.boardgame;

import java.util.List;

public interface BoardGameCustomRepository {
    int NUM_RECOMMENDED_BOARD_GAMES = 10;

    List<BoardGame> findMostBookmarkedBoardGames();

    List<BoardGame> findUserBookmarkedBoardGames(long userId);
}
