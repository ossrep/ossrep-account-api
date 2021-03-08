CREATE TABLE account_type (
    account_type_id         SERIAL PRIMARY KEY,
    name                    TEXT NOT NULL,
    description             TEXT NOT NULL
);
ALTER SEQUENCE account_type_account_type_id_seq RESTART 100000;

CREATE TABLE account (
    account_id              SERIAL PRIMARY KEY,
    account_type_id         INT NOT NULL,
    parent_account_id       INT,
    CONSTRAINT fk_account_type FOREIGN KEY (account_type_id) REFERENCES account_type (account_type_id),
    CONSTRAINT fk_parent_account FOREIGN KEY (parent_account_id) REFERENCES account (account_id)
);
ALTER SEQUENCE account_account_id_seq RESTART 100000;

CREATE TABLE contact_type (
    contact_type_id         SERIAL PRIMARY KEY,
    name                    TEXT NOT NULL,
    description             TEXT NOT NULL
);
ALTER SEQUENCE contact_type_contact_type_id_seq RESTART 100000;

CREATE TABLE contact (
    contact_id              SERIAL PRIMARY KEY,
    contact_type_id         INT NOT NULL,
    account_id              INT NOT NULL,
    first_name              TEXT NOT NULL,
    middle_name             TEXT,
    last_name               TEXT NOT NULL,
    suffix                  TEXT,
    email                   TEXT,
    phone                   TEXT,
    CONSTRAINT fk_contact_type FOREIGN KEY (contact_type_id) REFERENCES contact_type (contact_type_id),
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES account (account_id)
);
ALTER SEQUENCE contact_contact_id_seq RESTART 100000;

CREATE TABLE address_type (
    address_type_id         SERIAL PRIMARY KEY,
    name                    TEXT NOT NULL,
    description             TEXT NOT NULL
);
ALTER SEQUENCE address_type_address_type_id_seq RESTART 100000;

CREATE TABLE address (
    address_id              SERIAL PRIMARY KEY,
    address_type_id         INT NOT NULL,
    account_id              INT NOT NULL,
    address_line_1          TEXT NOT NULL,
    address_line_2          TEXT,
    city                    TEXT NOT NULL,
    state                   VARCHAR(2) NOT NULL,
    zip_code                VARCHAR(9) NOT NULL,
    CONSTRAINT fk_address_type FOREIGN KEY (address_type_id) REFERENCES address_type (address_type_id),
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES account (account_id)
);
ALTER SEQUENCE address_address_id_seq RESTART 100000;
