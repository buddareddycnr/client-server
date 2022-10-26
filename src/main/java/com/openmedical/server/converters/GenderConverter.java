package com.openmedical.server.converters;

import com.openmedical.server.constants.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender,String> {
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender.getGender();
    }

    @Override
    public Gender convertToEntityAttribute(String gender) {
        return Gender.getGender(gender);
    }
}
