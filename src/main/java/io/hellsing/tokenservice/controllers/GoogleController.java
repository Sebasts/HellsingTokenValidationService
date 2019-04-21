package io.hellsing.tokenservice.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.hellsing.tokenservice.service.GoogleTokenVerifierService;

@RestController
public class GoogleController {
		
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private GoogleTokenVerifierService gtvs;
    
    @RequestMapping(value = "/api/tokenservice/google", method = RequestMethod.POST )
    @ResponseBody
    public ResponseEntity validateToken(@RequestParam(value = "token", required = true)String token) {
    	System.out.println(token);
    	if(! gtvs.verifyOauthToken(token).equals("bad req")) {
    		return new ResponseEntity(token, HttpStatus.OK);
    		
    	}else {
    		return new ResponseEntity(HttpStatus.BAD_REQUEST);
    	}
    	
    }
}
