package com.sesame.pds.enums;

/**
 * @author KHAZRI OMAR
 * @since 04/01/2025
 */
public enum UserGender {
    MALE("MALE"), FEMALE("FEMALE"), OTHERS("OTHERS");

    private final String gender;

    UserGender(String gender) {
        this.gender = gender;
    }
}
