CREATE DATABASE IF NOT EXISTS `mall_pms`;

USE `mall_pms`;

DROP TABLE IF EXISTS `pms_category`;

CREATE TABLE `pms_category` (
  `cat_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'category id',
  `name` char(50) DEFAULT NULL COMMENT 'category name',
  `parent_cid` bigint(20) DEFAULT NULL COMMENT 'parent cid',
  `cat_level` int(11) DEFAULT NULL COMMENT 'level',
  `show_status` tinyint(4) DEFAULT NULL COMMENT '[0-hide, 1-show]',
  `sort` int(11) DEFAULT NULL COMMENT 'sort',
  `icon` char(255) DEFAULT NULL COMMENT 'icon uri',
  `product_unit` char(50) DEFAULT NULL COMMENT 'unit of measurement',
  `product_count` int(11) DEFAULT NULL COMMENT 'count',
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='3 levels classification of products';

/*Data for the table `pms_category` */

insert  into `pms_category`(`cat_id`,`name`,`parent_cid`,`cat_level`,`show_status`,`sort`,`icon`,`product_unit`,`product_count`) 
	values 
	/*Level 1*/
	(1,'Electronic Devices',0,1,1,0,NULL,NULL,0),(2,'Electronic Accessories',0,1,1,0,NULL,NULL,0),(3,'TV & Home Appliances',0,1,1,0,NULL,NULL,0),(4,'Health & Beauty',0,1,1,0,NULL,NULL,0),
	(5,'Groceries & Pets',0,1,1,0,NULL,NULL,0),(6,'Home & Lifestyle',0,1,1,0,NULL,NULL,0),
	/*Level 2*/
	(7,'Mobiles',1,2,1,0,NULL,NULL,0),(8,'Tablets',1,2,1,0,NULL,NULL,0),(9,'Laptops',1,2,1,0,NULL,NULL,0),(10,'Desktops Computers',1,2,1,0,NULL,NULL,0),
	(11,'Gaming Consoles',1,2,1,0,NULL,NULL,0),(12,'Action/Video Cameras',1,2,1,0,NULL,NULL,0),(13,'Security Cameras',1,2,1,0,NULL,NULL,0),(14,'Digital Cameras',1,2,1,0,NULL,NULL,0),
	(15,'Gadgets & Drones',1,2,1,0,NULL,NULL,0),
	(16,'Mobile Accessories',2,2,1,0,NULL,NULL,0),(17,'Audio',2,2,1,0,NULL,NULL,0),(18,'Wearables',2,2,1,0,NULL,NULL,0),(19,'Console Accessories',2,2,1,0,NULL,NULL,0),
	(20,'Camera Accessories',2,2,1,0,NULL,NULL,0),(21,'Computer Accessories',2,2,1,0,NULL,NULL,0),(22,'Storage',2,2,1,0,NULL,NULL,0),(23,'Printers',2,2,1,0,NULL,NULL,0),
	(24,'Computer Components',2,2,1,0,NULL,NULL,0),(25,'Network Components',2,2,1,0,NULL,NULL,0),
	(26,'TV & Video Devices',3,2,1,0,NULL,NULL,0),(27,'Home Audio',3,2,1,0,NULL,NULL,0),(28,'TV Accessories',3,2,1,0,NULL,NULL,0),(29,'Large Appliances',3,2,1,0,NULL,NULL,0),
	(30,'Skincare',4,2,1,0,NULL,NULL,0),(31,'Make-Up',4,2,1,0,NULL,NULL,0),(32,'Hair Care',4,2,1,0,NULL,NULL,0),(33,'Bath & Body',4,2,1,0,NULL,NULL,0),
	(34,'Beer, Wines & Spirits',5,2,1,0,NULL,NULL,0),(35,'Drinks',5,2,1,0,NULL,NULL,0),
	(36,'Bedding',6,2,1,0,NULL,NULL,0),(37,'Furniture',6,2,1,0,NULL,NULL,0),
	/*Level 3*/
	(38,'Laptops & Notebooks',9,3,1,0,NULL,NULL,0),(39,'Gaming laptops',9,3,1,0,NULL,NULL,0),(40,'2-in-1s',9,3,1,0,NULL,NULL,0),
	(41,'All-In-One',10,3,1,0,NULL,NULL,0),(42,'Gaming Desktops',10,3,1,0,NULL,NULL,0),(43,'2-in-1s',10,3,1,0,NULL,NULL,0),
	(44,'Playstation Consoles',11,3,1,0,NULL,NULL,0),(45,'Playstation Games',11,3,1,0,NULL,NULL,0),(46,'Playstation Controllers',11,3,1,0,NULL,NULL,0),
	(47,'Sports & Action Camera',12,3,1,0,NULL,NULL,0),(48,'Video Camera',12,3,1,0,NULL,NULL,0),(49,'360 Cameras',12,3,1,0,NULL,NULL,0),(50,'Professional Video Camera',12,3,1,0,NULL,NULL,0),
	(51,'IP Security Cameras',13,3,1,0,NULL,NULL,0),(52,'CCTV Security Cameras',13,3,1,0,NULL,NULL,0),
	(53,'DSLR',14,3,1,0,NULL,NULL,0),(54,'Mirrorless',14,3,1,0,NULL,NULL,0),(55,'Point & Shoot',14,3,1,0,NULL,NULL,0),(56,'Instant Camera',14,3,1,0,NULL,NULL,0),
	(57,'Drones',15,3,1,0,NULL,NULL,0),(58,'Media Players',15,3,1,0,NULL,NULL,0),(59,'Walkie-Talkies',15,3,1,0,NULL,NULL,0),
	(60,'Phone Cases',16,3,1,0,NULL,NULL,0),(61,'Power Banks',16,3,1,0,NULL,NULL,0),(62,'Screen Protectors',16,3,1,0,NULL,NULL,0),(63,'Cables & Converters',16,3,1,0,NULL,NULL,0),(64,'Wall Chargers',16,3,1,0,NULL,NULL,0),(65,'Wireless Chargers',16,3,1,0,NULL,NULL,0),(66,'Tablet Accessories',16,3,1,0,NULL,NULL,0),
	(67,'Headphones & Headsets',17,3,1,0,NULL,NULL,0),(68,'Portable Speakers',17,3,1,0,NULL,NULL,0),(69,'Home Entertainment',17,3,1,0,NULL,NULL,0),
	(70,'Smartwatches',18,3,1,0,NULL,NULL,0),(71,'Smartwatches Accessories',18,3,1,0,NULL,NULL,0),(72,'Fitness & Activity Trackers',18,3,1,0,NULL,NULL,0),
	(73,'Playstation Controllers',19,3,1,0,NULL,NULL,0),(74,'Playstation Cable & Chargers',19,3,1,0,NULL,NULL,0),
	(75,'Memory Cards',20,3,1,0,NULL,NULL,0),(76,'Lenses',20,3,1,0,NULL,NULL,0),(77,'Tripods & Monopods',20,3,1,0,NULL,NULL,0),(78,'Camera Cases, Covers',20,3,1,0,NULL,NULL,0),
	(79,'Monitors',21,3,1,0,NULL,NULL,0),(80,'Keyboards',21,3,1,0,NULL,NULL,0),(81,'Mice',21,3,1,0,NULL,NULL,0),(82,'Mousepads',21,3,1,0,NULL,NULL,0),
	(83,'External Hard Drives',22,3,1,0,NULL,NULL,0),(84,'External Solid State Drives',22,3,1,0,NULL,NULL,0),
	(85,'Graphic Cards',24,3,1,0,NULL,NULL,0),(86,'Desktop Casings',24,3,1,0,NULL,NULL,0),(87,'Motherboards',24,3,1,0,NULL,NULL,0),(88,'Fans & Heatsinks',24,3,1,0,NULL,NULL,0),(89,'Power Supply Units',24,3,1,0,NULL,NULL,0),(90,'RAM',24,3,1,0,NULL,NULL,0),(91,'Processsors',24,3,1,0,NULL,NULL,0),
	(92,'Mobile Broadband',25,3,1,0,NULL,NULL,0),(93,'Modems',25,3,1,0,NULL,NULL,0),
	(94,'Projectors',26,3,1,0,NULL,NULL,0),(95,'Smart Televisions',26,3,1,0,NULL,NULL,0),(96,'Digital Televisions',26,3,1,0,NULL,NULL,0),
	(97,'Soundbars',27,3,1,0,NULL,NULL,0),(98,'Karaoke Systems',27,3,1,0,NULL,NULL,0),(99,'Home Entertainment',27,3,1,0,NULL,NULL,0),
	(100,'3D Glasses',28,3,1,0,NULL,NULL,0),
	(101,'Washing Machines',29,3,1,0,NULL,NULL,0),(102,'Laundry Dryer',29,3,1,0,NULL,NULL,0),(103,'Refrigerators',29,3,1,0,NULL,NULL,0),(104,'Microwave',29,3,1,0,NULL,NULL,0),(105,'Oven',29,3,1,0,NULL,NULL,0),
	(106,'Serum & Essence',30,3,1,0,NULL,NULL,0),(107,'Facial Moisturizers',30,3,1,0,NULL,NULL,0),(108,'Face Mask & Packs',30,3,1,0,NULL,NULL,0),(109,'Facial Cleansers',30,3,1,0,NULL,NULL,0),(110,'Toner & Mists',30,3,1,0,NULL,NULL,0),
	(111,'Lips',31,3,1,0,NULL,NULL,0),(112,'Face',31,3,1,0,NULL,NULL,0),(113,'Eyes',31,3,1,0,NULL,NULL,0),(114,'Makeup Palettes & Sets',31,3,1,0,NULL,NULL,0),(115,'Makeup Accessories',31,3,1,0,NULL,NULL,0),(116,'Makeup Removers',31,3,1,0,NULL,NULL,0),
	(117,'Shampoo',32,3,1,0,NULL,NULL,0),(118,'Conditioner',32,3,1,0,NULL,NULL,0),
	(119,'Body Wash',33,3,1,0,NULL,NULL,0),(120,'Body Moisturizers',33,3,1,0,NULL,NULL,0),(121,'Sun Care',33,3,1,0,NULL,NULL,0),
	(122,'Spirits',34,3,1,0,NULL,NULL,0),(123,'Beer',34,3,1,0,NULL,NULL,0),(124,'Wine',34,3,1,0,NULL,NULL,0),(125,'Soju & Korean Wine',34,3,1,0,NULL,NULL,0),(126,'Cider',34,3,1,0,NULL,NULL,0),(127,'Wine Accessories',34,3,1,0,NULL,NULL,0),(128,'Shochu & Umeshu',34,3,1,0,NULL,NULL,0),
	(129,'Soft Drinks',35,3,1,0,NULL,NULL,0),(130,'Water',35,3,1,0,NULL,NULL,0),(131,'Hot Chocolate Drinks',35,3,1,0,NULL,NULL,0),(132,'Coffee',35,3,1,0,NULL,NULL,0),(133,'Tea',35,3,1,0,NULL,NULL,0),(134,'Juices',35,3,1,0,NULL,NULL,0),(135,'Fresh Milk',35,3,1,0,NULL,NULL,0),
	(136,'Pillows & Bolsters',36,3,1,0,NULL,NULL,0),(137,'Mattress Protectors',36,3,1,0,NULL,NULL,0),(138,'Bed Sheets',36,3,1,0,NULL,NULL,0),(139,'Bedding Sets',36,3,1,0,NULL,NULL,0),(140,'Blankets',36,3,1,0,NULL,NULL,0),(141,'Mattresses',36,3,1,0,NULL,NULL,0),(142,'Mattress Pads',36,3,1,0,NULL,NULL,0),
	(143,'Bedroom Furniture',37,3,1,0,NULL,NULL,0),(144,'Living Room Furniture',37,3,1,0,NULL,NULL,0),(145,'Kitchen & Dining Furniture',37,3,1,0,NULL,NULL,0),(146,'Home Office Furniture',37,3,1,0,NULL,NULL,0);