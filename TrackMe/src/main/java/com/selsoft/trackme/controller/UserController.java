package com.selsoft.trackme.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.User;
import com.selsoft.trackme.service.UserService;


/**
 * 
 * @author Sudhansu Sekhar
 *
 */

@RestController
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public ResponseEntity<Errors> saveUser(@RequestBody User user) {
		logger.info(user.getFirstName() + " data comes into UserController saveUser() for processing");
		Errors errors = userService.saveUser(user);
		return new ResponseEntity<Errors>(errors, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/get-user", method = RequestMethod.GET)
	public ResponseEntity<User> getUser() {
		logger.info("Data retrived from UserController getUser()");
		return new ResponseEntity<User>(new User(), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/user-login", method = RequestMethod.POST)
	public ResponseEntity<Errors> userLogIn(@RequestBody User user) {
		logger.info(user.getEmail() + " data comes into UserController saveUser() for processing");
		if(user.getEmail() == null && user.getEmail().equalsIgnoreCase("") && user.getPassword() == null && user.getPassword().equalsIgnoreCase(""))
		{
		return new ResponseEntity<Errors>(HttpStatus.BAD_REQUEST);
		}
		Errors errors = userService.saveUserLogin(user);
		return new ResponseEntity<Errors>(errors, HttpStatus.CREATED);
		
	}


}
