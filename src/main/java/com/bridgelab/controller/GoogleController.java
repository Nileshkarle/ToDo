package com.bridgelab.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.model.User;
import com.bridgelab.service.UserService;
import com.bridgelab.token.TokenGenerator;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class GoogleController {

	@Autowired
	GoogleConnection googleConnection;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TokenGenerator tokenService;
	@RequestMapping(value = "/loginWithGoogle")
	public void googleConnection(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(" in googleLoginURL  ");
		String unid = UUID.randomUUID().toString();
		request.getSession().setAttribute("STATE", unid);
		String googleLoginURL = googleConnection.getGoogleAuthURL(unid);
		System.out.println("googleLoginURL  " + googleLoginURL);
		response.sendRedirect(googleLoginURL);
	}
	
	@RequestMapping(value="/connectgoogle")
	public void redirectFromGoogle(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		String sessionState = (String) request.getSession().getAttribute("STATE");
		String googlestate = request.getParameter("state");
		
		System.out.println("in connect google");

		if (sessionState == null || !sessionState.equals(googlestate)) {
			response.sendRedirect("loginWithGoogle");
		}

		String error = request.getParameter("error");
		if (error != null && error.trim().isEmpty()) {
			response.sendRedirect("login");
		}
		
		String authCode = request.getParameter("code");
		String googleaccessToken = googleConnection.getAccessToken(authCode);
		System.out.println("accessToken " + googleaccessToken);
		
		JsonNode profile = googleConnection.getUserProfile(googleaccessToken);
		System.out.println("google profile :"+profile);
		System.out.println("google profile :" + profile.get("displayName"));
		System.out.println("user email in google login :" + profile.get("emails").get(0).get("value").asText()); //asText() is use to remove outer text.
		System.out.println("google profile :" + profile.get("image").get("url"));
		User user = userService.emailValidation(profile.get("emails").get(0).get("value").asText());

		if (user == null) {
			System.out.println(" user is new to our db");
			user = new User();
			user.setFirstName(profile.get("displayName").asText());
			user.setEmail(profile.get("emails").get(0).get("value").asText());
			user.setPassword("");
			user.setProfile(profile.get("image").get("url").asText());
			userService.saveUserData(user);
		}
		
		System.out.println(" user is not new to our db ,it is there in our db");
		/*tokens = tokenManupulation.generateTokens();
		user.setProfile(profile.get("image").get("url").asText());
		userService.updateUserProfile(user);
		
		tokens.setGetUser(user);
		tokenService.saveToken(tokens);*/
		user = userService.emailValidation(user.getEmail());
		String token = tokenService.createJWT(user.getId());
		response.setHeader("Authentication", token);
		System.out.println(token);
		Cookie accCookie = new Cookie("socialaccessToken", token);
		response.addCookie(accCookie);
		session.setAttribute("SocialLogin", token); 
		response.sendRedirect("http://localhost:8080/ToDo/#!/dummyPage");
	}
	
}
