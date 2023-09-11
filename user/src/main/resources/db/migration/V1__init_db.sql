CREATE TABLE roles
(
    id           VARCHAR(255) NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    name         VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id),
    CONSTRAINT uc_roles_name UNIQUE (name)
);

CREATE TABLE users
(
    id           VARCHAR(255) NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    username     VARCHAR(255),
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    password     VARCHAR(255),
    email        VARCHAR(255),
    phone_number VARCHAR(255),
    role_id      VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT FK_USERS_ON_ROLE_ID FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT uc_users_email UNIQUE (email),
    CONSTRAINT uc_users_phonenumber UNIQUE (phone_number),
    CONSTRAINT uc_users_username UNIQUE (username)
);


INSERT INTO roles (id, created_date, updated_date, name)
VALUES (gen_random_uuid(), current_timestamp, current_timestamp, 'USER');

INSERT INTO roles (id, created_date, updated_date, name)
VALUES (gen_random_uuid(), current_timestamp, current_timestamp, 'ADMIN');