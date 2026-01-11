package com.sesame.pds.controller;

import com.sesame.pds.controller.base.BaseController;
import com.sesame.pds.dto.AuthorDto;
import com.sesame.pds.dto.AuthorFilterPaginationRequest;
import com.sesame.pds.dto.base.pagination.FilterPaginationRequest;
import com.sesame.pds.dto.base.response.ApiResponse;
import com.sesame.pds.service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author KHAZRI OMAR
 * @since 07/11/2022
 */
@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/author")
public class AuthorController implements BaseController<AuthorService> {
    private final AuthorService authorService;

    @Override
    public AuthorService getService() {
        return authorService;
    }

    @GetMapping("/find-by-id/{authorId}")
    public ApiResponse findAuthorByAuthorId(@PathVariable Long authorId) {
        log.info("AuthorController: findAuthorByAuthorId() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Author fetched successfully.", getService().findById(authorId));
    }

    @PostMapping("/find-all-paginated-filtered")
    public ApiResponse findAllAuthorsPaginatedAndFiltered(@RequestBody FilterPaginationRequest<AuthorFilterPaginationRequest> authorFilterPaginationRequest) {
        log.info("AuthorController: findAllAuthorsPaginatedAndFiltered() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Authors paginated filtered fetched successfully.", getService().findAllAuthorsPaginatedAndFiltered(authorFilterPaginationRequest));
    }

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse createAuthor(@RequestBody AuthorDto authorDto) {
        log.info("AuthorController: createAuthor() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Author created successfully.", getService().create(authorDto));
    }

    @PostMapping("/create-list")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse createAuthors(@RequestBody List<AuthorDto> authorDtos) {
        log.info("AuthorController: createAuthors() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Authors created successfully.", getService().create(authorDtos));
    }

    @PutMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse updateAuthor(@RequestBody AuthorDto authorDto) {
        log.info("AuthorController: updateAuthor() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Author updated successfully.", getService().update(authorDto, authorDto.getId()));
    }
}
