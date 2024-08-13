package com.yeoboge.recommendation.core.common;

import com.yeoboge.recommendation.core.boardgame.Genre;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GenreConvertor implements AttributeConverter<Genre, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Genre attribute) {
        return attribute.getCode();
    }

    @Override
    public Genre convertToEntityAttribute(Integer dbData) {
        return Genre.ofCode(dbData);
    }
}
