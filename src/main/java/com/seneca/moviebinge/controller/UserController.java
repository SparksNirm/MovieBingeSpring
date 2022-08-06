package com.seneca.moviebinge.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seneca.moviebinge.exception.InValidRequest;
import com.seneca.moviebinge.exception.NotAValidEmail;
import com.seneca.moviebinge.model.MovieBingeResponse;
import com.seneca.moviebinge.model.User;
import com.seneca.moviebinge.service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins={"http://localhost:3000","https://moviebinge.herokuapp.com/"})
public class UserController {
	
	@Autowired
	public UserService userService;
	
	
	@GetMapping("/users")
	public ResponseEntity<MovieBingeResponse<User>> getAllUsers(){
		MovieBingeResponse<User> response=null;
		ResponseEntity<MovieBingeResponse<User>> responseEntity=null;
		List<User> users= new ArrayList<User>();
		users = userService.getAllUsers();
		response=new MovieBingeResponse<User>("All Users Retrieved",users);
		responseEntity=new ResponseEntity<MovieBingeResponse<User>>(response,HttpStatus.OK);
		return responseEntity;
	}
	
	/*
	 * @GetMapping("/user") public ResponseEntity<MovieBingeResponse<User>>
	 * getUserByUsername(@RequestParam String username){ MovieBingeResponse<User>
	 * response=null; ResponseEntity<MovieBingeResponse<User>> responseEntity=null;
	 * List<User> users=new ArrayList<>(); User user= new User(); try {
	 * user=userService.getUserByUsername(username); users.add(user); response=new
	 * MovieBingeResponse<>("User Retrieved Successfully",users); responseEntity=new
	 * ResponseEntity<MovieBingeResponse<User>>(response,HttpStatus.OK); } catch
	 * (Exception e) { response=new MovieBingeResponse<>(e.getMessage(),null);
	 * responseEntity=new
	 * ResponseEntity<MovieBingeResponse<User>>(response,HttpStatus.NOT_FOUND); }
	 * 
	 * return responseEntity; }
	 */
	
	@GetMapping("/users/{id}")
	public ResponseEntity<MovieBingeResponse<User>> getUserById(@PathVariable Long id	){
		MovieBingeResponse<User> response=null;
		ResponseEntity<MovieBingeResponse<User>> responseEntity=null;
		User user= new User();
		try {
			user=userService.getUserById(id);
			response=new MovieBingeResponse<>("User Retrieved Successfully",Collections.singletonList(user));
			responseEntity=new ResponseEntity<MovieBingeResponse<User>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response=new MovieBingeResponse<>(e.getMessage(),null);
			responseEntity=new ResponseEntity<MovieBingeResponse<User>>(response,HttpStatus.NOT_FOUND);
		}
		
		return responseEntity;
	}
	
	@PostMapping("/users")
	public ResponseEntity<MovieBingeResponse<User>> creatUser(@RequestBody User user){
		MovieBingeResponse<User> response=null;
		ResponseEntity<MovieBingeResponse<User>> responseEntity =null;
		User newUser=null;
		List<User> users =new ArrayList<User>();
		try {
			newUser=userService.createUser(user);
			users.add(newUser);
			response=new MovieBingeResponse<>("User Created Successfully with Id: "+newUser.getId(),users);
			responseEntity=new ResponseEntity<>(response,HttpStatus.OK);
		}
		 catch (InValidRequest e) {
				
				response=new MovieBingeResponse<>(e.getMessage(),null);
				responseEntity=new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			}
		 catch (NotAValidEmail e) {
				
				response=new MovieBingeResponse<>(e.getMessage(),null);
				responseEntity=new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			}
		catch (Exception e) {
			response=new MovieBingeResponse<>(e.getMessage(),null);
			responseEntity=new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		return responseEntity;
	}
	
	/*
	 * @PostMapping("/validateuser") public ResponseEntity<MovieBingeResponse<User>>
	 * validateUser(@RequestBody AuthUser user){ MovieBingeResponse<User>
	 * response=null; ResponseEntity<MovieBingeResponse<User>> responseEntity =null;
	 * Boolean result; try { result = userService.authenticateUser(user); if(result)
	 * { response=new MovieBingeResponse<>("User Authentication Successfull",null);
	 * responseEntity=new ResponseEntity<>(response,HttpStatus.OK); }else {
	 * response=new MovieBingeResponse<>
	 * ("User Authentication Failed. Check Username and Password",null);
	 * responseEntity=new ResponseEntity<>(response,HttpStatus.NOT_FOUND); } } catch
	 * (Exception e) { response=new MovieBingeResponse<>(e.getMessage(),null);
	 * responseEntity=new ResponseEntity<>(response,HttpStatus.NOT_FOUND); }
	 * 
	 * return responseEntity; }
	 */
	
	@GetMapping("/")
	public ResponseEntity<String> hello(){
		ResponseEntity<String> responseEntity=null;
		responseEntity=new ResponseEntity<>("Hello World!!!",HttpStatus.OK);
		return responseEntity;
		
	}
}
