-- Drop tables if they exist
DROP TABLE IF EXISTS lottery CASCADE;
DROP TABLE IF EXISTS user_ticket CASCADE;

CREATE TABLE lottery (
    ticket_id VARCHAR(255) PRIMARY KEY,
    price DECIMAL NOT NULL,
    amount DECIMAL NOT NULL
);


CREATE TABLE user_ticket (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    ticket_id VARCHAR(255) REFERENCES lottery(ticket_id)
);