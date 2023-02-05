CREATE TABLE categories
(
    id         BIGSERIAL,
    name       VARCHAR,
    updated_at timestamp WITHOUT TIME ZONE,
    created_at timestamp WITHOUT TIME ZONE,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id   BIGSERIAL,
    name VARCHAR,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         BIGSERIAL,
    firstname  VARCHAR,
    lastname   VARCHAR,
    email      VARCHAR,
    password   VARCHAR,
    role       BIGINT,
    updated_at timestamp WITHOUT TIME ZONE,
    created_at timestamp WITHOUT TIME ZONE,
    CONSTRAINT pk_users PRIMARY KEY (id),
    FOREIGN KEY (role) REFERENCES roles (id)
);

CREATE TABLE products
(
    id          BIGSERIAL,
    author_id   BIGINT,
    customer_id BIGINT,
    category_id BIGINT,
    status      VARCHAR,
    name        VARCHAR,
    description TEXT,
    start_price DECIMAL,
    price       DECIMAL,
    duration    BIGINT,
    finished_at timestamp WITHOUT TIME ZONE,
    updated_at  timestamp WITHOUT TIME ZONE,
    created_at  timestamp WITHOUT TIME ZONE,
    CONSTRAINT pk_products PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES users(id),
    FOREIGN KEY (customer_id) REFERENCES users(id)
);

CREATE TABLE photos
(
    id         uuid,
    product_id BIGINT,
    filename   VARCHAR,
    deleted_at timestamp WITHOUT TIME ZONE,
    updated_at timestamp WITHOUT TIME ZONE,
    created_at timestamp WITHOUT TIME ZONE,
    CONSTRAINT pk_photos PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO categories (name, created_at, updated_at)
VALUES ('Антиквариат', now(), now()),
       ('Техника', now(), now()),
       ('Мебель', now(), now()),
       ('Недвижимость', now(), now()),
       ('Авто', now(), now());
INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');