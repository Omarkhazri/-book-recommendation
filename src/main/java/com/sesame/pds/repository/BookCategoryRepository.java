package com.sesame.pds.repository;

import com.sesame.pds.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    List<BookCategory> findAllByMarkedAsDeletedFalse();
}