package com.openmedical.server.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    public static boolean isEmailIdValid(String emailId,String pattern){
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(emailId);
        if(matcher.matches())
            return true;
        else
            return false;
    }
}
