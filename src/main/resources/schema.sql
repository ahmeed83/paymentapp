DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS transaction;

create table customer
(
    id            BIGSERIAL   NOT NULL PRIMARY KEY,
    name          VARCHAR(50) NOT NULL,
    date_of_birth DATE        NOT NULL,
    gender        VARCHAR(50) NOT NULL
);

create table account
(
    id          BIGSERIAL        NOT NULL PRIMARY KEY,
    customer_id BIGINT           NOT NULL,
    name        VARCHAR(50)      NOT NULL,
    email       VARCHAR(50)      NOT NULL,
    balance     DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

create table transaction
(
    id              BIGSERIAL        NOT NULL PRIMARY KEY,
    account_id      BIGINT           NOT NULL,
    amount          DOUBLE PRECISION NOT NULL,
    To_account_name VARCHAR(50)      NOT NULL,
    FOREIGN KEY (account_id) REFERENCES account (id)
);

insert into customer (name, date_of_birth, gender)
values ('Amigo', '1985-08-30', 'Male');

insert into account (customer_id, name, email, balance)
values (1, 'Cash Account', 'amigo@google.com', 411.88);
insert into account (customer_id, name, email, balance)
values (1, 'Current Account', 'sfarrimond1@sourceforge.net', 2282.40);
insert into account (customer_id, name, email, balance)
values (1, 'Deposit No Account', 'kmouan2@istockphoto.com', 500.05);
insert into account (customer_id, name, email, balance)
values (1, 'Saving Account', 'mmartinho3@godaddy.com', 3458.96);
insert into account (customer_id, name, email, balance)
values (1, 'Loan Account', 'khavard4@hubpages.com', 3100.50);

insert into transaction (account_id, amount, To_account_name)
values (2, 218.42, 'Deposit No Account');
insert into transaction (account_id, amount, To_account_name)
values (1, 204.88, 'Loan Account');
insert into transaction (account_id, amount, To_account_name)
values (1, 74.99, 'Saving Account');
insert into transaction (account_id, amount, To_account_name)
values (1, 45.52, 'Loan Account');
insert into transaction (account_id, amount, To_account_name)
values (1, 90.86, 'Cash Account');
insert into transaction (account_id, amount, To_account_name)
values (2, 425.63, 'Cash Account');
insert into transaction (account_id, amount, To_account_name)
values (2, 183.07, 'Deposit No Account');
insert into transaction (account_id, amount, To_account_name)
values (4, 35.31, 'Deposit No Account');
insert into transaction (account_id, amount, To_account_name)
values (3, 269.59, 'Loan Account');
insert into transaction (account_id, amount, To_account_name)
values (5, 234.67, 'Cash Account');