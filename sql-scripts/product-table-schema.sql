CREATE TABLE product_master (
	ProductId INT NOT NULL AUTO_INCREMENT FIRST, 
	ProductName VARCHAR(50) NOT NULL,
	ProductPrice DOUBLE NULL,
	ProductImg BLOB NULL,
	ProductType VARCHAR(50) NOT NULL,
	ProductStock INT NOT NULL,
	ProductLabel VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (ProductId),
	UNIQUE INDEX ProductName (ProductName)
);

-----------ADD DUMMY PRODUCT FOR STARTING-------------------

INSERT INTO `product_master` (`ProductName`, `ProductPrice`, `ProductImg`, `ProductType`, `ProductStock`, `ProductLabel`)
VALUES
-- Basic products (core components)
('MCB', 150.00, NULL, 'basic', 25, 'In Stock'),
('SPD', 180.00, NULL, 'basic', 15, 'In Stock'),
('DC Fuse', 75.00, NULL, 'basic', 9, 'Low Stock'),
('Cable 4mm', 25.00, NULL, 'basic', 0, 'Out of Stock'),
('Connector MC4', 35.00, NULL, 'basic', 12, 'In Stock'),

-- Derived products (made from basic components)
('ACDB', 850.00, NULL, 'derived', 8, 'Low Stock'),
('DCDB', 950.00, NULL, 'derived', 5, 'Low Stock'),
('Combiner Box', 1450.00, NULL, 'derived', 0, 'Out of Stock'),
('String Monitoring Unit', 2450.00, NULL, 'derived', 11, 'In Stock'),
('Solar Junction Box', 1250.00, NULL, 'derived', 6, 'Low Stock');


--------CHANGE COLUMN NAME---------------

ALTER TABLE product_master
CHANGE COLUMN `ProductId` `product_id` INT NOT NULL AUTO_INCREMENT,
CHANGE COLUMN `ProductName` `product_name` VARCHAR(50) NOT NULL,
CHANGE COLUMN `ProductPrice` `product_price` DOUBLE,
CHANGE COLUMN `ProductImg` `product_img` BLOB,
CHANGE COLUMN `ProductType` `product_type` VARCHAR(50) NOT NULL,
CHANGE COLUMN `ProductStock` `product_stock` INT NOT NULL,
CHANGE COLUMN `ProductLabel` `product_label` VARCHAR(50) DEFAULT NULL;

