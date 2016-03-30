#Java Maven Test Project
This project demo how JUnit testing setting on a maven can help
- Section A @Test
- Section B @BeforeTest, @AfterTest, @BeforeClass, @AfterClass, @Ignore
- Section C @RunWith(Parameterized.class)


##Section A Test Basic @Test
###Step 1 change the maven project to support 1.6 and higher
```xml
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<maven.compiler.source>1.7</maven.compiler.source>
		<maven.compiler.target>1.7</maven.compiler.target>
	</properties>
```
###Step 2 change the dependence to latest Junit version and set to scope as test
```xml
	<dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
```
###Step 3 implement a code with bug
```java

	public String process1(String input){
		return input.equalsIgnoreCase("Hello")?"You input hello":"You input "+input;
	}
```
###Step 4 implement the JUnit with different test case
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
 ###Step 5 Run the test on maven
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
##Section B more than basic @BeforeTest, @AfterTest, @BeforeClass, @AfterClass, @Ignore
Please note the before and after class are static method. that means it is one and only one call on the unit test.
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
##Section C @RunWith(Parameterized.class)
###Step 1 Create a CVS, DB or anything can provide a list of test data
```csv
TestCase,TestValue,ExpectedValue,exception,end
Null Test Case,NULL,,java.lang.NullPointerException,end
Empty Test Case,,You input ,,end
123 test Case,123,You input 123,,end
Hello Test Case,Hello,You input hello,,end
```
![alt tag](https://cloud.githubusercontent.com/assets/4963861/14143423/b1632106-f6bd-11e5-891f-459ba80ef578.png)
###Step 2 Create a Test case with implementation runwith
The method below inside the test case to create a Collection of object array. It design to read the CSV file from the test case resource above.
```java

	@Parameters(name = "{index}: {0}")
	public static Collection<Object[]> getData() throws IOException{

		InputStream inputStream = AppTest2.class.getClassLoader().getResourceAsStream("com/david/MavenTesting/data.csv");
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
```
To get ready for the parameters annotation it should has a class level annotation, properties and the constructor
Please remind the test name must be end with [*Test.java](http://stackoverflow.com/questions/15159847/does-parameterized-junit-test-correct-with-mvn-test) for maven test requirement.
```java

	@RunWith(Parameterized.class)
	public class App2Test  {
	
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
```
Then the junit test case jsut use the instance properties as its paramter input from constructor above.
```java

	@Test
	public void test_app(){
		try {
		String actualResult=app.process1(testValue);
		assertEquals(testCaseName,expectedValue,actualResult);
		} catch (Throwable e){
			assertEquals(testCaseName,exceptionType,e.getClass().getName());
		}
		
		
	}
```

###Step 3 Execute it.
```maven
davids-MacBook-Pro:01MavenTesting david$ mvn clean test
...
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.david.MavenTesting.App2Test
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.065 sec
Running com.david.MavenTesting.AppTest
...
Tests run: 6, Failures: 0, Errors: 0, Skipped: 1, Time elapsed: 0.008 sec

Results :

Tests run: 10, Failures: 0, Errors: 0, Skipped: 1
```
![alt tag](https://cloud.githubusercontent.com/assets/4963861/14143569/99c4139c-f6be-11e5-9878-668672844c94.png)
