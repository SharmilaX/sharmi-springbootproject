-- ============================================================
-- ProductService Dummy Data
-- ============================================================

-- Categories
-- Columns: category_id, category_code, category_name, description
INSERT INTO categories (category_id, category_code, category_name, description) VALUES
(1,  'ELEC',   'Electronics',            'All electronic devices and accessories'),
(2,  'FASH',   'Fashion',                'Clothing, footwear and accessories'),
(3,  'HOME',   'Home & Living',          'Furniture, decor and home appliances'),
(4,  'SPORT',  'Sports & Fitness',       'Sportswear and fitness equipment'),
(5,  'BEAUTY', 'Beauty & Personal Care', 'Skincare, haircare and grooming products'),
(6,  'FOOD',   'Food & Beverages',       'Groceries, snacks and drinks'),
(7,  'TOYS',   'Toys & Games',           'Toys, board games and outdoor play'),
(8,  'BOOKS',  'Books & Stationery',     'Books, notebooks and office supplies'),
(9,  'AUTO',   'Automotive',             'Car accessories and spare parts'),
(10, 'HEALTH', 'Health & Wellness',      'Vitamins, supplements and medical devices');

-- SubCategories
-- Columns: sub_category_id, category_id, sub_category_code, sub_category_name, description
INSERT INTO sub_categories (sub_category_id, category_id, sub_category_code, sub_category_name, description) VALUES
(1,  1,  'MOBILE',   'Mobile Phones',      'Smartphones and feature phones'),
(2,  1,  'LAPTOP',   'Laptops',            'Laptops and ultrabooks'),
(3,  2,  'MENS',     'Men''s Clothing',    'T-shirts, jeans and shirts for men'),
(4,  2,  'WOMENS',   'Women''s Clothing',  'Tops, dresses and ethnic wear for women'),
(5,  3,  'FURN',     'Furniture',          'Sofas, beds, tables and chairs'),
(6,  3,  'KITCHEN',  'Kitchen Appliances', 'Mixers, ovens and coffee makers'),
(7,  4,  'FITNESS',  'Fitness Equipment',  'Dumbbells, yoga mats and treadmills'),
(8,  5,  'SKINCARE', 'Skin Care',          'Moisturizers, serums and face wash'),
(9,  6,  'SNACKS',   'Snacks',             'Chips, biscuits and packaged snacks'),
(10, 8,  'FICTION',  'Fiction Books',      'Novels and short story collections');

-- Products
-- Columns: product_id, sub_category_id, product_name, product_code, description, brand, price, status
INSERT INTO products (product_id, sub_category_id, product_name, product_code, description, brand, price, status) VALUES
(1,  1,  'iPhone 15',              'PRD-APPL-001', '6.1 inch Super Retina XDR display',          'Apple',        99999.00, 'ACTIVE'),
(2,  1,  'Samsung Galaxy S24',     'PRD-SAMS-002', '6.2 inch Dynamic AMOLED 2X display',         'Samsung',      79999.00, 'ACTIVE'),
(3,  2,  'MacBook Pro 14',         'PRD-APPL-003', 'M3 chip with 14 inch Liquid Retina display',  'Apple',       199999.00, 'ACTIVE'),
(4,  2,  'Dell XPS 15',            'PRD-DELL-004', '15.6 inch OLED touch display laptop',         'Dell',        149999.00, 'ACTIVE'),
(5,  3,  'Levi''s 511 Jeans',     'PRD-LEVI-005', 'Slim fit stretchable denim jeans',            'Levi''s',       3999.00, 'ACTIVE'),
(6,  4,  'Nike Air Max 270',       'PRD-NIKE-006', 'Lightweight running and casual sneakers',     'Nike',          8999.00, 'ACTIVE'),
(7,  5,  'Wooden Sofa Set',        'PRD-HOME-007', '3+1+1 wooden sofa set with cushions',         'FurnitureMax', 35999.00, 'ACTIVE'),
(8,  7,  'Yoga Mat Pro',           'PRD-SPRT-008', 'Non-slip 6mm thick premium yoga mat',         'FitLife',       1299.00, 'ACTIVE'),
(9,  8,  'Neutrogena Moisturizer', 'PRD-NEUT-009', 'Oil-free daily hydrating face moisturizer',   'Neutrogena',     799.00, 'ACTIVE'),
(10, 10, 'The Alchemist',          'PRD-BOOK-010', 'A philosophical novel by Paulo Coelho',       'HarperCollins',  499.00, 'ACTIVE');

-- SKUs
-- Columns: sku_id, product_id, sku_code, color, size, stock_quantity, sku_price, status
INSERT INTO skus (sku_id, product_id, sku_code, color, size, stock_quantity, sku_price, status) VALUES
(1,  1,  'SKU-APPL-001-BLK-128', 'Black',  '128GB',    50,  99999.00,  'ACTIVE'),
(2,  1,  'SKU-APPL-001-WHT-256', 'White',  '256GB',    30,  109999.00, 'ACTIVE'),
(3,  2,  'SKU-SAMS-002-GRY-128', 'Gray',   '128GB',    40,  79999.00,  'ACTIVE'),
(4,  2,  'SKU-SAMS-002-BLK-256', 'Black',  '256GB',    20,  89999.00,  'ACTIVE'),
(5,  3,  'SKU-APPL-003-SLV-512', 'Silver', '512GB',    15,  199999.00, 'ACTIVE'),
(6,  4,  'SKU-DELL-004-GRY-512', 'Gray',   '512GB',    10,  149999.00, 'ACTIVE'),
(7,  5,  'SKU-LEVI-005-BLU-32',  'Blue',   '32',       100,  3999.00,  'ACTIVE'),
(8,  6,  'SKU-NIKE-006-BLK-9',   'Black',  'UK 9',     60,   8999.00,  'ACTIVE'),
(9,  8,  'SKU-SPRT-008-BLU-STD', 'Blue',   'Standard', 200,  1299.00,  'ACTIVE'),
(10, 9,  'SKU-NEUT-009-NA-50ML', 'N/A',    '50ml',     150,   799.00,  'ACTIVE');
