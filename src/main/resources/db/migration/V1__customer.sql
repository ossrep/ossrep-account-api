CREATE TYPE customer_type AS ENUM ('INDIVIDUAL', 'BUSINESS');
CREATE CAST (varchar AS customer_type) WITH INOUT AS IMPLICIT;

CREATE TABLE customer
(
    customer_id   BIGSERIAL PRIMARY KEY,
    customer_type customer_type NOT NULL,
    legal_name    TEXT,
    prefix        TEXT,
    first_name    TEXT,
    middle_name   TEXT,
    last_name     TEXT,
    suffix        TEXT
);
ALTER SEQUENCE customer_customer_id_seq RESTART 1000000000000000000;