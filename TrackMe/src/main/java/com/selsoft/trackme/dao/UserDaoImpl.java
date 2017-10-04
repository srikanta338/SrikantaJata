package com.selsoft.trackme.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.PasswordResetToken;
import com.selsoft.trackme.model.User;

/**
 * 
 * @author Sudhansu Sekhar
 *
 */
@Repository
public class UserDaoImpl implements UserDao {
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	private MongoTemplate template;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.selsoft.trackme.dao.UserDao#saveUser(com.selsoft.trackme.model.User)
	 * When User data comes from Service This method, will saves the Data into
	 * DB by calling MongoTemplate save()
	 * 
	 */
	@Override
	public void saveUser(User user) {

		template.save(user);
		logger.info("User " + user.getFirstName() + " Saved in Database");

	}

	@Override
	public void saveUserLogin(User user) {

		Query query = new Query(Criteria.where("email").is(user.getEmail().toLowerCase()));
		Update update = new Update();
		update.set("loggedOn", user.isLoggedOn());
		update.set("lastAccessed", user.getLastAccessed());

		template.updateFirst(query, update, User.class);

		logger.info("User Email " + user.getEmail() + " last access time " + user.getLastAccessed());

	}

	}

	public void changeUserPassword(User user, String password) {
		user.setPassword(passwordEncoder.encode(password));
		repository.save(user);
	}

	@Override
	public User findUserByEmail(String email) {
		Query query = new Query(Criteria.where("email").is(email.toLowerCase()));
		List<User> userExist = template.find(query, User.class);

		return userExist.get(0);
	}

	@Override
	public void saveResetPasswordToken(PasswordResetToken token) {
		
	}

}
