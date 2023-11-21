-- DROP SCHEMA IF EXISTS example_db CASCADE;
CREATE SCHEMA IF NOT EXISTS example_db;
CREATE TABLE IF NOT EXISTS example_db.USER (
    id INT GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email_address VARCHAR(255) NOT NULL,
    active VARCHAR(255) NOT NULL DEFAULT 'active',
    password_hash VARCHAR(255) NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(email_address),
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS example_db.ROLE (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS example_db.PRODUCT (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price float DEFAULT 0,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS example_db.PAYMENTPROFILE (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS example_db.PURCHASE (
    id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT,
    paymentprofile_id INT,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_purchase_user_id FOREIGN KEY(user_id) REFERENCES example_db.USER(id),
    CONSTRAINT fk_purchase_paymentprofile_id FOREIGN KEY(paymentprofile_id) REFERENCES example_db.PAYMENTPROFILE(id)
);
CREATE TABLE IF NOT EXISTS example_db.USERPROFILE (
    id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT,
    paymentprofile_id INT,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_userprofile_user_id FOREIGN KEY(user_id) REFERENCES example_db.USER(id),
    CONSTRAINT fk_userprofile_paymentprofile_id FOREIGN KEY(paymentprofile_id) REFERENCES example_db.PAYMENTPROFILE(id)
);
CREATE TABLE IF NOT EXISTS example_db.CART (
    id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT,
    status VARCHAR(255) NOT NULL DEFAULT 'active',
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_cart_user_id FOREIGN KEY(user_id) REFERENCES example_db.USER(id)
);
CREATE TABLE IF NOT EXISTS example_db.CARTITEM (
    id INT GENERATED ALWAYS AS IDENTITY,
    cart_id INT,
    product_id INT,
    quantity int DEFAULT 0,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_cartitem_cart_id FOREIGN KEY(cart_id) REFERENCES example_db.CART(id),
    CONSTRAINT fk_cartitem_product_id FOREIGN KEY(product_id) REFERENCES example_db.PRODUCT(id)
);
CREATE TABLE IF NOT EXISTS example_db.ORDER (
    id INT GENERATED ALWAYS AS IDENTITY,
    purchase_id INT,
    cart_id INT,
    user_id INT,
    status VARCHAR(255) NOT NULL DEFAULT 'active',
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_order_purchase_id FOREIGN KEY(purchase_id) REFERENCES example_db.PURCHASE(id),
    CONSTRAINT fk_order_cart_id FOREIGN KEY(cart_id) REFERENCES example_db.CART(id),
    CONSTRAINT fk_order_user_id FOREIGN KEY(user_id) REFERENCES example_db.USER(id)
);
CREATE TABLE IF NOT EXISTS example_db.ORDERITEM (
    id INT GENERATED ALWAYS AS IDENTITY,
    order_id INT,
    cartitem_id INT,
    price float DEFAULT 0,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_orderitem_order_id FOREIGN KEY(order_id) REFERENCES example_db.ORDER(id),
    CONSTRAINT fk_orderitem_cartitem_id FOREIGN KEY(cartitem_id) REFERENCES example_db.CARTITEM(id)
);
CREATE TABLE IF NOT EXISTS example_db.USERROLE (
    id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT,
    role_id INT,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_userrole_user_id FOREIGN KEY(user_id) REFERENCES example_db.USER(id),
    CONSTRAINT fk_userrole_role_id FOREIGN KEY(role_id) REFERENCES example_db.ROLE(id)
);
CREATE TABLE IF NOT EXISTS example_db.AUDITTRAIL (
    id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT,
    action VARCHAR(255) NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_audittrail_user_id FOREIGN KEY(user_id) REFERENCES example_db.USER(id)
);