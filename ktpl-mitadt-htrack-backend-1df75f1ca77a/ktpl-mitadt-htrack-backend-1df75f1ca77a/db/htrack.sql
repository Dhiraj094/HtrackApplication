/*
SQLyog Enterprise - MySQL GUI v8.18 
MySQL - 8.0.23 : Database - htrack
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`htrack` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `htrack`;

/*Table structure for table `ht_health_history` */

DROP TABLE IF EXISTS `ht_health_history`;

CREATE TABLE `ht_health_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pulse` int DEFAULT NULL,
  `oxygen` int DEFAULT NULL,
  `record` datetime DEFAULT NULL,
  `ht_image_id` int DEFAULT NULL,
  `ht_user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ht_health_history_ht_image1_idx` (`ht_image_id`),
  KEY `fk_ht_health_history_ht_user1_idx` (`ht_user_id`),
  CONSTRAINT `fk_ht_health_history_ht_image1` FOREIGN KEY (`ht_image_id`) REFERENCES `ht_image` (`id`),
  CONSTRAINT `fk_ht_health_history_ht_user1` FOREIGN KEY (`ht_user_id`) REFERENCES `ht_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `ht_image` */

DROP TABLE IF EXISTS `ht_image`;

CREATE TABLE `ht_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` blob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `ht_user` */

DROP TABLE IF EXISTS `ht_user`;

CREATE TABLE `ht_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `gender` char(1) NOT NULL,
  `birhdate` date DEFAULT NULL,
  `blood_group` varchar(4) DEFAULT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
