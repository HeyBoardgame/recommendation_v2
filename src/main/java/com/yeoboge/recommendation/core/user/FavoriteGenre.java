package com.yeoboge.recommendation.core.user;

import com.yeoboge.recommendation.core.boardgame.Genre;
import com.yeoboge.recommendation.core.common.GenreConvertor;
import jakarta.persistence.*;

@Entity
@Table(name = "favorite_genre")
public class FavoriteGenre {
    @Id
    @Column(name = "favorite_genre_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Convert(converter = GenreConvertor.class)
    private Genre genreCode;
}
