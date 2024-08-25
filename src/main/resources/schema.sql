CREATE TABLE IF NOT EXISTS users
(
    user_id    BIGINT AUTO_INCREMENT PRIMARY KEY ,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    username   VARCHAR(50) NOT NULL UNIQUE,
    email      VARCHAR(50) NOT NULL UNIQUE,
    password   TEXT        NOT NULL);

CREATE TABLE IF NOT EXISTS roles
(
    role_id BIGINT auto_increment primary key,
    name    VARCHAR(50) NOT NULL CHECK (name IN ('ROLE_ADMIN', 'ROLE_USER'))
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT REFERENCES users (user_id) ,
    role_id BIGINT REFERENCES roles (role_id) ,
    CONSTRAINT users_roles_pk PRIMARY KEY (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS accounts
(
    account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bank_name TEXT,
    user_id BIGINT REFERENCES users (user_id) ,
    currency varchar(3) NOT NULL CHECK ( currency IN ('EUR', 'USD', 'RSD')) ,
    current_balance DOUBLE NOT NULL ,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS incomes
(
    income_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    income_date DATE NOT NULL,
    account_id BIGINT REFERENCES accounts (account_id) ,
    amount DOUBLE NOT NULL,
    payment_from TEXT,
    current_balance DOUBLE
);

CREATE TABLE IF NOT EXISTS expenses
(
    expense_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    expense_date DATE NOT NULL,
    account_id BIGINT REFERENCES accounts (account_id),
    amount DOUBLE NOT NULL,
    payment_to TEXT,
    current_balance DOUBLE
);

