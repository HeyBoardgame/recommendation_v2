package com.yeoboge.recommendation.core.boardgame;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static com.yeoboge.recommendation.core.boardgame.QBoardGame.boardGame;
import static com.yeoboge.recommendation.core.bookmark.QBookmark.bookmark;
import static com.yeoboge.recommendation.core.user.QFavoriteGenre.favoriteGenre;

@Repository
@RequiredArgsConstructor
public class BoardGameCustomRepositoryImpl implements BoardGameCustomRepository {
    public static final int NUM_FAVORITE_GENRE = 3;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<BoardGame> findMostBookmarkedBoardGames() {
        List<BoardGame> result = queryFactory.select(boardGame)
                .from(boardGame)
                .join(bookmark)
                .on(boardGame.id.eq(bookmark.boardGame.id))
                .groupBy(boardGame.id)
                .orderBy(bookmark.id.count().desc())
                .limit(NUM_RECOMMENDED_BOARD_GAMES)
                .fetch();
        return result.size() == NUM_RECOMMENDED_BOARD_GAMES ? result : Collections.emptyList();
    }

    @Override
    public List<BoardGame> findUserBookmarkedBoardGames(long userId) {
        List<BoardGame> result = queryFactory.select(boardGame)
                .from(boardGame)
                .join(bookmark)
                .on(boardGame.id.eq(bookmark.boardGame.id))
                .where(bookmark.user.id.eq(userId))
                .orderBy(bookmark.createdAt.desc())
                .limit(NUM_RECOMMENDED_BOARD_GAMES)
                .fetch();
        return result.size() == NUM_RECOMMENDED_BOARD_GAMES ? result : Collections.emptyList();
    }

    @Override
    public List<Genre> findUserFavoriteGenre(long userId) {
        return queryFactory.select(boardGame.genre)
                .from(boardGame)
                .join(favoriteGenre)
                .on(boardGame.genre.eq(favoriteGenre.genreCode))
                .where(favoriteGenre.user.id.eq(userId))
                .groupBy(boardGame.genre)
                .orderBy(boardGame.id.count().desc())
                .limit(NUM_FAVORITE_GENRE)
                .fetch();
    }
}
