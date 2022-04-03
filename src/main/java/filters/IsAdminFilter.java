package filters;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;

import dao.UserDao;
import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;
import ninja.Results;

public class IsAdminFilter implements Filter {
	
	@Inject
	private UserDao userdao;
	
	@Override
	public Result filter(FilterChain chain, Context context) {
		String username = context.getSession().get("username");
		Boolean isAdmin = userdao.isAdmin(username);
		if(isAdmin) return chain.next(context);
		else {
			Map<String, String> res = new HashMap<>();
			res.put("message", "Not authorized to perform the task");
			return Results.forbidden().json().render(res);
		}
	}
}
