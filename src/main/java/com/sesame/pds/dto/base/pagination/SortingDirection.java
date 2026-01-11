package com.sesame.pds.dto.base.pagination;

/**
 * @author KHAZRI OMAR
 * @since 03/11/2022
 */
public enum SortingDirection {
    ASC("ASC"),
    DESC("DESC");

    private final String value;

    SortingDirection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
