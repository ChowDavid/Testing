#Java Maven Test Project
This project demo how basic setting on junit test on a maven project
##Step 1 change the maven project to support 1.6 and higher
```xml
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<maven.compiler.source>1.7</maven.compiler.source>
		<maven.compiler.target>1.7</maven.compiler.target>
	</properties>
```
##Step 2 change the dependence to latest Junit version and set to scope as test
```xml
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
 ##Step 5 Run the test on maven
 ```maven
 davids-MacBook-Pro:01MavenTesting david$ mvn test
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building 01MavenTesting 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ 01MavenTesting ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/david/Documents/workspaces/workspace_Training/Testing/01MavenTesting/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ 01MavenTesting ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /Users/david/Documents/workspaces/workspace_Training/Testing/01MavenTesting/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ 01MavenTesting ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/david/Documents/workspaces/workspace_Training/Testing/01MavenTesting/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ 01MavenTesting ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /Users/david/Documents/workspaces/workspace_Training/Testing/01MavenTesting/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ 01MavenTesting ---
[INFO] Surefire report directory: /Users/david/Documents/workspaces/workspace_Training/Testing/01MavenTesting/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.david.MavenTesting.AppTest
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.061 sec

Results :

Tests run: 5, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.948 s
[INFO] Finished at: 2016-03-30T16:33:32+08:00
[INFO] Final Memory: 13M/245M
[INFO] ------------------------------------------------------------------------
davids-MacBook-Pro:01MavenTesting david$ 
 
```
##Other then test case
It has beforetest, aftertest, beforeclass, afterclass and ignore. Please note the before and after class are static method. that means it is one and only one call on the unit test.
```java
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
	
	@Ignore @Test
	public void test_not_run(){
		fail("This test case should not run");
	}
```
If test case run it will show the skip and and also the call flow
```maven
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.david.MavenTesting.AppTest
Before class exec
Before for each test case
After for each test case
Before for each test case
After for each test case
Before for each test case
After for each test case
Before for each test case
After for each test case
Before for each test case
After for each test case
After class exec
Tests run: 6, Failures: 0, Errors: 0, Skipped: 1, Time elapsed: 0.069 sec

Results :

Tests run: 6, Failures: 0, Errors: 0, Skipped: 1
```