CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint AUTO_INCREMENT NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

INSERT INTO `user` (`username`, `password`, `email`) VALUES
  ("tester1", "p4ssw0rd", "tester1@example.org"),
  ("tester2", "p4ssw0rd", "tester1@example.org");
  
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint AUTO_INCREMENT NOT NULL,
  `item_code` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

INSERT INTO `product` (`item_code`, `description`, `price`) VALUES
  ("product1", "A product", "29,99"),
  ("product", "Another product", "19,99");

