package com.king.photo_app_users.services;

import com.king.photo_app_users.models.CreateUserModel;
import com.king.photo_app_users.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDetails);
    List<CreateUserModel> findAll();

}
