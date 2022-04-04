package etc;

import java.util.HashMap;
import java.util.Map;

import ninja.Result;
import ninja.Results;

public class Utilities {

	public Utilities() {
	}

	public Result createMessageResponse(Integer status, String message) {
		Map<String, String> res = new HashMap<>();
		res.put("message", message);
		return Results.status(status).json().render(res);
	}
}
