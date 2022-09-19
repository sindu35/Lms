package com.users.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.users.model.Roles;
import com.users.model.User;
import com.users.repository.UserRepository;
import com.users.web.dto.UserRegisterDto;

@Service
public class UserServiceImpl  implements UserService {
	
	
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	public UserServiceImpl(UserRepository userRepository) {
		super();
		
		this.userRepository = userRepository;
	}


	@Override
	public User save(UserRegisterDto registratioDto) {
		User user=new User(registratioDto.getFirstname(),
				registratioDto.getLastname(),registratioDto.getEmail(),
				passwordEncoder.encode(registratioDto.getPassword()),Arrays.asList(new Roles("ROLE_USER")));
		
		return userRepository.save(user);
		
			}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByEmail(username);
		if(user == null)
		{
			throw new UsernameNotFoundException("Invalid UserName or Password");
		}
		return  new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<?extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles){
	return 	roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
	}





	
	
	

}
