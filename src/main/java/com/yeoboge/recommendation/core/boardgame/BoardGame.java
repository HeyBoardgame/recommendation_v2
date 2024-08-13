package com.yeoboge.recommendation.core.boardgame;

import com.yeoboge.recommendation.core.bookmark.Bookmark;
import com.yeoboge.recommendation.core.common.GenreConvertor;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "board_game")
public class BoardGame {
    @Id
    @Column(name = "board_game_id")
    private Long id;
    private String name;
    @Convert(converter = GenreConvertor.class)
    @Column(name = "genre_code")
    private Genre genre;

    @OneToMany(mappedBy = "boardGame", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks;
}
