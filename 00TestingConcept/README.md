#Testing Concept
This Section is about the concept of testing
we need to know about [black-box testing](https://en.wikipedia.org/wiki/Black-box_testing) and [white-box testing](https://en.wikipedia.org/wiki/White-box_testing), [boundary testing](https://en.wikipedia.org/wiki/Boundary_testing), [positive and negative test case](http://www.nishantverma.com/2010/03/test-case-paths-happy-sad-bad.html).
The test code should be invisible to the deployment binary. The test cases should be cover all the possible logical branch. The test results should be consistency. The test case should run automatically.

Something not to do:
- Don't only write main program as your test case.
- Don't expected UAT or SIT can find lot of problem.
- Don't reply on Human tester. 
- Don't write any test code on the production code.

##Test approach development
- Testable code 
- Test performance
- Test case design
- Dummy, Mock, Stub

##Test Tools & framework
There are some test tools out of the box
- JMeter
- SOAPUI
- Selenium UI test
- Cloud base UI Test said [sauce](https://saucelabs.com/selenium/)
- sonar
- JUnit
- TestNG
- Mockit
- 


###Testable code
Each Service class should had a unit test cases to check before code submit to SCM. The test case should include boundary test case and also the positive test and negative test case. It should also consider the test case can trigger the exception in case it need to connect other device said file read/write, db connection, ftp connection. those can be use the dummy to trigger the exception during test exec time.The service end-point said DAO, FTP, Communicate end point should be mock before testing.
Each end-point should be test as much as possible.
Each DAO end-point class should be test by In-memory Datasource in-order to confirm the CURD operation can run correctly.
Each WS client end-point should has a dummy WS server to provide a test case during unit test.
Each WS server end-point should has a dummy WS client to call in-order to execute the unit test.
So that the code should not be hard coded and it should be decoupling each other. 

###Testing performance
Test should be consider speed. So that it should made use of mock and expected response form mock. It should not connect the device (external resource) as test dependence. It should mock everything on every end-point.

###Test case design
 Test cases should consider Max value, Min Value, expected Value, expected +1 and expected -1 value. Overflow? or exception as input form callee module.
Test should also consider concurrent test. So that test can also consider more than one thread to trigger the system. It can find a bug if a large loading concurrent testing on a web server.

###Dummy, Mock, Stub
They are very similar. 
- Dummy should has no logic.
- Mock and Stub are very similar. Stub is a replacement of real end-point with logic inside and output. It can be very complicated. 
- Mock has no need external class module compare with Stub.

