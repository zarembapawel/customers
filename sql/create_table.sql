CREATE TABLE IF NOT EXISTS customers.customers(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255),
    address VARCHAR(255),
    date_add DATETIME,
    date_update DATETIME
);