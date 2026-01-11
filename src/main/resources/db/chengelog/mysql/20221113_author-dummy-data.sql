-- liquibase formatted sql

-- changeset henry:20221113_insert_author_dummy_data

INSERT INTO author (name, description, birthdate, country, age, gender, image_url, created_date, modified_date, created_by, modified_by) VALUES
('J.K. Rowling', 'Author of the world-famous Harry Potter series', '1965-07-31', 'United Kingdom', 59, 'FEMALE', 'https://via.placeholder.com/300?text=JK+Rowling', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('George R.R. Martin', 'Fantasy author known for A Song of Ice and Fire', '1948-09-20', 'USA', 76, 'MALE', 'https://via.placeholder.com/300?text=GRRM', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Agatha Christie', 'The Queen of Mystery and Crime novels', '1890-01-15', 'United Kingdom', 132, 'FEMALE', 'https://via.placeholder.com/300?text=Agatha+Christie', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Isaac Asimov', 'Pioneer of science fiction literature', '1920-01-02', 'USA', 104, 'MALE', 'https://via.placeholder.com/300?text=Isaac+Asimov', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Stephen King', 'Master of horror and suspense', '1947-09-21', 'USA', 77, 'MALE', 'https://via.placeholder.com/300?text=Stephen+King', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Paulo Coelho', 'Author of The Alchemist', '1947-08-24', 'Brazil', 77, 'MALE', 'https://via.placeholder.com/300?text=Paulo+Coelho', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Chimamanda Ngozi Adichie', 'Contemporary African novelist', '1977-09-15', 'Nigeria', 47, 'FEMALE', 'https://via.placeholder.com/300?text=Chimamanda', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Malcolm Gladwell', 'Non-fiction writer and journalist', '1966-09-03', 'Canada', 58, 'MALE', 'https://via.placeholder.com/300?text=Malcolm+Gladwell', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Sarah J. Maas', 'Fantasy and romance author', '1986-03-05', 'USA', 38, 'FEMALE', 'https://via.placeholder.com/300?text=Sarah+J+Maas', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('Dan Brown', 'Thriller author known for The Da Vinci Code', '1964-06-22', 'USA', 60, 'MALE', 'https://via.placeholder.com/300?text=Dan+Brown', NOW(), NOW(), 'SYSTEM', 'SYSTEM');

