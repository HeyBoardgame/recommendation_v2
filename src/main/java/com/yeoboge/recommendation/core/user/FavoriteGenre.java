package com.yeoboge.recommendation.core.user;

import com.yeoboge.recommendation.core.boardgame.Genre;
import com.yeoboge.recommendation.core.common.GenreConvertor;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "favorite_genre")
public class FavoriteGenre {
    @Id
    @Column(name = "favorite_genre_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Convert(converter = GenreConvertor.class)
    @Column(name = "genre_code")
    private Genre genreCode;
}
