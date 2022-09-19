package com.users.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.users.model.User;
import com.users.web.dto.UserRegisterDto;

public interface UserService extends UserDetailsService{
	User save(UserRegisterDto registratioDto);
	

}
