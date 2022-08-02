package com.seneca.moviebinge.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seneca.moviebinge.exception.InValidRequest;
import com.seneca.moviebinge.exception.NotAValidEmail;
import com.seneca.moviebinge.model.User;
import com.seneca.moviebinge.repo.UserRepository;


@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public SequenceGeneratorService sequenceGeneratorService;
	
	public List<User> getAllUsers(){
		
		List<User> users=  userRepository.findAll();
		
		return users;
	}
	
	public User createUser(User user) throws Exception{	
		
		User newUser=null;
		user.setId(sequenceGeneratorService.generateSequenceUser(User.SEQUENCE_NAME));
		System.out.println(user);
		if(user.getUserName()==null || user.getUserName()=="")
		{
			throw new InValidRequest("Invalid Request");
		}
		if(user.getEmailAddress()==null || user.getEmailAddress()=="")
		{
			throw new InValidRequest("Invalid Request");
		}
		
		if(user.getFirstName()==null || user.getFirstName()=="")
		{
			throw new InValidRequest("Invalid Request");
		}
		if(user.getLastName()==null || user.getLastName()=="")
		{
			throw new InValidRequest("Invalid Request");
		}
		if(user.getEmailAddress().matches("^(.+)@(.+)$")) {
			if(isUniqueUserName(user.getUserName()) && isUniqueEmail(user.getEmailAddress()))
			{
				String encodedPassoword=passwordEncoder.encode(user.getPassword());
				user.setPassword(encodedPassoword);
				newUser=userRepository.save(user);
			}else
			{
				throw new Exception("Username or Email Already exists");
			}
		}
		else
		{
			throw new NotAValidEmail("Not a Valid Email Format");
		}
		
		return newUser;
	}
	
	/*
	 * public User getUserByUsername(String userName) throws Exception { User
	 * user=null; if(null!=userName) { user=
	 * userRepository.findByUserName(userName).get();
	 * 
	 * }else { throw new Exception("No User Found with username: "+userName); }
	 * return user; }
	 */
	
	public User getUserById(Long id) throws Exception
	{
		User user=null;
		if(null!=id) {
			user= userRepository.findById(id).get();
			
		}else
		{
			throw new Exception("No User Found with username: "+id);
		}
		return user;
	}
	
	private Boolean isUniqueUserName(String userName) {
		User user= userRepository.findByUserName(userName).get();
		Boolean isUnique=true;
		if(user!=null)
		{
			isUnique=false;
		}
		return isUnique;
	}
	
	private Boolean isUniqueEmail(String email) {
		User user= userRepository.findByEmailAddress(email);
		Boolean isUnique=true;
		if(user!=null)
		{
			isUnique=false;
		}
		return isUnique;
	}
	
	/*
	 * public Boolean authenticateUser(AuthUser user) throws Exception { Boolean
	 * result=null; Optional<User>
	 * userDb=userRepository.findByUserName(user.getUsername());
	 * if(userDb.isPresent()) { User authuser=userDb.get();
	 * result=passwordEncoder.matches(user.getPassword(), authuser.getPassword());
	 * 
	 * }else { throw new Exception("No User Found"); }
	 * 
	 * return result;
	 * 
	 * 
	 * }
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User foundUser = userRepository.findByUserName(username).get();
		
		String userN=foundUser.getUserName();
		String password = foundUser.getPassword();
		return new org.springframework.security.core.userdetails.User(userN, password, new ArrayList<>());
	}
	
	

}
