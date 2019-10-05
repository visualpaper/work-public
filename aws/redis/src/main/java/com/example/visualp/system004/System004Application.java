package com.example.visualp.system004;

import com.example.visualp.system004.accessor.redis.RedisAccessor;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class System004Application {

	@Autowired
	RedisAccessor accessor;

	public static void main(String[] args) {
		SpringApplication.run(System004Application.class, args);
	}

	@PostConstruct
	public void start() throws Exception {
		accessor.zCommands();
	}
}
