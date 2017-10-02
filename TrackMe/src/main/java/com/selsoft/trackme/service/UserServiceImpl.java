package com.selsoft.trackme.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selsoft.trackme.dao.UserDao;
import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.User;
import com.selsoft.trackme.model.ValidError;
import com.selsoft.trackme.validation.UserValidation;

import com.selsoft.trackme.utils.Utils;

/**
 * 
 * @author Sudhansu Sekhar
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	private static final Logger logger = Logger.getLogger(UserService.class);
	private UserValidation validation = new UserValidation();

	public Errors saveUser(User user) {
		if (isValid(user)) {
			logger.info("User data is Valid and processing to Dao");
			userDao.saveUser(user);
		} else {
			logger.info("User data is not Valid returning Error Data");
			return getError(user);
		}
		return null;
	}

	public boolean isValid(User user) {

		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		String password = user.getPassword();

		ValidError nameErrors = validation.nameValidation(firstName, lastName);
		ValidError emailErrors = validation.emailValidation(email);
		ValidError passwordErrors = validation.passwordValidation(password);

		if ("Success".equals(nameErrors.getErrorCode()) && "Success".equals(emailErrors.getErrorCode())
				&& "Success".equals(passwordErrors.getErrorCode())) {
			return true;
		} else {
			return false;
		}

	}

	public Errors getError(User user) {
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		String password = user.getPassword();

		ValidError nameErrors = validation.nameValidation(firstName, lastName);
		ValidError emailErrors = validation.emailValidation(email);
		ValidError passwordErrors = validation.passwordValidation(password);

		List<ValidError> list = new ArrayList<ValidError>();
		list.add(nameErrors);
		list.add(emailErrors);
		list.add(passwordErrors);

		Errors errors = new Errors();
		errors.setError(list);
		return errors;

	}
	
	public boolean isValidUser(User user) {
		
		String email = user.getEmail();
		String password = user.getPassword();
		
		ValidError emailErrors = validation.emailValidation(email);
		ValidError passwordErrors = validation.passwordValidation(password);

		if ("Success".equals(emailErrors.getErrorCode())
				&& "Success".equals(passwordErrors.getErrorCode())) {
			return true;
		} else {
			return false;
		}

	}

	public Errors saveUserLogin(User user) {
		if (isValidUser(user)) {
			logger.info("User data is Valid and processing to Dao");
			
			String password = user.getPassword();
			try {
				// encrypting the password seeing to User object
				// setting last accessed time & userlogin boolean flag.
			String encryptPwd = Utils.encryptPassword(password);
			user.setPassword(encryptPwd);
			user.setLoggedOn(true);
			Calendar time = Calendar.getInstance();
			time.add(Calendar.MILLISECOND, -time.getTimeZone().getOffset(time.getTimeInMillis()));
			Date date = time.getTime();
			user.setLastAccessed(date.toString());
			userDao.saveUserLogin(user);
			}
			catch(Exception e) {
				// throw custom seception
			}
			
		} else {
			logger.info("Email Id or Password are not valid returning Error Data");
			return getError(user);
		}
		return null;
	}

}
