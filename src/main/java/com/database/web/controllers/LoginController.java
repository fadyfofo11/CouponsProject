package com.database.web.controllers;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database.beans.Token;
import com.database.facades.ClientFacade;
import com.database.facades.ClientType;
import com.database.loginManager.LoginManager;
import com.database.web.other.Session;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

	@Autowired
	private LoginManager loginManager;

	@Autowired
	private Map<String, Session> sessionsMap;

	@PostMapping("/login/{email}/{password}/{type}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password,
			@PathVariable ClientType type) {
		String token = UUID.randomUUID().toString();
		try {
			ClientFacade service = loginManager.login(email, password, type);
			if (service != null) {
				Session session = new Session(service, System.currentTimeMillis());
				sessionsMap.put(token, session);

				Token toke = new Token(token);
				System.out.println(token);
				return ResponseEntity.ok(toke);
			}
			return ResponseEntity.badRequest().body("Failed to login");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Email or password are worng!");
		}
	}

	@PostMapping("/logout/{token}")
	public void logout(@PathVariable String token) {
		sessionsMap.remove(token);
	}

}
