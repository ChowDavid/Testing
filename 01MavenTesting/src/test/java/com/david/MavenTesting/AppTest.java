package com.david.MavenTesting;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {

	@Test
	public void test_happy() {
		App app=new App();
		assertEquals("You input 123", app.process1("123"));
	}
	
	@Test
	public void test_Empty() {
		App app=new App();
		assertEquals("You input ", app.process1(""));
	}
	
	@Test
	public void test_Number() {
		App app=new App();
		assertEquals("You input 123.0", app.process1(new Double(123).toString()));
	}
	
	@Test
	public void test_Hello() {
		App app=new App();
		assertEquals("You input hello", app.process1("Hello"));
	}
	
	@Test(expected=NullPointerException.class)
	public void test_Null() {
		App app=new App();
		assertEquals("You input ", app.process1(null));
	}

}
