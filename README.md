# JumoAccounting
Jumo World Mobile Accounting Application.  First Technical Challenge.

Build/Run
 1. mvn clean compile assembly:single
 2. java -jar target/jumo-accounting-1.0-jar-with-dependencies.jar Loans.csv true
 3. Check the output file: Output.csv

Production Application Dependency
 1. mvn clean install
 2. Include maven dependency in your application
 3. Use library

Logging
 - SLF4J has been implemented; the logger of your choice may be selected when deploying to your application server.

Assumptions:
 1. Aggregation by Network, Product, Month - Month is the actual month and does not take the year into consideration.

Code Test Coverage:
 I have added as many unit tests as time has allowed.  The initial coverage is satisfactory.  However, more tests need to be added.

Language Considerations:
I selected Java for the following reasons:
- An Object Oriented Language.
- Is able to run on most Operating Systems.
- The jar can be included as is for production deployment.
- I am familiar with it

Performance Considerations:
I realised near the end of this project that I have coded to some extent "to impress".  I was wondering if a simple Main method may be quicker?  I do believe that this solution is very reusable and has some performance considerations such as:
- The parser feeds the transaction directly into the aggregator otherwise another O(n) iteration would need to be completed for aggregation.