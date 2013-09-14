package com.google.endpoints.myapplication.server;

import java.util.ArrayList;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.users.User;

/**
 * Defines v1 of a helloworld API, which provides simple "greeting" methods.
 */
@Api(name = "helloworld", version = "v1")
public class Greetings {
	public static ArrayList<Greeting> greetings = new ArrayList<Greeting>();

	static {
		greetings.add(new Greeting("hello world!"));
		greetings.add(new Greeting("goodbye world!"));
	}

	@ApiMethod(name = "greetings.message", httpMethod = "get", path = "message")
	public Greeting getGreeting(@Named("id") Integer id) {
		return greetings.get(id);
	}

	@ApiMethod(name = "greetings.multiply", httpMethod = "post", path = "multiply")
	public Greeting insertGreeting(@Named("times") Integer times,
			Greeting greeting) {
		Greeting response = new Greeting();
		StringBuilder responseBuilder = new StringBuilder();
		for (int i = 0; i < times; i++) {
			responseBuilder.append(greeting.getMessage());
			greetings.add(new Greeting(greeting.getMessage()));
		}
		response.setMessage(responseBuilder.toString());
		return response;
	}

	@ApiMethod(name = "greetings.authed", path = "greeting/authed")
	public Greeting authedGreeting(User user) {
		Greeting response = new Greeting("hello " + user.getEmail());
		return response;
	}
}