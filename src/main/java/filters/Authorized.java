package filters;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;

import dao.UserDao;
import etc.LoggedInUser;
import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;
import ninja.Results;

public class Authorized implements Filter {
	
	@Inject
	UserDao userDao;
	
	private String username;
	
	public Authorized() {}
	
	public Authorized(@LoggedInUser String username) {
		this.username = username;
	}
	
	
	@Override
    public Result filter(FilterChain chain, Context context) {

		if(userDao.isAdmin(username)) return chain.next(context);
		
		Map<String, String> res = new HashMap<>();
		res.put("message", "User not authorized for this request");
		
		return Results.status(401).json().render(res);
    }
}
