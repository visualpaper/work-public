package com.example.visualp.system.system012;

import com.example.visualp.system.system012.jwt.Jwt;
import com.example.visualp.system.system012.jwt.RSAJwtParser;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class System012Application {

  @Autowired
  RSAJwtParser rsaJwtParser;

	public static void main(String[] args) {
		SpringApplication.run(System012Application.class, args);
	}

	@PostConstruct
	public void start() throws Exception {

	  // RSA
    String encryptedJwt = rsaJwtParser.encrypt("sample data");
    Jwt jwt = rsaJwtParser.decrypt(encryptedJwt);

    System.out.println(jwt.getPayload());
    System.out.println(jwt.toString());
	}
}
