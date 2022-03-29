package controllers;

import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;


@Singleton
public class ApplicationController {

    public Result index() {

        return Results.html();

    }
    
    public Result helloWorldJson() {
        
        SimplePojo simplePojo = new SimplePojo();
        simplePojo.content = "Hello World! Hello Json!";

        return Results.json().render(simplePojo);

    }
    
    public static class SimplePojo {

        public String content;
        
    }
}
