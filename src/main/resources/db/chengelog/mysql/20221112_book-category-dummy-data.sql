-- liquibase formatted sql

-- changeset henry:20221112_insert_book_category_dummy_data

INSERT INTO book_category (name, description, created_date, modified_date, created_by, modified_by) VALUES
('Science Fiction', 'Explore futuristic worlds and technological innovations', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Mystery', 'Thrilling detective stories and puzzles to solve', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Romance', 'Tales of love and relationships', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Fantasy', 'Magical worlds with dragons and wizards', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Thriller', 'Heart-pounding suspenseful stories', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Biography', 'Real-life stories of remarkable people', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('History', 'Historical accounts and events', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Self-Help', 'Books for personal growth and development', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Business', 'Professional and entrepreneurial guidance', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Children', 'Stories and adventures for young readers', NOW(), NOW(), 'SYSTEM', 'SYSTEM');

