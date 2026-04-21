SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS agent;
DROP TABLE IF EXISTS my_business;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE my_business (
    business_id INT NOT NULL AUTO_INCREMENT,
    business_name VARCHAR(200) NOT NULL,
    street_address VARCHAR(250) NOT NULL,
    city VARCHAR(180),
    zipcode VARCHAR(30),
    PRIMARY KEY (business_id)
);

CREATE TABLE agent (
    agent_id INT NOT NULL AUTO_INCREMENT,
    business_id INT NOT NULL,
    agent_name VARCHAR(260) NOT NULL,
    PRIMARY KEY (agent_id),
    FOREIGN KEY (business_id) REFERENCES my_business (business_id) ON DELETE CASCADE
);

CREATE TABLE customer (
    customer_id INT NOT NULL AUTO_INCREMENT,
    agent_id INT NOT NULL,
    PRIMARY KEY (customer_id),
    FOREIGN KEY (agent_id) REFERENCES agent (agent_id) ON DELETE CASCADE
);

CREATE TABLE service (
    service_id INT NOT NULL AUTO_INCREMENT,
    customer_id INT NOT NULL,
    task VARCHAR(300),
    PRIMARY KEY (service_id),
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);