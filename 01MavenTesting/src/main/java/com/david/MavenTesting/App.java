package com.david.MavenTesting;

/**
 * Hello world!
 *
 */
public class App {

	public String process1(String input){
		return input.equalsIgnoreCase("Hello")?"You input hello":"You input "+input;
	}
    
}
