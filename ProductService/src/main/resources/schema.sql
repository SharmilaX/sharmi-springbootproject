-- ============================================================
-- ProductService Database Schema
-- ============================================================

CREATE TABLE IF NOT EXISTS categories (
    category_id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_code   VARCHAR(50)  NOT NULL UNIQUE,
    category_name   VARCHAR(100) NOT NULL,
    description     VARCHAR(255),
    created_at      TIMESTAMP,
    created_by      VARCHAR(100),
    updated_at      TIMESTAMP,
    updated_by      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS sub_categories (
    sub_category_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id       BIGINT       NOT NULL,
    sub_category_code VARCHAR(50)  NOT NULL UNIQUE,
    sub_category_name VARCHAR(100) NOT NULL,
    description       VARCHAR(255),
    created_at        TIMESTAMP,
    created_by        VARCHAR(100),
    updated_at        TIMESTAMP,
    updated_by        VARCHAR(100),
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE IF NOT EXISTS products (
    product_id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    sub_category_id  BIGINT       NOT NULL,
    product_name     VARCHAR(100) NOT NULL,
    product_code     VARCHAR(50)  NOT NULL UNIQUE,
    description      VARCHAR(255),
    brand            VARCHAR(100),
    price            DOUBLE,
    status           VARCHAR(20),
    created_at       TIMESTAMP,
    created_by       VARCHAR(100),
    updated_at       TIMESTAMP,
    updated_by       VARCHAR(100),
    FOREIGN KEY (sub_category_id) REFERENCES sub_categories(sub_category_id)
);

CREATE TABLE IF NOT EXISTS skus (
    sku_id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id       BIGINT      NOT NULL,
    sku_code         VARCHAR(50) NOT NULL UNIQUE,
    color            VARCHAR(50),
    size             VARCHAR(50),
    stock_quantity   INT,
    sku_price        DOUBLE,
    status           VARCHAR(20),
    created_at       TIMESTAMP,
    created_by       VARCHAR(100),
    updated_at       TIMESTAMP,
    updated_by       VARCHAR(100),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);
