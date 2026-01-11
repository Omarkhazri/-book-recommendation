package com.sesame.pds.transformer;

import com.sesame.pds.dto.AuthorDto;
import com.sesame.pds.entity.Author;
import com.sesame.pds.transformer.base.BaseTransformer;
import com.sesame.pds.transformer.mapper.AuthorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author KHAZRI OMAR
 * @since 07/11/2022
 */
@Component
@AllArgsConstructor
public class AuthorTransformer implements BaseTransformer<Author, AuthorDto, AuthorMapper> {
    private final AuthorMapper authorMapper;

    @Override
    public AuthorMapper getMapper() {
        return authorMapper;
    }
}
