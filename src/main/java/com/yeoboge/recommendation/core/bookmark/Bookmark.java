package com.yeoboge.recommendation.core.bookmark;

import com.yeoboge.recommendation.core.boardgame.BoardGame;
import com.yeoboge.recommendation.core.user.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Bookmark {
    @Id
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_game_id")
    private BoardGame boardGame;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
