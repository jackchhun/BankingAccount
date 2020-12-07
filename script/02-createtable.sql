--
-- Launch the script as codingadmin or start server application with the option --spring.jpa.hibernate.ddl-auto=create
--



create table accountcd (
   id integer not null auto_increment,
    account_number varchar(16) not null,
    name varchar(128) not null,
    balance_id integer,
    customer_id integer not null,
    primary key (id)
) engine=InnoDB;

create table balancecd (
   id integer not null auto_increment,
    balance DECIMAL(10,2) default '0.00' not null,
    datetime TIMESTAMP not null,
    primary key (id)
) engine=InnoDB;

create table customercd (
   id integer not null auto_increment,
    email varchar(64) not null,
    name varchar(32) not null,
    surname varchar(32) not null,
    primary key (id)
) engine=InnoDB;

create table operationcd (
   id integer not null auto_increment,
    amount DECIMAL(10,2) default '0.00' not null,
    datetime TIMESTAMP not null,
    operation_type varchar(12) not null,
    title varchar(128) not null,
    account_id integer not null,
    primary key (id)
) engine=InnoDB;

alter table accountcd 
   add constraint UK_dwp6b8y1n2uu44dh37adyhgdv unique (account_number);

alter table accountcd 
   add constraint FK_account_balance 
   foreign key (balance_id) 
   references balancecd (id);

alter table accountcd 
   add constraint FK_account_customer 
   foreign key (customer_id) 
   references customercd (id);

alter table operationcd 
   add constraint FK_operation_account
   foreign key (account_id) 
   references accountcd (id);
