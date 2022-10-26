package com.openmedical.server.constants;

public enum Gender {
    MALE("Male"),FEMALE("Female"),TRANSGENDER("Transgender"),NOTINTERESTEDDISCLOSE("NotInterestedDisclose");
    private String gender;
    private Gender(String gender){
        this.gender = gender;
    }
    public static Gender getGender(String gender){
        return Gender.valueOf(gender);
    }

    public String getGender() {
        return gender;
    }
}
