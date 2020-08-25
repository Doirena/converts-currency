# Converts-currency
 Simple currency converts application, where You can get calculate amount by LT Bank for currency exchange rates.
 
 ## Working with:
 - FRONT-END: Thymeleaf, HTML, jQuery;
 - BACK-END: Spring Boot gradle (Java 8, packaging: jar);
 - Database: H2.
 
  
 ### Project run application
 ##### Create schema if You use Mysql:
 ```
 CREATE SCHEMA `converts_currency` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
``` 
 ##### Table for data, which parse from https://www.lb.lt/webservices/FxRates/:
```
 CREATE TABLE `converts_currency`.`currency_data` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `type` VARCHAR(255) NOT NULL,
   `rate` DECIMAL(10,4) NOT NULL,
   PRIMARY KEY (`id`))
 ENGINE = InnoDB
 DEFAULT CHARACTER SET = utf8
 COLLATE = utf8_bin;
 ```
 ##### Table for save client action
```
 CREATE TABLE client_action (
     id INT AUTO_INCREMENT PRIMARY KEY,
     action VARCHAR(255) NOT NULL,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
 );
 ```
 ##### Table for data update
```
 CREATE TABLE `converts_currency`.`config_date` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `date` DATE NULL,
   PRIMARY KEY (`id`));
```

### Used dependencies:
- spring-boot-starter-data-jpa
- spring-boot-starter-thymeleaf
- spring-boot-starter-web
- spring-boot-devtools
- h2database
- spring-boot-starter-test
- validation-api
