package controllers;

import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.session.Session;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.UserDao;
import etc.Utilities;
import models.User;

@Singleton
public class AuthController {

	@Inject
	UserDao userDao;
	
	Utilities utility = new Utilities();

	public Result login(Context context) {

		return Results.html();
	}

	public Result signup(User user, Context context) {

		try {
			User savedUser = userDao.saveUser(user);
			return Results.json().render(savedUser);
		} catch (Exception e) {
			return utility.createMessageResponse(422, e.getMessage());
		}
	}

	public Result loginPost(@Param("username") String username, @Param("password") String password,
			@Param("rememberMe") Boolean rememberMe, Context context) {

		User user = userDao.isUserAndPasswordValid(username, password);

		if (user != null) {
			Session session = context.getSession();
			session.put("username", username);
			session.put("isAdmin", userDao.isAdmin(username).toString());
			if (rememberMe != null && rememberMe) {
				session.setExpiryTime(7 * 24 * 60 * 60 * 1000L);
			}
			return Results.json().render(user);
		} else {
			return utility.createMessageResponse(401, "Invalid username and password");
		}
	}

	public Result isAdmin(@Param("username") String username, Context context) {
		Boolean isAdmin = userDao.isAdmin(username);
		if (isAdmin) return utility.createMessageResponse(200, "OK");
		return utility.createMessageResponse(403, "forbidden");
	}

	public Result logout(Context context) {
		context.getSession().clear();
		context.cleanup();
		return utility.createMessageResponse(200, "Successfully log out");

	}

}