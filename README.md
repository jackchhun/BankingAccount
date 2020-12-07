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


## Service API Calls

##### Get History

http://localhost:8080/mybank/account/{accountNumber}/seehistory/{page}/{size}

* accountNumber : Account number of an existing account.
* page : number of page starting with 0.
* size : size of each page, max is set to 50 in the server side.

Example history call :

```
GET http://localhost:8080/mybank/account/23451238002/seehistory/0/20
```

Response in JSON format:

```
{
    "returnCode": "200",
    "returnMessage": "OK",
    "serviceCalled": "seehistory",
    "history": {
        "content": [
            {
                "datetime": "2020-12-07T09:40:50",
                "amount": 250.0,
                "title": "Eurostar ticket refund",
                "operationType": "Deposit"
            },
            {
                "datetime": "2020-12-07T09:41:16",
                "amount": -100.0,
                "title": "ATM",
                "operationType": "Withdraw"
            }
        ],
        "pageable": {
            "sort": {
                "sorted": false,
                "unsorted": true,
                "empty": true
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 20,
            "paged": true,
            "unpaged": false
        },
        "totalPages": 1,
        "totalElements": 2,
        "last": true,
        "size": 20,
        "number": 0,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "numberOfElements": 2,
        "first": true,
        "empty": false
    },
    "balance": {
        "accountNumber": "23451238002",
        "datetime": "2020-12-07T09:41:16",
        "balance": 150.0
    }
}
```


##### Make a deposit

http://localhost:8080/mybank/account/{accountNumber}/makedeposit

* accountNumber : Account number of an existing account.

Example history call :

```
POST http://localhost:8080/mybank/account/23451238002/makedeposit
```

Header to add to service call :

| Key | Value |
| --- | --- |
| Content-Type | application/json |
| Accept | application/json |

Example body :

```
{
    "datetime": "2020-11-30T09:30:00",
    "amount": 3500.00,
    "title": "Income"
}
```

Response in JSON format:

```
{
    "returnCode": "200",
    "returnMessage": "OK",
    "serviceCalled": "makedeposit",
    "history": null,
    "balance": null
}
```

##### Make a Withdrawal

http://localhost:8080/mybank/account/{accountNumber}/makewithdrawal

* accountNumber : Account number of an existing account.

Example history call :

```
POST http://localhost:8080/mybank/account/23451238002/makedeposit
```

Header to add to service call :

| Key | Value |
| --- | --- |
| Content-Type | application/json |
| Accept | application/json |


Example body :

```
{
    "datetime": "2020-12-06T09:30:00",
    "amount": 100.0,
    "title": "ATM"
}
```

Response in JSON format:

```
{
    "returnCode": "200",
    "returnMessage": "OK",
    "serviceCalled": "makedeposit",
    "history": null,
    "balance": null
}
```

