
package com.openmedical.server.util;

import com.openmedical.entity.Address;
import com.openmedical.entity.Customer;
import com.openmedical.server.validation.FieldsValidator;
import org.springframework.stereotype.Component;

@Component
public class MandatoryFieldsLengthValidator<T> {
    public boolean isValidInput(T inputObject) {
        if (inputObject instanceof Customer) {
            var user = (Customer) inputObject;
            if (isValidStringLength(user.getFirstName()) && isValidStringLength(user.getMobileNumber())
                    && isValidStringLength(user.getEmailId()))
                return true;
            else
                return false;
        } else if (inputObject instanceof Address) {
            var address = (Address) inputObject;
            if (isValidStringLength(address.getCountry()) && isValidStringLength(address.getCity())
                    && isValidStringLength(address.getPostCode()))
                return true;
            else
                return false;
        }
        return false;
    }

    public boolean isValidStringLength(String input) {
        FieldsValidator<String> stringFieldsValidator = (data) -> {
            if (data.isBlank())
                return true;
            else
                return false;
        };
        return stringFieldsValidator.isValidLength(input);
    }

    public boolean isValidLong(long input) {
        FieldsValidator<Long> integerFieldsValidator = (data) -> {
            if (input != 0)
                return true;
            else
                return false;
        };
        return integerFieldsValidator.isValidLength(input);
    }
}

