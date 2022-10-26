package com.openmedical.server.converters;

import com.openmedical.server.constants.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status,String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
        return status.getStatus();
    }

    @Override
    public Status convertToEntityAttribute(String status) {
        return Status.getStatus(status);
    }
}
