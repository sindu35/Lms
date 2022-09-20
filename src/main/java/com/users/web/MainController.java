package com.users.web;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.users.helper.Message;
import com.users.model.User;
import com.users.repository.UserRepository;

@Controller
public class MainController {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	

	//user home page where books are shown
	@GetMapping("/")
	public String userhome(Model model) {
		 model.addAttribute("Books", "books");
		return "index";
	}
	//user profile page
	@GetMapping("/profile")
    public String viewUserAccountForm(Model model,Principal principal) {
        
      
         User user=userRepo.findByEmail(principal.getName());
        model.addAttribute("user", user);
       
         
        return "profile";
    }
	//open settings
	@GetMapping("/settings")
	public String settings() {
		return "changepassword";
	}
	//changing password
	@PostMapping("/change_password")
	public String changePassword(@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword,Principal principal,HttpSession session) {
		
		User currentUser=userRepo.findByEmail(principal.getName());
		
		if(this.bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
			session.setAttribute("message",new Message("your password is successfully changed","success")); 
		
			currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
			this.userRepo.save(currentUser);
		
		}else
		{
			session.setAttribute("message",new Message("your old password is wrong","danger")); 
			return "redirect:/settings";
		}
			
		
		return "redirect:/";
	}
	//books issued to user
	
}
