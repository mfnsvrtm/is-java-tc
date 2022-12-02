CREATE TABLE item (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    condition VARCHAR(50) NOT NULL,
    category_id SERIAL,
    seller_id SERIAL NOT NULL
);

CREATE TABLE lot (
    id SERIAL PRIMARY KEY,
    item_id SERIAL,
    starting_price DECIMAL(9, 2),
    minimum_bid_increment DECIMAL(9, 2),
    auction_start TIMESTAMPTZ NOT NULL,
    auction_end TIMESTAMPTZ NOT NULL,
    winning_bid_id SERIAL
);

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE bid (
    id SERIAL PRIMARY KEY,
    lot_id SERIAL NOT NULL,
    bidder_id SERIAL NOT NULL,
    amount DECIMAL(9, 2) NOT NULL,
    time TIMESTAMPTZ NOT NULL,
    retracted BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE purchase (
    id SERIAL PRIMARY KEY,
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
    address TEXT
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
ALTER TABLE lot ADD CONSTRAINT fk_lot_item FOREIGN KEY (item_id) REFERENCES item(id);
ALTER TABLE lot ADD CONSTRAINT fk_lot_bid FOREIGN KEY (winning_bid_id) REFERENCES bid(id);
ALTER TABLE bid ADD CONSTRAINT fk_bid_lot FOREIGN KEY (lot_id) REFERENCES lot(id);
ALTER TABLE bid ADD CONSTRAINT fk_bid_user FOREIGN KEY (bidder_id) REFERENCES "user"(id);
ALTER TABLE purchase ADD CONSTRAINT fk_purchase_bid FOREIGN KEY (bid_id) REFERENCES bid(id);
ALTER TABLE "user" ADD CONSTRAINT fk_user_address FOREIGN KEY (address_id) REFERENCES address(id);
ALTER TABLE user_group ADD CONSTRAINT fk_user_group_user FOREIGN KEY (user_id) REFERENCES "user"(id);
ALTER TABLE user_group ADD CONSTRAINT fk_user_group_group FOREIGN KEY (group_id) REFERENCES "group"(id);
