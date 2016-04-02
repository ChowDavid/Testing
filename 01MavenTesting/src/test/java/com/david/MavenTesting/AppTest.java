package com.david.MavenTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class AppTest {
	

	
	@BeforeClass
	public static void beforeClass(){
		System.out.println("Before class exec");
	}
	
	@Before
	public void beforeTest(){
		System.out.println("Before for each test case");
	}
	
	@AfterClass
	public static void afterClass(){
		System.out.println("After class exec");
	}
	
	@After
	public void afterTest(){
		System.out.println("After for each test case");
	}

	@Test
	public void test_happy() {
		App app=new App();
		assertEquals("You input 1234", app.process1("123"));
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
	
	@Ignore @Test
	public void test_not_run(){
		fail("This test case should not run");
	}

}
