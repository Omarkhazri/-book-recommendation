package com.sesame.pds.repository;

import com.sesame.pds.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author KHAZRI OMAR
 * @since 07/11/2022
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthorIdAndMarkedAsDeletedFalse(Long authorId);

    @Query("SELECT b FROM Book b WHERE b.markedAsDeleted = :deletedRecords ")
    Page<Book> findAll(Pageable pageable, Boolean deletedRecords);

    List<Book> findAllByCategoryNameInAndMarkedAsDeletedFalse(List<String> categories);

    @Query("SELECT b FROM Book b WHERE b.markedAsDeleted = :deletedRecords")
    Page<Book> findAllBooksPaginatedAndFiltered(Boolean deletedRecords, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.markedAsDeleted = :deletedRecords " +
            "AND (CAST(:name AS string) IS NULL OR lower(b.name) like lower(concat('%', :name, '%'))) " +
            "AND (CAST(:fromPrice AS double) IS NULL OR b.price >= :fromPrice) " +
            "AND (CAST(:toPrice AS double) IS NULL OR b.price <= :toPrice) " +
            "AND (CAST(:fromPagesNumber AS int) IS NULL OR b.pagesNumber >= :fromPagesNumber) " +
            "AND (CAST(:toPagesNumber AS int) IS NULL OR b.pagesNumber <= :toPagesNumber) " +
            "AND (CAST(:fromReadingDuration AS int) IS NULL OR b.readingDuration >= :fromReadingDuration) " +
            "AND (CAST(:toReadingDuration AS int) IS NULL OR b.readingDuration <= :toReadingDuration) " +
            "AND (b.category.id IN (:categoryIds)) ")
    Page<Book> findAllBooksPaginatedAndFilteredWithCategories(String name, Set<Long> categoryIds, Double fromPrice, Double toPrice, Integer fromPagesNumber, Integer toPagesNumber, Integer fromReadingDuration, Integer toReadingDuration, Boolean deletedRecords, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.markedAsDeleted = :deletedRecords " +
            "AND (CAST(:name AS string) IS NULL OR lower(b.name) like lower(concat('%', :name, '%'))) " +
            "AND (CAST(:fromPrice AS double) IS NULL OR b.price >= :fromPrice) " +
            "AND (CAST(:toPrice AS double) IS NULL OR b.price <= :toPrice) " +
            "AND (CAST(:fromPagesNumber AS int) IS NULL OR b.pagesNumber >= :fromPagesNumber) " +
            "AND (CAST(:toPagesNumber AS int) IS NULL OR b.pagesNumber <= :toPagesNumber) " +
            "AND (CAST(:fromReadingDuration AS int) IS NULL OR b.readingDuration >= :fromReadingDuration) " +
            "AND (CAST(:toReadingDuration AS int) IS NULL OR b.readingDuration <= :toReadingDuration) ")
    Page<Book> findAllBooksPaginatedAndFilteredWithoutCategories(String name, Double fromPrice, Double toPrice, Integer fromPagesNumber, Integer toPagesNumber, Integer fromReadingDuration, Integer toReadingDuration, Boolean deletedRecords, Pageable pageable);
}