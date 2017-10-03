package com.selsoft.trackme.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.User;
import com.selsoft.trackme.service.UserServiceImpl;

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
	 * @see com.selsoft.trackme.dao.UserDao#saveUser(com.selsoft.trackme.model.User)
	 * When User data comes from Service This method, will saves the Data into DB by
	 * calling MongoTemplate save()
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
		Update update=new Update();
		update.set("loggedOn", user.isLoggedOn());
		update.set("lastAccessed", user.getLastAccessed());

		template.updateFirst(query, update, User.class);

		logger.info("User Email " + user.getEmail() + " last access time "+user.getLastAccessed());

	}
	

}
