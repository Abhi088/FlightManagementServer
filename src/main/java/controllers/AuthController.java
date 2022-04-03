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

import models.User;

@Singleton
public class AuthController {

	@Inject
	UserDao userDao;
	
	public Result login(Context context) {

		return Results.html();
	}

	public Result signup(User user, Context context) {

		try {
			User savedUser = userDao.saveUser(user);

			return Results.json().render(savedUser);
		} catch (Exception e) {
			Map<String, String> res = new HashMap<>();
			res.put("message", e.getMessage());
			return Results.status(422).json().render(res);
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
				session.setExpiryTime(24 * 60 * 60 * 1000L);
			}
			return Results.json().render(user);
		} else {
			Map<String, String> res = new HashMap<>();
			res.put("message", "Invalid username or password");
			return Results.status(401).json().render(res);
		}
	}

	public Result logout(Context context) {

		// remove any user dependent information
		context.getSession().clear();
		context.cleanup();

		Map<String, String> res = new HashMap<>();
		res.put("message", "Successfully logout");

		return Results.json().render(res);

	}

}