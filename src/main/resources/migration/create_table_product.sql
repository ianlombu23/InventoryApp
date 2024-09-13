CREATE TABLE product (
  product_id int NOT NULL AUTO_INCREMENT,
  product_name varchar(100) NOT NULL,
  stock int NOT NULL,
  serial_number varchar(100) NOT NULL,
  additional_info json DEFAULT NULL,
  product_image longblob,
  created_at timestamp NOT NULL,
  created_by varchar(100) NOT NULL,
  updated_at timestamp NULL DEFAULT NULL,
  updated_by varchar(100) DEFAULT NULL,
  PRIMARY KEY (product_id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;