CREATE TABLE IF NOT EXISTS `users` (
  `user_id` BIGINT  AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phoneNumber` varchar(50),
  `created_at` date,
  `created_by` varchar(50),
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL

);

CREATE TABLE IF NOT EXISTS `user_address` (

  `address_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `user_id` BIGINT,
  `address1` varchar(100) NOT NULl,
  `address2` varchar(100),
  `address3` varchar(200),
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `country` varchar(50),
  `created_at` date,
  `created_by` varchar(20),
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

