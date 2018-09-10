# Technology
* Spring 
  * make use of simple REST and data repository libraries
  * Has object validation built in and good REST support 
* Hibernate and Flyway
  * Hibernate well supported with Spring
  * Flyway for applying and managing database migrations 
    - keeps audit of when and what migrations applied, Spring can automatically apply on startup
 
# Improvements
* Exceptions could be mapped to appropriate status codes e.g. Bad request to 400/422
* More endpoints for filtering and searching on resources e.g. search by name, regex, best match
* Simple UI for adding/querying resources

# Issues 