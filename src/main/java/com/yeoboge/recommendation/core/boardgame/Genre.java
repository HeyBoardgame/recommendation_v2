package com.yeoboge.recommendation.core.boardgame;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Genre {
    THEMATIC(1, "테마"),
    STRATEGY(2, "전략"),
    WAR(3, "전쟁"),
    FAMILY(4, "가족"),
    ABSTRACT(5, "단순"),
    PARTY(6, "파티"),
    CHILDREN(7, "키즈");

    private final int code;
    private final String name;

    public static Genre ofCode(int code) {
        return Arrays.stream(Genre.values())
                .filter(g -> g.getCode() == code)
                .findFirst()
                .orElseThrow();
    }
}
