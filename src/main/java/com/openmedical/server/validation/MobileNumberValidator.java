package com.openmedical.server.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MobileNumberValidator {


    public static boolean isValidMobileNumber(String mobileNumber,String mobileNumberFormat){
        Pattern compile = Pattern.compile(mobileNumberFormat);
        Matcher matcher = compile.matcher(mobileNumber);
        if(matcher.matches())
            return true;
        else
            return false;
    }
}
