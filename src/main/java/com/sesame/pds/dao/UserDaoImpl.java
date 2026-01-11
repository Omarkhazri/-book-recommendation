package com.sesame.pds.dao;

import com.sesame.pds.entity.User;
import com.sesame.pds.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author KHAZRI OMAR
 * @since 04/01/2025
 */
@Component
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return getRepository().findByEmail(email);
    }

    @Override
    public Boolean isUserExistsByEmail(String email) {
        return getRepository().existsByEmail(email);
    }
}
