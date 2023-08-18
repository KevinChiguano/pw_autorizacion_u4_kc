package com.example.demo.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${app.jwt.secret}")
	private String jwtSecret;
	
	@Value("${app.jwt.expiration.ms}")
	private int jwtExpiration;
	
	
	//clase que genera el token
	public String generateJwtToken(String nombre) {
		
		LOG.info("Semilla: " + jwtSecret + " Tiempo: "+ jwtExpiration);
		
		return Jwts.builder()
				.setSubject(nombre) //nombre del usuario
				.setIssuedAt(new Date()) //expiracion fecha actual
				.setExpiration(new Date(System.currentTimeMillis()+ this.jwtExpiration)) //agregar el tiempo
				.signWith(SignatureAlgorithm.HS512,this.jwtSecret) //algoritmo y semilla
				.compact();
		
	}

}
