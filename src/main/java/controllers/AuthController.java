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
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Login
    ///////////////////////////////////////////////////////////////////////////
    public Result login(Context context) {

        return Results.html();
    }
    
    public Result signup(User user,
    					 Context context) {
    	
    	try {
    		User savedUser = userDao.saveUser(user);
        	
        	return Results.json().render(savedUser);
    	} catch(Exception e) {
    		Map<String, String> res = new HashMap<>();
    		res.put("message", e.getMessage());
    		return Results.status(422).json().render(res);
    	}
    }

    public Result loginPost(@Param("username") String username,
                            @Param("password") String password,
                            @Param("rememberMe") Boolean rememberMe,
                            Context context) {

        boolean isUserNameAndPasswordValid = userDao.isUserAndPasswordValid(username, password);

        if (isUserNameAndPasswordValid) {
            Session session = context.getSession();
            session.put("username", username);

            if (rememberMe != null && rememberMe) {
                session.setExpiryTime(24 * 60 * 60 * 1000L);
            }

            return Results.json().render(context.getSession());

        } else {

            // something is wrong with the input or password not found.
//            context.getFlashScope().put("username", username);
//            context.getFlashScope().put("rememberMe", String.valueOf(rememberMe));
//            context.getFlashScope().error("login.errorLogin");
        	Map<String, String> res = new HashMap<>();
        	res.put("message", "Invalid username or password");
            return Results.status(401).json().render(res);
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // Logout
    ///////////////////////////////////////////////////////////////////////////
    public Result logout(Context context) {

        // remove any user dependent information
        context.getSession().clear();

        Map<String, String> res = new HashMap<>();
        res.put("message", "Successfully logout");
        
        return Results.json().render(res);

    }

}