-- liquibase formatted sql

-- changeset henry:20221115_insert_user_dummy_data

INSERT INTO user (first_name, last_name, email, password, phone_number, birthdate, country, age, gender, marital_status, image_url, created_date, modified_date, created_by, modified_by) VALUES
('John', 'Doe', 'john.doe@example.com', '$2a$10$cAaEdb381nMIUh16B0OpMOtKoslJuVLsma3vF7a9pUcBtHtKACYUK', '1234567890', '1990-01-15', 'USA', 34, 'MALE', 'SINGLE', 'https://via.placeholder.com/300?text=John+Doe', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Jane', 'Smith', 'jane.smith@example.com', '$2a$10$cAaEdb381nMIUh16B0OpMOtKoslJuVLsma3vF7a9pUcBtHtKACYUK', '1234567891', '1992-05-22', 'USA', 32, 'FEMALE', 'MARRIED', 'https://via.placeholder.com/300?text=Jane+Smith', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Michael', 'Johnson', 'michael.johnson@example.com', '$2a$10$cAaEdb381nMIUh16B0OpMOtKoslJuVLsma3vF7a9pUcBtHtKACYUK', '1234567892', '1988-08-30', 'UK', 36, 'MALE', 'IN_RELATIONSHIP', 'https://via.placeholder.com/300?text=Michael', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Emma', 'Williams', 'emma.williams@example.com', '$2a$10$cAaEdb381nMIUh16B0OpMOtKoslJuVLsma3vF7a9pUcBtHtKACYUK', '1234567893', '1995-03-10', 'Canada', 29, 'FEMALE', 'SINGLE', 'https://via.placeholder.com/300?text=Emma+Williams', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('David', 'Brown', 'david.brown@example.com', '$2a$10$cAaEdb381nMIUh16B0OpMOtKoslJuVLsma3vF7a9pUcBtHtKACYUK', '1234567894', '1991-11-20', 'Australia', 33, 'MALE', 'MARRIED', 'https://via.placeholder.com/300?text=David+Brown', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Sarah', 'Davis', 'sarah.davis@example.com', '$2a$10$cAaEdb381nMIUh16B0OpMOtKoslJuVLsma3vF7a9pUcBtHtKACYUK', '1234567895', '1993-07-05', 'USA', 31, 'FEMALE', 'SINGLE', 'https://via.placeholder.com/300?text=Sarah+Davis', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Robert', 'Miller', 'robert.miller@example.com', '$2a$10$cAaEdb381nMIUh16B0OpMOtKoslJuVLsma3vF7a9pUcBtHtKACYUK', '1234567896', '1989-09-12', 'USA', 35, 'MALE', 'MARRIED', 'https://via.placeholder.com/300?text=Robert', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Lisa', 'Wilson', 'lisa.wilson@example.com', '$2a$10$cAaEdb381nMIUh16B0OpMOtKoslJuVLsma3vF7a9pUcBtHtKACYUK', '1234567897', '1994-02-28', 'Germany', 30, 'FEMALE', 'IN_RELATIONSHIP', 'https://via.placeholder.com/300?text=Lisa+Wilson', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('James', 'Moore', 'james.moore@example.com', '$2a$10$cAaEdb381nMIUh16B0OpMOtKoslJuVLsma3vF7a9pUcBtHtKACYUK', '1234567898', '1987-12-18', 'USA', 37, 'MALE', 'SINGLE', 'https://via.placeholder.com/300?text=James+Moore', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Angela', 'Taylor', 'angela.taylor@example.com', '$2a$10$cAaEdb381nMIUh16B0OpMOtKoslJuVLsma3vF7a9pUcBtHtKACYUK', '1234567899', '1996-06-11', 'France', 28, 'FEMALE', 'MARRIED', 'https://via.placeholder.com/300?text=Angela+Taylor', NOW(), NOW(), 'SYSTEM', 'SYSTEM');

-- Note: All passwords are hashed 'password123' with bcrypt

