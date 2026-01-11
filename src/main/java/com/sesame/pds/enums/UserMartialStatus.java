package com.sesame.pds.enums;

/**
 * @author KHAZRI OMAR
 * @since 04/01/2025
 */
public enum UserMartialStatus {
    SINGLE("SINGLE"), MARRIED("MARRIED"), IN_RELATIONSHIP("IN_RELATIONSHIP");

    private final String martialStatus;

    UserMartialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
    }
}
