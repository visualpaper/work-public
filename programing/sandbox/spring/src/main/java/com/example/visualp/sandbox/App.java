package com.example.visualp.sandbox;

import com.example.visualp.sandbox.copy.CopyStudy;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

  @Autowired
  private CopyStudy copyStudy;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@PostConstruct
	public void start() throws Exception {
    copyStudy.execute();
	}
}
