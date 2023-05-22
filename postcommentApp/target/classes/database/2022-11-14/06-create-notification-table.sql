--liquibase formatted sql
--changeset psamula:8
CREATE TABLE notification (
                              id BIGINT(20) NOT NULL,
                              user_id BIGINT(20) NOT NULL,
                              notification_content VARCHAR(255) NOT NULL,
                              PRIMARY KEY (id)
);