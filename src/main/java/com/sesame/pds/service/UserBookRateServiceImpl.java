package com.sesame.pds.service;

import com.sesame.pds.dao.UserBookRateDao;
import com.sesame.pds.dto.BookDto;
import com.sesame.pds.dto.UserBookRateDto;
import com.sesame.pds.entity.UserBookRate;
import com.sesame.pds.transformer.UserBookRateTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author KHAZRI OMAR
 * @since 09/11/2022
 */
@Slf4j
@Service
public class UserBookRateServiceImpl implements UserBookRateService {
    private final UserBookRateTransformer userBookRateTransformer;
    private final UserBookRateDao userBookRateDao;
    private final UserService userService;
    private final BookService bookService;

    public UserBookRateServiceImpl(UserBookRateTransformer userBookRateTransformer, UserBookRateDao userBookRateDao, UserService userService, BookService bookService) {
        this.userBookRateTransformer = userBookRateTransformer;
        this.userBookRateDao = userBookRateDao;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public UserBookRateDao getDao() {
        return userBookRateDao;
    }

    @Override
    public UserBookRateTransformer getTransformer() {
        return userBookRateTransformer;
    }

    @Override
    @Transactional
    public UserBookRateDto create(UserBookRateDto dto) {
        log.info("UserBookRateService: create() called");
        dto.setUser(userService.findById(userService.getCurrentUser().getId()));
        BookDto bookDto = bookService.findById(dto.getBook().getId());
        BookDto updateBook = updateBookRate(bookDto, dto);
        dto.setBook(updateBook);
        return getTransformer().transformEntityToDto(getDao().create(getTransformer().transformDtoToEntity(dto)));
    }

    @Override
    @Transactional
    public UserBookRateDto update(UserBookRateDto dto, Long id) {
        log.info("UserBookRateService: update() called");
        Optional<UserBookRate> entity = getDao().findById(id);
        if (entity.isEmpty()) throw new EntityNotFoundException("User doesn't rate this book!");

        // Get the old rating to recalculate the average
        int oldRating = entity.get().getRate();

        getTransformer().updateEntity(dto, entity.get());
        BookDto bookDto = bookService.findById(dto.getBook().getId());
        BookDto updateBook = updateBookRateOnUpdate(bookDto, oldRating, dto.getRate());
        dto.setBook(updateBook);
        return getTransformer().transformEntityToDto(getDao().update(entity.get()));
    }

    @Override
    @Transactional
    public UserBookRateDto rateBook(UserBookRateDto userBookRateDto) {
        log.info("UserBookRateService: rateBook() called");

        // Get bookId from the book object
        if (userBookRateDto.getBook() == null || userBookRateDto.getBook().getId() == null) {
            throw new IllegalArgumentException("Book ID is required");
        }

        Long bookId = userBookRateDto.getBook().getId();

        // Load the complete book from database
        BookDto completeBook = bookService.findById(bookId);
        if (completeBook == null) {
            throw new EntityNotFoundException("Book not found with ID: " + bookId);
        }
        userBookRateDto.setBook(completeBook);

        // Set the current user
        userBookRateDto.setUser(userService.findById(userService.getCurrentUser().getId()));

        Long userId = userBookRateDto.getUser().getId();
        Optional<UserBookRate> userBookRate = getDao().findUserBookRateByUserIdAndBookId(userId, bookId);

        if (userBookRate.isPresent()) {
            userBookRateDto.setId(userBookRate.get().getId());
            return update(userBookRateDto, userBookRateDto.getId());
        }

        // Update book rating and save
        BookDto updateBook = updateBookRate(completeBook, userBookRateDto);
        userBookRateDto.setBook(updateBook);
        return getTransformer().transformEntityToDto(getDao().create(getTransformer().transformDtoToEntity(userBookRateDto)));
    }

    private BookDto updateBookRate(BookDto bookDto, UserBookRateDto userBookRateDto) {
        // Calculate the new average rating (for NEW rating)
        double currentTotal = bookDto.getRate() * bookDto.getUsersRateCount();
        long newCount = bookDto.getUsersRateCount() + 1;
        double newAverage = (currentTotal + userBookRateDto.getRate()) / newCount;

        bookDto.setUsersRateCount(newCount);
        bookDto.setRate(newAverage);
        return bookService.update(bookDto, bookDto.getId());
    }

    private BookDto updateBookRateOnUpdate(BookDto bookDto, int oldRating, int newRating) {
        // Calculate the new average rating (for EXISTING rating update)
        // Remove old rating and add new rating
        double currentTotal = bookDto.getRate() * bookDto.getUsersRateCount();
        currentTotal = currentTotal - oldRating + newRating;
        double newAverage = currentTotal / bookDto.getUsersRateCount();

        bookDto.setRate(newAverage);
        return bookService.update(bookDto, bookDto.getId());
    }

    @Override
    public List<UserBookRateDto> findAllRatingsByBookId(Long bookId) {
        log.info("UserBookRateService: findAllRatingsByBookId() called for book: {}", bookId);
        List<UserBookRate> ratings = getDao().findAllByBookIdAndMarkedAsDeletedFalse(bookId);
        return ratings.stream()
                .map(getTransformer()::transformEntityToDto)
                .collect(java.util.stream.Collectors.toList());
    }
}
