package com.fanfq.sbt;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WebControllerTest {

	@LocalServerPort //spring boot 1.4 才支持
	private int port;
	//@Value("${local.server.port}")
	//private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void webTest(){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(
				"http://localhost:"+port+"/web/hello", HttpMethod.GET,	requestEntity, String.class);
		System.out.println("port is: " + port);
		System.out.println("headers: " + responseEntity.getHeaders());
		System.out.println(responseEntity.getBody());
	}
	
}
