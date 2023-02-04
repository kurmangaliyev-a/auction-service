CREATE TABLE categories
(
    id         BIGSERIAL,
    name       VARCHAR,
    updated_at timestamp WITHOUT TIME ZONE,
    created_at timestamp WITHOUT TIME ZONE,
    CONSTRAINT pk_categories PRIMARY KEY (id)
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
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         BIGSERIAL,
    firstname  VARCHAR,
    lastname   VARCHAR,
    email      VARCHAR,
    password   VARCHAR,
    updated_at timestamp WITHOUT TIME ZONE,
    created_at timestamp WITHOUT TIME ZONE,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE photos
(
    id         uuid,
    product_id BIGINT,
    filename   VARCHAR,
    deleted_at timestamp WITHOUT TIME ZONE,
    updated_at timestamp WITHOUT TIME ZONE,
    created_at timestamp WITHOUT TIME ZONE,
    CONSTRAINT pk_photos PRIMARY KEY (id)
);