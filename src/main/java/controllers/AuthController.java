package controllers;

import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.session.Session;

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
    	
    	User savedUser = userDao.saveUser(user);
    	
    	return Results.json().render(savedUser);
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
            context.getFlashScope().put("username", username);
            context.getFlashScope().put("rememberMe", String.valueOf(rememberMe));
            context.getFlashScope().error("login.errorLogin");

            return Results.json().render(context);

        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // Logout
    ///////////////////////////////////////////////////////////////////////////
    public Result logout(Context context) {

        // remove any user dependent information
        context.getSession().clear();

        return Results.json().render(context);

    }

}