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
