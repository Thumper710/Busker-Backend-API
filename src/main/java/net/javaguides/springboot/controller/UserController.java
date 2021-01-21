package net.javaguides.springboot.controller;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.webUtils.WebUtils;

@CrossOrigin									 	//-Puts spring on same port as react
@RestController										//-Creates rest controller	
@RequestMapping("/api/v1/")				 			//-Sets request mapping
public class UserController {
	
	@Autowired
	private UserRepository userRepository;	
	//-Autowire allows us to write userRepository once and use throughout
	@Autowired
	private WebUtils webUtils;
	
	@GetMapping("users")	 						 //This code creates a simple rest endpoint that 
	public List<User> getUsers(){					 //returns a list of all users in the @Autowired userRepository
		return this.userRepository.findAll();		 //object, created from UserRepository class
	}
	

	@PostMapping("login")
	public ResponseEntity<User> logIn(@RequestBody User user){
		System.out.println(user.toString());
		Optional<User> userAccount = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if(userAccount.isEmpty()) {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<User>(userAccount.get(), HttpStatus.OK);

	} 
	
	@PostMapping("searchUser")
	public ResponseEntity<List<User>> searchUser(@RequestParam String searchQuery){
		return new ResponseEntity<List<User>>(userRepository.searchForUsers(searchQuery), HttpStatus.OK);
		
	}
	
	@PutMapping("editUser/{id}")
	public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User userDetails){
		User user = userRepository.findById(id).get();
		
		
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setDescription(userDetails.getDescription());
		user.setEmail(userDetails.getEmail());

		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	
		
	}
	
	
	@RequestMapping(value="user", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<User> setUser(User user){
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		if(existingUser.isEmpty()) {
			System.out.println(":::> USER ::> "+user.toString() );
			User savedUser = this.userRepository.save(user);
			System.out.println(savedUser.getFile().getOriginalFilename()+ " hello test *****>>>>>");
			try {
				webUtils.addProfilePhoto(user.getFile(), savedUser.getId(), "profile");
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			savedUser.setFile(null);
			return new ResponseEntity<User>( savedUser, HttpStatus.OK);
		}
		return new ResponseEntity<User>(existingUser.get(), HttpStatus.OK);
	
		
	}
	
	   
}


	
	
