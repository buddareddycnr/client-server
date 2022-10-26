package com.openmedical.client;

import com.openmedical.server.constants.Gender;
import com.openmedical.server.constants.Title;
import com.openmedical.server.exceptions.UnknownException;
import com.openmedical.entity.Address;
import com.openmedical.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Component
public class OpenMedicalClient implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenMedicalClient.class);
    @Value("${openMedical.server.service1}")
    private String service1;
    @Value("${openMedical.server.service2}")
    private String service2;
    @Value("${openMedical.server.service3}")
    private String service3;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> servicesUrls = Arrays.asList(service1,service2,service3);
        while (true){
        String propertyValue = servicesUrls.get(new Random().nextInt(servicesUrls.size()));
        String[] separateUrl = propertyValue.split(",");
            callServer(separateUrl[0],separateUrl[1]);

        }
    }
    private void callServer(String url,String method){
            HttpHeaders httpHeaders =new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("CorrelationId", UUID.randomUUID().toString());
            try {
                switch (method) {
                    case "GET":
                        List<Customer> forEntity = (List<Customer>) restTemplate().getForObject(new URI(url), Customer.class);
                        if(null !=forEntity){
                            LOGGER.info("Retrieved all the customer details");
                        }
                        break;
                    case "POST":
                        ResponseEntity<Customer> userEntity = restTemplate().postForEntity(new URI(url),getUser(), Customer.class);
                        HttpStatus statusCode = userEntity.getStatusCode();
                        if(statusCode.is2xxSuccessful()){
                            LOGGER.info("User saved successfully {}",userEntity.getBody().toString());
                        }
                        break;

                    case "DELETE":
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("emailId","test@gmail.com");
                        restTemplate().delete(String.valueOf(new URI(url)),params);
                        break;
                    default:
                        LOGGER.info("Not supporting at the moment");
                }
            } catch (URISyntaxException e) {
                throw new UnknownException(e);
            }
        }
        private Customer getUser(){
            Customer customer = new Customer();
            customer.setAddress(getAddress());
            customer.setEmailId("buddareddycnr@gmail.com");
            customer.setGender(Gender.MALE);
            customer.setLastName("Edwire");
            customer.setFirstName("Jockson");
            customer.setMobileNumber("07586584814");
            customer.setTitle(Title.MR);
            customer.setCreatedBy("buddareddycnr@gmail.com");
            customer.setLastUpdatedBy("buddareddycnr@gmail.com");
        return customer;
        }
        private HashSet<Address> getAddress(){
            Address address= new Address();
            address.setCity("Edinburgh");
            address.setCountry("UK");
            address.setFlatNumber("Flat 3");
            address.setStreetName("Slateford");
            address.setHouseNumber("No# 5");
            address.setPostCode("EH14 1GT");
            HashSet<Address> addresses = new HashSet<>();
            addresses.add(address);
            return addresses;
        }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
