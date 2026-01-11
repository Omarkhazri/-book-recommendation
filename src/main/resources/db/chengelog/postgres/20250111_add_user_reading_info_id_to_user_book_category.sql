-- liquibase formatted sql

-- changeset khazri:20250111_add_user_reading_info_id_to_user_book_category

ALTER TABLE user_book_category ADD COLUMN user_reading_info_id BIGINT;

ALTER TABLE user_book_category
ADD CONSTRAINT user_book_category_user_reading_info_id_fk
FOREIGN KEY (user_reading_info_id) REFERENCES user_reading_info (id);


