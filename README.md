Project Info:
1. This is in-memory H2 DB based project.
1. Once the project starts, the DB gets initialized with a couple of records.

Referenced from:
https://www.youtube.com/watch?v=9ME94z46fsU

In this project, I am using the Unirest api to write a rest client to invoke the api
http://kong.github.io/unirest-java/

To open H2 Console UI
http://localhost:8080/h2/login.jsp

SQL:
SELECT * FROM PERSON;

Notes:
1. When working with Unirest, examine how to work with JsonNode (see the method implementation)

Question:
1. What is the difference between RequestParam and PathVariable. Basically how does get single entity works using url http://localhost:8080/person-service/getPerson/1
2. How to start the Spring application with H2 database pre-populated with a few rows in the table?
   Ans: Done using @PostConstruct to populate a single table
3. H2RestClient (getPerson): This method errors out if there are no person record in the table. How to handle this?
4. Logging RestAPI request and response using Unirest interceptor (class UnirestJSONInterceptor). I observed that the response logging
   errors when using .asObject(Person.class) with post(). However, logging is fine when using .asJSON();
