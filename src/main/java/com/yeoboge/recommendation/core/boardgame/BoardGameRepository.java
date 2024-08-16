package com.yeoboge.recommendation.core.boardgame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long>, BoardGameCustomRepository {
    List<BoardGame> findBoardGamesByIdIsIn(List<Long> ids);
}
