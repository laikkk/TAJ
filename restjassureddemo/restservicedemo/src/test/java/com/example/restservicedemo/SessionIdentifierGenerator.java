package com.example.restservicedemo;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public final class SessionIdentifierGenerator {
	  private SecureRandom random = new SecureRandom();
	  Random generator = new Random(); 
	  
	  public String nextSessionId() {
	    return new BigInteger(130, random).toString(32);
	  }
	  
	  public int nextInt() {
		  return generator.nextInt(10) + 1;
		  }
	}