package com.users.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.users.model.User;
import com.users.repository.UserRepository;
import com.users.service.UserService;
import com.users.service.UserServiceImpl;
import com.users.web.dto.UserRegisterDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	private UserService userService;
	
	
	
	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegisterDto userRegistrationDto() {
		return new UserRegisterDto();
	}

	@GetMapping
	public String showRegistrationForm(){
		
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegisterDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
	
	
	
	

}
