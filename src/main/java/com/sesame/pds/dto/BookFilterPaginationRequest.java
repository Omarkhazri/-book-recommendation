package com.sesame.pds.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author KHAZRI OMAR
 * @since 08/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookFilterPaginationRequest {
    private String name;
    private List<Long> categoryIds;  // Changed to List for JSON serialization
    private Double fromPrice;
    private Double toPrice;
    private Integer fromPagesNumber;
    private Integer toPagesNumber;
    private Integer fromReadingDuration;
    private Integer toReadingDuration;

    public Set<Long> getCategoryIdsAsSet() {
        if (categoryIds == null || categoryIds.isEmpty()) return null;
        return categoryIds.stream().collect(Collectors.toSet());
    }
}
