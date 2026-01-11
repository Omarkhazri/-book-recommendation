package com.sesame.pds.dto.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author KHAZRI OMAR
 * @since 03/11/2022
 */
@Data
@AllArgsConstructor
public class ApiResponse {
    private Boolean success;
    private String timestamp;
    private String message;
    private Object body;
}
