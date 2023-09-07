package com.example.assignment2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Assignment2Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment2Application.class, args);
        quote();
        System.exit(0);
    }

    public static void quote() {
        try {
            String url = "https://api.quotable.io/random";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonPrice = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonPrice);

            //gets quote
            String quote = root.findValue("content").asText();
            //gets author name
            String author = root.findValue("author").asText();
            //print quote and author
            System.out.println("Quote: " + " \"" + quote + "\"");
            System.out.println("Author: " + author);

        } catch (JsonProcessingException ex) {
            System.out.println("error");
        }
    }

}
