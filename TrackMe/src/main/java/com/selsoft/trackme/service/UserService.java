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
	public void createPasswordResetTokenForUser(User user, String token);
	public User findUserByEmail(String userEmail);
	public String validatePasswordResetToken(long id, String token);
	public void forgotPassword(String email);
	
	
 
}
