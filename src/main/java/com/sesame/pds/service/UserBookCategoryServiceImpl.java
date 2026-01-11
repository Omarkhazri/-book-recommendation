package com.sesame.pds.service;

import com.sesame.pds.dao.UserBookCategoryDao;
import com.sesame.pds.dto.UserBookCategoryDto;
import com.sesame.pds.transformer.UserBookCategoryTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Slf4j
@Service
public class UserBookCategoryServiceImpl implements UserBookCategoryService {
    private final UserBookCategoryDao userBookCategoryDao;
    private final UserBookCategoryTransformer userBookCategoryTransformer;
    private final UserService userService;
    private final BookCategoryService bookCategoryService;

    public UserBookCategoryServiceImpl(UserBookCategoryDao userBookCategoryDao, UserBookCategoryTransformer userBookCategoryTransformer, UserService userService, BookCategoryService bookCategoryService) {
        this.userBookCategoryDao = userBookCategoryDao;
        this.userBookCategoryTransformer = userBookCategoryTransformer;
        this.userService = userService;
        this.bookCategoryService = bookCategoryService;
    }

    @Override
    public UserBookCategoryDao getDao() {
        return userBookCategoryDao;
    }

    @Override
    public UserBookCategoryTransformer getTransformer() {
        return userBookCategoryTransformer;
    }

    @Override
    public UserBookCategoryDto create(UserBookCategoryDto dto) {
        log.info("UserBookCategoryService: create() called");
        dto.setUser(userService.getCurrentUser());
        dto.setCategory(bookCategoryService.findById(dto.getCategory().getId()));
        return getTransformer().transformEntityToDto(getDao().create(getTransformer().transformDtoToEntity(dto)));
    }

    @Override
    public List<UserBookCategoryDto> findAllUserBookCategories() {
        log.info("UserBookCategoryService: findAllUserBookCategories() called");
        return getTransformer().transformEntityToDto(getDao().findAllByUserId(userService.getCurrentUser().getId()));
    }
}
