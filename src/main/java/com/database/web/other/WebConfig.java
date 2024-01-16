package com.database.web.other;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.database.facades.AdminFacade;
import com.database.facades.ClientFacade;

@Configuration
@Aspect
public class WebConfig {

	@Around("execution(* com.database.web.controllers.AdminController.*(..))")
	public ResponseEntity<?> authenticate(ProceedingJoinPoint point) throws Throwable {
		String token = (String) point.getArgs()[0];
		if (sessionsMap().containsKey(token)) {
			Session curSession = sessionsMap().get(token);
			ClientFacade admin = curSession.getService();
			if (admin instanceof AdminFacade
					&& (System.currentTimeMillis() - curSession.getLastAccessed() < 1000 * 30 * 5)) {
				return (ResponseEntity<?>) point.proceed();
			} else {
				sessionsMap().remove(token);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized Login");
	}

	@Bean
	public Map<String, Session> sessionsMap() {
		return new HashMap<String, Session>();
	}
}
