package com;

import com.openmedical.client.OpenMedicalClient;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplicationBuilder server = new SpringApplicationBuilder();
		server.sources(Application.class);
		server.profiles("server");
		server.run(args);
		SpringApplicationBuilder client = new SpringApplicationBuilder();
		client.sources(OpenMedicalClient.class);
		client.profiles("client");
		client.web(WebApplicationType.NONE);
		client.main(OpenMedicalClient.class);
		client.run(args);

	}

}
