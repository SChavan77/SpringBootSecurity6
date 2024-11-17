# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.5/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.5/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.3.5/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

### Spring Security
Once Imported Spring Security dependecny. /login page will come by default.
username: user
password: watch the console: "Using generated security password: ........"
To trigger the logout: use http://localhost:8080/logout
1) http://localhost:8080/login Login page
2) Enter credentials
3) http://localhost:8080/home?continue You land up here
4) If we hit http://localhost:8080/logout, it will prompt for the logout option. Proceed.
Once logout, the existing password credential doesn't work.

**CSRF Concept**:

When client requests for the URL resource (GET/POST..), before directing to the URL, it goes to /login page.
Once login is successful, sessionId & one more hidden field will be sent back to the client.
That hidden variabe is CSRFToken.
Whenever hackers/users tries to modify the data, Spring security has made the CSRF token as a mandatory field.
So at time, even though we have used the correct login credentials, GET give result, but NOT POST request.

To pass CSRF in the request:
1) Fetch the attribute _csrf from the HTTPServletRequest. : localhost:8080/csrf 
2) Copy the fetched csrf token and pas it in HEADERS in the target resource URL.
   "headerName": "X-CSRF-TOKEN"
    "token":"xxx"

3) make sure you have logged in before 2) step.
We can disable the CSRFToken feature.

**How to add our custom username and passwords**
In application.properties,
    spring.security.user.name
    spring.security.user.password

**Taking control of SecurityFilter**
(1) Plug in the filter chain
(2) Add the authentication for the requests
(3) Login setUp
(4) CSRF config if needed

**We setUp UserDetailsServce**

Study: UserDetailsService, UserDetails -> go to it's implementation classes

First time Register step: We need to byPass Authentication step. How to do that?
Use -  .requestMatchers("register").permitAll()


Login & Verify user API:

Even for login, we need to byPass Authentication step.