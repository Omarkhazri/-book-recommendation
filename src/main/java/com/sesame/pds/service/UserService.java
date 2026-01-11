package com.sesame.pds.service;


import com.sesame.pds.dao.UserDao;
import com.sesame.pds.dto.UserDto;
import com.sesame.pds.entity.User;
import com.sesame.pds.enums.UserGender;
import com.sesame.pds.enums.UserMartialStatus;
import com.sesame.pds.service.base.BaseService;
import com.sesame.pds.transformer.UserTransformer;

import java.util.List;

/**
 * @author KHAZRI OMAR
 * @since 04/01/2025
 */
public interface UserService extends BaseService<User, UserDto, UserDao, UserTransformer> {
    UserDto findUserByEmail(String email);

    List<UserGender> getUserGenders();

    List<UserMartialStatus> getUserMartialStatuses();

    UserDto getCurrentUser();

    Boolean isUserExistsByEmail(String email);
}
