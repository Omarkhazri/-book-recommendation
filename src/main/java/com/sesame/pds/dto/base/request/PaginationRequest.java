package com.sesame.pds.dto.base.request;

import com.sesame.pds.dto.base.pagination.Page;
import com.sesame.pds.dto.base.pagination.SortingBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author KHAZRI OMAR
 * @since 03/11/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationRequest extends Page {
    private List<SortingBy> sortingByList;
}
