package com.david.MavenTesting;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;



@RunWith(Parameterized.class)
public class App2Test{
	
	private String testCaseName;
	private String testValue;
	private String expectedValue;
	private String exceptionType;
	private App app;
	
	

	public App2Test(String testCaseName, String testValue, String expectedValue,String exceptionType) {
		super();
		this.testCaseName = testCaseName;
		this.testValue = testValue;
		this.expectedValue = expectedValue;
		this.exceptionType=exceptionType;
	}

	@Parameters(name = "{index}: {0}")
	public static Collection<Object[]> getData() throws IOException{

		InputStream inputStream = App2Test.class.getClassLoader().getResourceAsStream("com/david/MavenTesting/data.csv");
		BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
		int c=0;
		String line=null;
		List<Object[]> lists= new ArrayList<Object[]>();
		while((line=br.readLine())!=null){
			c++;
			if (c<=1)continue;
			String[] cells=line.split(",");
			if (cells.length<4) continue;
			if (cells[1].equalsIgnoreCase("NULL")){
				cells[1]=null;
			} 
			lists.add(new Object[]{cells[0],cells[1],cells[2],cells[3]});
		}
		br.close();
		return lists;
		
	}
	
	@Before
	public  void init(){
		app=new App();
	}
	
	@Test
	public void test_app(){
		try {
		String actualResult=app.process1(testValue);
		assertEquals(testCaseName,expectedValue,actualResult);
		} catch (Throwable e){
			assertEquals(testCaseName,exceptionType,e.getClass().getName());
		}
		
		
	}
	
}
