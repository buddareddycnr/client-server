package com.openmedical.server.converters;

import com.openmedical.server.constants.Title;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TitleConverter implements AttributeConverter<Title,String> {
    @Override
    public String convertToDatabaseColumn(Title title) {
        return title.getTitle();
    }

    @Override
    public Title convertToEntityAttribute(String title) {
        return Title.getTitle(title);
    }
}
