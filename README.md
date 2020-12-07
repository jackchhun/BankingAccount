# Account Management

## Requirement

Mysql database


## Get started

Start with the default parameters. The application will create the table structure.

* Launch 01-createdb.sql script as database admin

* Launch the server API : java -jar target/BankingAccount-0.0.1-SNAPSHOT.jar

* Launch 03-populate.sql script as database codingadmin to populate the minimum of data


Start the service API and manually control the table structure

* Launch 01-createdb.sql script as database admin

* Launch 02-createtable.sql script as database codingadmin

* Launch 03-populate.sql script as database codingadmin to populate the minimum of data

* Launch the server API : java -jar target/BankingAccount-0.0.1-SNAPSHOT.jar --spring.jpa.hibernate.ddl-auto=none
