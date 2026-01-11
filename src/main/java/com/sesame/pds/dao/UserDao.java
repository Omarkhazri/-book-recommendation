package com.sesame.pds.dao;

import com.sesame.pds.dao.base.BaseDao;
import com.sesame.pds.entity.User;
import com.sesame.pds.repository.UserRepository;

import java.util.Optional;

/**
 * @author KHAZRI OMAR
 * @since 04/01/2025
 */
public interface UserDao extends BaseDao<User, UserRepository> {
    Optional<User> findUserByEmail(String email);

    Boolean isUserExistsByEmail(String email);
}
