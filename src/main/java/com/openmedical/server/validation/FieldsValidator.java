package com.openmedical.server.validation;

@FunctionalInterface
public interface FieldsValidator<T> {
    boolean isValidLength(T field);
}
