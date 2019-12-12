# springboot-apache-cli

Spring Boot 2.2.x Example project illustrating:

  - how to use command line arguments with Apache common-cli
  - how to use spring profile

# Using the project:

1. Clone project from GitHub
2. Import in Eclipse as maven project
3. Use JUnit test

## Building

To build for a specific environment setting active one of the Spring profiles:

  - _mvn clean package -P{target-profile}_

Where {target-profile} can be test (by default if no -P build argument specified) or prod


