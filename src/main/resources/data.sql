-- SELECT * FROM express_store.user;

-- Insert data into user

INSERT INTO user VALUES ("b12d61ff-2261-11ef-ad97-f02f74a4b4a3", now(), "normaluser1@gmail.com", 0, 7564431201, "Vasu Aggarwal",
	"$2a$10$9BPk2WCNRcDSMJRe/1ER5.29nnUk.xy0YfEWqlXFbNZTmn7r/XJrG", "normal01", null);

INSERT INTO user VALUES ("b12e348a-2261-11ef-ad97-f02f74a4b4a3", now(), "normaluser2@gmail.com", 0, 7564431202, "Vinay Aggarwal",
	"$2a$10$9BPk2WCNRcDSMJRe/1ER5.29nnUk.xy0YfEWqlXFbNZTmn7r/XJrG", "normal02", null);

INSERT INTO user VALUES ("b12ee770-2261-11ef-ad97-f02f74a4b4a3", now(), "normaluser3@gmail.com", 0, 7564431203, "Rishi Raj Makkar",
"$2a$10$9BPk2WCNRcDSMJRe/1ER5.29nnUk.xy0YfEWqlXFbNZTmn7r/XJrG", "normal03", null);

INSERT INTO user VALUES ("b12f8946-2261-11ef-ad97-f02f74a4b4a3", now(), "normaluser4@gmail.com", 0, 7564431204, "Sayyam Jain",
"$2a$10$9BPk2WCNRcDSMJRe/1ER5.29nnUk.xy0YfEWqlXFbNZTmn7r/XJrG", "normal04", null);

INSERT INTO user VALUES ("b1301d67-2261-11ef-ad97-f02f74a4b4a3", now(), "normaluser5@gmail.com", 0, 7564431205, "Rohit Gupta",
"$2a$10$9BPk2WCNRcDSMJRe/1ER5.29nnUk.xy0YfEWqlXFbNZTmn7r/XJrG", "normal05", null);

INSERT INTO user VALUES ("b130d29b-2261-11ef-ad97-f02f74a4b4a3", now(), "adminuser1@gmail.com", 0, 8564431201, "Vasu Aggarwal",
"$2a$10$9BPk2WCNRcDSMJRe/1ER5.29nnUk.xy0YfEWqlXFbNZTmn7r/XJrG", "admin01", null);

INSERT INTO user VALUES ("b131566b-2261-11ef-ad97-f02f74a4b4a3", now(), "adminuser2@gmail.com", 0, 8564431202, "Rishi Raj",
"$2a$10$9BPk2WCNRcDSMJRe/1ER5.29nnUk.xy0YfEWqlXFbNZTmn7r/XJrG", "admin02", null);

-- Create roles
insert into role values (1, "ROLE_ADMIN");
insert into role values (2, "ROLE_NORMAL");
-- Define roles to user

INSERT INTO user_role VALUES ("normal01", 2);
INSERT INTO user_role VALUES ("normal02", 2);
INSERT INTO user_role VALUES ("normal03", 2);
INSERT INTO user_role VALUES ("normal04", 2);
INSERT INTO user_role VALUES ("normal05", 2);
INSERT INTO user_role VALUES ("admin01", 1);
INSERT INTO user_role VALUES ("admin02", 1);

-- Create coupons

INSERT INTO coupon VALUES (1, "2024-07-04 15:30:00", "Big Billion", 1, "CUSTOMER", now(), 10, 1500, 1000, now());
INSERT INTO coupon VALUES (2, "2024-07-04 15:30:00", "Big Dhamaka", 1, "CUSTOMER", now(), 20, 4000, 5000, now());
INSERT INTO coupon VALUES (3, "2024-07-04 15:30:00", "End of Season", 1, "CATEGORY", now(), 8, 1000, 500, now());
INSERT INTO coupon VALUES (4, "2024-07-04 15:30:00", "FRESH", 1, "CATEGORY", now(), 10, 1000, 700, now());
INSERT INTO coupon VALUES (5, "2024-07-04 15:30:00", "Great Indian", 1, "CUSTOMER", now(), 10, 1500, 1000, now());

-- Create categories
Insert into category VALUES (1, "Grocery", 1, 4);
Insert into category VALUES (2, "Fashion", 1, 1);
Insert into category VALUES (3, "Appliances", 1, 1);
Insert into category VALUES (4, "Mobiles", 1, 1);
Insert into category VALUES (5, "Electronics", 0, null);
Insert into category VALUES (6, "Home", 0, null);
Insert into category VALUES (7, "Furniture", 0, null);
Insert into category VALUES (8, "Personal Care", 0, null);
Insert into category VALUES (9, "Toys & Baby", 0, null);
Insert into category VALUES (10, "Sports", 0, null);
Insert into category VALUES (11, "Medicines", 0, null);

-- Add products
	-- grocery products
	INSERT INTO product VALUES (1, "Fresh apples imported directly from the farms of India", now(), 200, 1, now(), "img url", "Apples 500gm", 110.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (2, "Imported Bananas from Kerala", now(), 300, 1, now(), "img url", "Banana 6pcs", 35.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (3, "Indori Potato", now(), 100, 1, now(), "img url", "Potato 2kg", 40.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (4, "Onion", now(), 10, 1, now(), "img url", "Onion 1kg", 45.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");

	-- fashion products
	INSERT INTO product VALUES (5, "100% Cotton especially designed for Summers", now(), 20, 1, now(), "img url", "Octave Oversized TShirt Men", 799.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (6, "New Pattern Oversized TShirt (Unisex)", now(), 30, 1, now(), "img url", "SouledStore Oversized TShirt (Unisex)", 999.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (7, "Mango Crop top ", now(), 40, 1, now(), "img url", "Mango Crop Top", 1099.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	
	-- Appliance products
	INSERT INTO product VALUES (8, "Latest coil technology, smart environment temperature adjustment, Alexa + Google Voice Assistant in built, More Energy Savings", now(), 11, 1, now(), "img url", "LG Smart Inverter Air Conditioner", 38999.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (9, "Carrier Air Conditioner with power adjustment.", now(), 20, 1, now(), "img url", "Window Air Conditioner Carrier", 27999.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (10, "Bixby built in, AI enabled", now(), 100, 1, now(), "img url", "Double Door Fridge Samsung", 125000.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (11, "Voice Assistant built in, Magic remote included, Nano technology with 4k Display", now(), 150, 1, now(), "img url", "LG Nano83 65Inch", 85000.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	
	-- Mobile products
	INSERT INTO product VALUES (12, "Oneplus 12 8GB RAM | 256GB ROM | 50 Mega px Camera", now(), 21, 1, now(), "img url", "Oneplus 12", 65000.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (13, "Iphone 15 with dynamic island", now(), 21, 1, now(), "img url", "IPhone 15", 66000.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (14, "Iphone 15 Plus with 5000Mah Battery", now(), 21, 1, now(), "img url", "IPhone 15 Plus", 92000.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (15, "Latest Samsung S24 Ultra with 108 Mega px camera and 4500 Mah Battery.", now(), 21, 1, now(), "img url", "Samsung S24 Ultra", 124000, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	
	-- Electronic products
	INSERT INTO product VALUES (16, "Apple watch series 9 with hand gestures", now(), 10, 1, now(), "img url", "Apple Watch Series 9", 48999.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (17, "Spatial Audio | 42mm Drivers | Noise cancellation", now(), 20, 1, now(), "img url", "Realme Buds Air 5 Pro", 5899.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (18, "Gaming Laptop | NVIDIA 4070TI Graphics Card | 16GB RAM 512GB SSD", now(), 21, 1, now(), "img url", "ASUS ROG 4070T", 90000.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (19, "The premiumness without compomising ergonomics", now(), 20, 1, now(), "img url", "Logitech Master MX 3s Mouse", 9498.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	
	-- Home products
	INSERT INTO product VALUES (20, "Latest floral print portico bedsheet", now(), 21, 1, now(), "img url", "Portico Bedsheet", 1500.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (21, "Abstract are printed pillow covers", now(), 21, 1, now(), "img url", "Pillow Covers", 400.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	
	-- furniture products
	INSERT INTO product VALUES (22, "Natural Sheesham wood used", now(), 21, 1, now(), "img url", "TV Cabinet", 23000.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (23, "6 seater dining table with italian marble top", now(), 21, 1, now(), "img url", "Dining Table", 30000.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (24, "Height Adjustable workstation", now(), 21, 1, now(), "img url", "Study Table", 10000.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	
	-- sport products
	INSERT INTO product VALUES (25, "Graphite single piece badminton racquet", now(), 21, 1, now(), "img url", "Badminton Racquet", 2200.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (26, "High speed shuttle box", now(), 21, 1, now(), "img url", "Yonex Shuttle box", 650.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (27, "Focused for hitting long range", now(), 21, 1, now(), "img url", "Cricket season bat", 5500.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	INSERT INTO product VALUES (28, "Resistance band minimu", now(), 21, 1, now(), "img url", "Resistance Band 15KG", 899.00, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
	-- INSERT INTO product VALUES (29, "about", now(), 21, 1, now(), "img url", "name", price, "8dd9db3c-2236-11ef-ad97-f02f74a4b4a3");
	-- INSERT INTO product VALUES (30, "about", now(), 21, 1, now(), "img url", "name", price, "8dd9db3c-2236-11ef-ad97-f02f74a4b4a3");
	
-- Add product categories
INSERT INTO product_category VALUES (1, 1);
INSERT INTO product_category VALUES (2, 1);
INSERT INTO product_category VALUES (3, 1);
INSERT INTO product_category VALUES (4, 1);

INSERT INTO product_category VALUES (5, 2);
INSERT INTO product_category VALUES (6, 2);
INSERT INTO product_category VALUES (7, 2);

INSERT INTO product_category VALUES (8, 3);
INSERT INTO product_category VALUES (9, 3);
INSERT INTO product_category VALUES (10, 3);
INSERT INTO product_category VALUES (11, 3);

INSERT INTO product_category VALUES (12, 4);
INSERT INTO product_category VALUES (13, 4);
INSERT INTO product_category VALUES (14, 4);
INSERT INTO product_category VALUES (15, 4);

INSERT INTO product_category VALUES (16, 5);
INSERT INTO product_category VALUES (17, 5);
INSERT INTO product_category VALUES (18, 5);
INSERT INTO product_category VALUES (19, 5);

INSERT INTO product_category VALUES (20, 6);
INSERT INTO product_category VALUES (21, 6);

INSERT INTO product_category VALUES (22, 7);
INSERT INTO product_category VALUES (23, 7);
INSERT INTO product_category VALUES (24, 7);

INSERT INTO product_category VALUES (25, 10);
INSERT INTO product_category VALUES (26, 10);
INSERT INTO product_category VALUES (27, 10);
INSERT INTO product_category VALUES (28, 10);

insert into express_store.cart values (1, FALSE, now(), now(), NULL, NULL, "b12d61ff-2261-11ef-ad97-f02f74a4b4a3");
insert into express_store.cart values (2, FALSE, now(), now(), NULL, NULL, "b12e348a-2261-11ef-ad97-f02f74a4b4a3");
insert into express_store.cart values (3, FALSE, now(), now(), NULL, NULL, "b12ee770-2261-11ef-ad97-f02f74a4b4a3");
insert into express_store.cart values (4, FALSE, now(), now(), NULL, NULL, "b12f8946-2261-11ef-ad97-f02f74a4b4a3");
insert into express_store.cart values (5, FALSE, now(), now(), NULL, NULL, "b1301d67-2261-11ef-ad97-f02f74a4b4a3");
insert into express_store.cart values (6, FALSE, now(), now(), NULL, NULL, "b130d29b-2261-11ef-ad97-f02f74a4b4a3");
insert into express_store.cart values (7, FALSE, now(), now(), NULL, NULL, "b131566b-2261-11ef-ad97-f02f74a4b4a3");
