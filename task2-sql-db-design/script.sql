CREATE TABLE item (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    condition VARCHAR(50) NOT NULL,
    category_id SERIAL,
    seller_id SERIAL NOT NULL,
    starting_price DECIMAL(9, 2),
    auction_start TIMESTAMPTZ NOT NULL,
    auction_end TIMESTAMPTZ
);

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE bid (
    id SERIAL PRIMARY KEY,
    item_id SERIAL NOT NULL,
    bidder_id SERIAL NOT NULL,
    amount DECIMAL(9, 2) NOT NULL,
    timer TIMESTAMPTZ NOT NULL
);

CREATE TABLE purchase (
    id SERIAL PRIMARY KEY,
    item_id SERIAL NOT NULL,
    bid_id SERIAL NOT NULL,
    amount DECIMAL(9, 2) NOT NULL,
    payment_method VARCHAR(30) NOT NULL,
    bank_account_number VARCHAR(100) NOT NULL
);

CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(30) NOT NULL,
    address_id SERIAL NOT NULL
);

CREATE TABLE address (
    id SERIAL PRIMARY KEY,
    country VARCHAR(30) NOT NULL,
    district VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    building VARCHAR(30) NOT NULL,
    apartment VARCHAR(10) NOT NULL,
    postal_code VARCHAR(30) NOT NULL
);

CREATE TABLE "group" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE user_group (
    user_id SERIAL NOT NULL,
    group_id SERIAL NOT NULL,
    PRIMARY KEY (user_id, group_id)
);

ALTER TABLE item ADD CONSTRAINT fk_item_category FOREIGN KEY (category_id) REFERENCES category(id);
ALTER TABLE item ADD CONSTRAINT fk_item_user FOREIGN KEY (seller_id) REFERENCES "user"(id);
ALTER TABLE bid ADD CONSTRAINT fk_bid_item FOREIGN KEY (item_id) REFERENCES item(id);
ALTER TABLE bid ADD CONSTRAINT fk_bid_user FOREIGN KEY (bidder_id) REFERENCES "user"(id);
ALTER TABLE purchase ADD CONSTRAINT fk_purchase_item FOREIGN KEY (item_id) REFERENCES item(id);
ALTER TABLE purchase ADD CONSTRAINT fk_purchase_bid FOREIGN KEY (bid_id) REFERENCES bid(id);
ALTER TABLE "user" ADD CONSTRAINT fk_user_address FOREIGN KEY (address_id) REFERENCES address(id);
ALTER TABLE user_group ADD CONSTRAINT fk_user_group_user FOREIGN KEY (user_id) REFERENCES "user"(id);
ALTER TABLE user_group ADD CONSTRAINT fk_user_group_group FOREIGN KEY (group_id) REFERENCES "group"(id);