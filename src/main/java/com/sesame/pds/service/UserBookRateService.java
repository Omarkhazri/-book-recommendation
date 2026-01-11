package com.sesame.pds.service;

import com.sesame.pds.dao.UserBookRateDao;
import com.sesame.pds.dto.UserBookRateDto;
import com.sesame.pds.entity.UserBookRate;
import com.sesame.pds.service.base.BaseService;
import com.sesame.pds.transformer.UserBookRateTransformer;

import java.util.List;

/**
 * @author KHAZRI OMAR
 * @since 09/11/2022
 */
public interface UserBookRateService extends BaseService<UserBookRate, UserBookRateDto, UserBookRateDao, UserBookRateTransformer> {
    UserBookRateDto rateBook(UserBookRateDto userBookRateDto);

    List<UserBookRateDto> findAllRatingsByBookId(Long bookId);
}
