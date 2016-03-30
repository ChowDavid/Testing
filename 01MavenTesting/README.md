#Java Maven Test Project
This project demo how basic setting on junit test on a maven project
##Step 1 change the maven project to support 1.6 and higher
```
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<maven.compiler.source>1.7</maven.compiler.source>
		<maven.compiler.target>1.7</maven.compiler.target>
	</properties>
```
##Step 2 change the dependence to latest Junit version and set to scope as test
```
	
	<dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
```
##Step 3 implement a code with bug
```java

	public String process1(String input){
		return input.equalsIgnoreCase("Hello")?"You input hello":"You input "+input;
	}
```
##Step 4 implement the JUnit with different test case
```java
	
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
```
    