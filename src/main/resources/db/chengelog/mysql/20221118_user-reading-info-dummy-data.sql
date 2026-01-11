-- liquibase formatted sql

-- changeset henry:20221118_insert_user_reading_info_dummy_data

INSERT INTO user_reading_info (user_id, reading_level, created_date, modified_date, created_by, modified_by) VALUES
(1, 'EXPERT', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
(2, 'EXPERT', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
(3, 'EXPERT', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
(4, 'INTERMEDIATE', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
(5, 'EXPERT', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
(6, 'BEGINNER', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
(7, 'EXPERT', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
(8, 'INTERMEDIATE', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
(9, 'EXPERT', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
(10, 'EXPERT', NOW(), NOW(), 'SYSTEM', 'SYSTEM');
