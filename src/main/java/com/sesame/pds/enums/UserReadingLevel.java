package com.sesame.pds.enums;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
public enum UserReadingLevel {
    BEGINNER("BEGINNER"),
    INTERMEDIATE("INTERMEDIATE"),
    EXPERT("EXPERT"),
    ADVANCED("ADVANCED");

    private final String level;

    UserReadingLevel(String level) {
        this.level = level;
    }
}
