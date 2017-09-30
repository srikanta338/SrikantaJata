package com.selsoft.trackme.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selsoft.trackme.dao.UserDao;
import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.User;
import com.selsoft.trackme.model.ValidError;
import com.selsoft.trackme.validation.UserValidation;

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

}
