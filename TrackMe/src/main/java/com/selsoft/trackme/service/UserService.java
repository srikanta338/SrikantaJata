package com.selsoft.trackme.service;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.User;

/**
 * 
 * @author Sudhansu Sekhar
 *
 */
public interface UserService {

	public Errors saveUser(User user) ;
	public Errors saveUserLogin(User user) ;
	

}
