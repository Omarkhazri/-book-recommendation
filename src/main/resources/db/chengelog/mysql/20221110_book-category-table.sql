-- liquibase formatted sql

-- changeset henry:20221110_create_book_category_table

CREATE TABLE IF NOT EXISTS book_category
(
    id                BIGINT UNSIGNED        NOT NULL AUTO_INCREMENT,
    name              CHARACTER VARYING(50)  NOT NULL,
    description       CHARACTER VARYING(150) NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),
    CONSTRAINT book_category_name_unq UNIQUE KEY (name)
);