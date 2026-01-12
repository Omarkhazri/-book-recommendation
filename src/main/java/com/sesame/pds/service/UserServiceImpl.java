package com.sesame.pds.service;

import com.sesame.pds.dao.UserDao;
import com.sesame.pds.dto.UserDto;
import com.sesame.pds.dto.UserReadingInfoDto;
import com.sesame.pds.entity.User;
import com.sesame.pds.enums.UserGender;
import com.sesame.pds.enums.UserMartialStatus;
import com.sesame.pds.enums.UserReadingLevel;
import com.sesame.pds.manager.JWTAuthenticationManager;
import com.sesame.pds.transformer.UserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

/**
 * @author KHAZRI OMAR
 * @since 04/01/2025
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserTransformer userTransformer;
    private final UserDao userDao;
    private final JWTAuthenticationManager jwtAuthenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserReadingInfoService userReadingInfoService;

    public UserServiceImpl(UserTransformer userTransformer, UserDao userDao, JWTAuthenticationManager jwtAuthenticationManager, @Lazy PasswordEncoder passwordEncoder, @Lazy UserReadingInfoService userReadingInfoService) {
        this.userTransformer = userTransformer;
        this.userDao = userDao;
        this.jwtAuthenticationManager = jwtAuthenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userReadingInfoService = userReadingInfoService;
    }

    @Override
    public UserDao getDao() {
        return userDao;
    }

    @Override
    public UserTransformer getTransformer() {
        return userTransformer;
    }

    @Override
    public UserDto create(UserDto dto) {
        log.info("UserService: create() called");
        // check if email already exists
        if (getDao().isUserExistsByEmail(dto.getEmail()))
            throw new EntityExistsException("User email already exists - " + dto.getEmail());

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User transformedDtoToEntity = getTransformer().transformDtoToEntity(dto);
        UserDto createdUserDto = getTransformer().transformEntityToDto(getDao().create(transformedDtoToEntity));

        // Create default UserReadingInfo for the new user
        try {
            UserReadingInfoDto userReadingInfoDto = new UserReadingInfoDto();
            userReadingInfoDto.setReadingLevel(UserReadingLevel.BEGINNER);
            userReadingInfoDto.setUser(createdUserDto);
            userReadingInfoDto.setUserBookCategories(new ArrayList<>());
            userReadingInfoService.create(userReadingInfoDto);
            log.info("UserReadingInfo created successfully for user: {}", createdUserDto.getEmail());
        } catch (Exception e) {
            log.warn("Failed to create UserReadingInfo for user: {}", createdUserDto.getEmail(), e);
        }

        return createdUserDto;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        log.info("UserService: findUserByEmail() called");
        Optional<User> optionalUser = getDao().findUserByEmail(email);
        if (optionalUser.isEmpty())
            throw new EntityExistsException("User not exists for email: " + email);
        return getTransformer().transformEntityToDto(optionalUser.get());
    }

    @Override
    public List<UserGender> getUserGenders() {
        log.info("UserService: getUserGenders() called");
        return new ArrayList<>(EnumSet.allOf(UserGender.class));
    }

    @Override
    public List<UserMartialStatus> getUserMartialStatuses() {
        log.info("UserService: getUserMartialStatuses() called");
        return new ArrayList<>(EnumSet.allOf(UserMartialStatus.class));
    }

    @Override
    public UserDto getCurrentUser() {
        log.info("UserService: getCurrentUser() called");
        return findUserByEmail(jwtAuthenticationManager.getCurrentUserEmail());
    }

    @Override
    public Boolean isUserExistsByEmail(String email) {
        log.info("UserService: isUserExistsByEmail() called");
        return getDao().isUserExistsByEmail(email);
    }
}
