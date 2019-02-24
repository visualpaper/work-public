package com.example.visualp.system003;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.IntStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xerial.snappy.Snappy;

@SpringBootApplication
public class System003Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(System003Application.class, args);


		String input = create();
		System.out.println(input.getBytes("UTF-8").length);
		byte[] compressed = Snappy.compress(input.getBytes("UTF-8"));
		System.out.println(compressed.length);
		byte[] uncompressed = Snappy.uncompress(compressed);
		System.out.println(uncompressed.length);
		String result = new String(uncompressed, "UTF-8");
		System.out.println(result);


		ObjectMapper mapper = new ObjectMapper();
		Sample sample = mapper.readValue(result, Sample.class);

		System.out.println(sample.parts.size());
	}

	public static String create() {
		return "{\"objectId\": \"550e8400-e29b-41d4-a716-446655440000\", \"uploadId\": \"OW2fM5iVylEpFEMM9_HpKowRapC3vn5sSL39_396UW9zLFUWVrnRHaPjUJddQ5OxSHVXjYtrN47NBZ-khxOjyEXAMPLE\", \"parts\": [" + createParts() + "]}";
	}

	public static String createParts() {
		String value = "{\"number\": 1, \"etag\": \"c06f7cd4baacb087002a99a5f48bf953\"}";

		for (int i = 0; i < 10000; i++) {
			value = value + ",{\"number\": 1, \"etag\": \"c06f7cd4baacb087002a99a5f48bf953\"}";
		}
		return value;
	}
}
