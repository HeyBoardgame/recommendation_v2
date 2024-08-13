package com.yeoboge.recommendation.core.user;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;
    private String nickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteGenre> favoriteGenres;
}
