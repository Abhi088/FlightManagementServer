package filters;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;

import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Ninja;
import ninja.Result;
import ninja.Results;

public class LoggedInFilter implements Filter {
	
	private final Ninja ninja;
	
	@Inject
	public LoggedInFilter(Ninja ninja) {
		this.ninja = ninja;
	}
	
	@Override
    public Result filter(FilterChain chain, Context context) {
		if (context.getSession() == null
                || context.getSession().get("username") == null) {
			Map<String, String> res = new HashMap<>();
			res.put("message", "You are not logged in");
            return Results.forbidden().json().render(res);
        } else {
            return chain.next(context);
        }
    }
}
