INSERT INTO address (id, address)
VALUES
    (0, '23883 Torphy Shoal Suite 636 Harrisburg, NE 69345'),
    (1, '943030, Самарская область, город Орехово-Зуево, шоссе Чехова, 16'),
    (2, '41 Evans SquareLake AnnaNG5 1HT'),
    (3, 'user address'),
    (4, 'admin address');

INSERT INTO "user" (id, username, password, first_name, last_name, email, phone_number, address_id)
VALUES
    -- https://fauxid.com/identity/38043731-3289-4ae2-9cef-7ae9283af896
    (0, 'shanahan.lavern', E'\'C?T4e\'@[Y[', 'Madie', 'Leannon', 'emmerich.rex@okeefe.com', '(680) 626-3764', 0),
    -- https://fauxid.com/identity/a353c55c-155b-4779-8aeb-952548b4b610
    (1, 'nazar15', E'(7$cE("&T+M6T&wP7A?', 'Константин', 'Колобов', 'rada.titov@rambler.ru', '+7 (922) 662-9075', 1),
    -- https://fauxid.com/identity/03dfaa0e-7a2b-4e97-8800-ac50803c4f11
    (2, 'king.william', E'}^9V+Hd!R!^', 'Luke', 'Anderson', 'sarah.wood@bailey.com', '+44(0)7985 776038', 2),
    -- password is: user
    (3, 'user', '$2a$10$Un96kfeh9NXrj/qIQ8TsJ.ICQBDGyyZpj20Ma0aX8sVjCwYK60EgK', 'Er', 'Us', 'user@user.user', '05312', 3),
    -- password is: admin
    (4, 'admin', '$2a$10$d.F2Ml/YmlUifT4iuFld4e8FazKYaf8mf3N4fkUlFnZZRZ/20dARy', 'Min', 'Ad', 'admin@onlineauction.app', '401', 4);

INSERT INTO "group" (id, name)
VALUES
    (0, 'administrator');

INSERT INTO user_group (user_id, group_id)
VALUES
    (4, 0);

INSERT INTO category (id, name)
VALUES
    (0, 'Cell Phones & Smartphones');

INSERT INTO item (id, name, description, condition, category_id, seller_id)
VALUES
    -- https://www.ebay.com/itm/174899817987?epid=10029467251&hash=item28b8d76e03:g:6NAAAOSwGx5hHmET&amdata=enc%3AAQAHAAAA4EOp4AAG9K6chSM0Ezl6BBN%2F8uVl64lJIHeQiDe%2BH0N7HocAHNwJxsazjwxPfqUhp1WTh00%2BqyCCZRwfgWNtPMh7hlqklGKmmiCK2BNxNlS0p%2FsYVMsGUZPpD%2BhVXR1f%2BRknozzLGKUAaeEvsyvC9ejRs0GEqs%2FTKvluqY2DKE6uOEykequnyZh0YHp4mHrU2WAIYvtkLoB87OXxoeAGYpqzRG9ngZrbUWBfR%2FEKYNnNOPvFeB%2BVOmDRMFMWj%2BI%2BCpc%2FIPO%2BkTS4KDlZFTp%2FTxD6mwzp8%2B%2BR1C%2Bh0H6ShTWd%7Ctkp%3ABFBMmNj2kJ5h&var=474054802899
    (0, 'Samsung Galaxy S10', 'Fully tested and restored to factory settings by our in house technicians. - Good – This product is in good cosmetic condition, there will be signs of wear which may include scratches, visible scuffs and/or screen discoloration but nothing that will impair functionality. Battery health will be a minimum of 80%. The item has been fully tested, restored to factory settings and is in excellent working order.', 'Good - Refurbished', 0, 0);

INSERT INTO lot (id, item_id, starting_price, minimum_bid_increment, auction_start, auction_end, winning_bid_id)
VALUES
    (0, 0, 115.0, 1.0, '2022-11-05 00:00:00+04', '2022-11-12 00:00:00+04', null);

INSERT INTO bid (id, lot_id, bidder_id, amount, time)
VALUES
    (0, 0, 1, 116.0, '2022-11-05 12:07:31+04'),
    (1, 0, 2, 117.0, '2022-11-05 15:45:05+04'),
    (2, 0, 1, 118.0, '2022-11-05 15:47:21+04'),
    (3, 0, 2, 119.0, '2022-11-06 07:13:02+04'),
    (4, 0, 1, 130.0, '2022-11-06 12:07:31+04');

UPDATE lot SET winning_bid_id = 4 WHERE id = 0;

-- https://wiki.postgresql.org/wiki/Fixing_Sequences
SELECT SETVAL('address_id_seq', (SELECT (MAX(id) + 1) from address), FALSE);
SELECT SETVAL('bid_id_seq', (SELECT (MAX(id) + 1) from bid), FALSE);
SELECT SETVAL('category_id_seq', (SELECT (MAX(id) + 1) from category), FALSE);
SELECT SETVAL('group_id_seq', (SELECT (MAX(id) + 1) from "group"), FALSE);
SELECT SETVAL('item_id_seq', (SELECT (MAX(id) + 1) from item), FALSE);
SELECT SETVAL('lot_id_seq', (SELECT (MAX(id) + 1) from lot), FALSE);
SELECT SETVAL('purchase_id_seq', (SELECT (MAX(id) + 1) from purchase), FALSE);
SELECT SETVAL('user_id_seq', (SELECT (MAX(id) + 1) from "user"), FALSE);