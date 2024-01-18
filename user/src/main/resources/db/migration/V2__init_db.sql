CREATE TABLE IF NOT EXISTS roles
(
    id           UUID NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    created_id   UUID,
    updated_id   UUID,
    deleted      BOOLEAN,
    version      BIGINT,
    name         VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id),
    CONSTRAINT uc_roles_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS users
(
    id           UUID NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    created_id   UUID,
    updated_id   UUID,
    deleted      BOOLEAN,
    version      BIGINT,
    username     VARCHAR(255),
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    password     VARCHAR(255),
    email        VARCHAR(255),
    phone_number VARCHAR(255),
    role_id      UUID,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uc_users_email UNIQUE (email),
    CONSTRAINT uc_users_phonenumber UNIQUE (phone_number),
    CONSTRAINT uc_users_username UNIQUE (username),
    CONSTRAINT FK_USERS_ON_ROLE_ID FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO roles (id, created_date, updated_date, name, deleted, version)
VALUES (gen_random_uuid(), current_timestamp, current_timestamp, 'USER', false, 0) on conflict do nothing;

INSERT INTO roles (id, created_date, updated_date, name, deleted, version)
VALUES (gen_random_uuid(), current_timestamp, current_timestamp, 'ADMIN', false, 0) on conflict do nothing;