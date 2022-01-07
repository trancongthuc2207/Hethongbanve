-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: hethongbanve
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chuyendi`
--

DROP TABLE IF EXISTS `chuyendi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuyendi` (
  `MaChuyen` int NOT NULL,
  `TenChuyen` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Gia` double DEFAULT NULL,
  `ThoiGianBatDau` timestamp NOT NULL,
  `ThoiGianKetThuc` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`MaChuyen`,`ThoiGianBatDau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuyendi`
--

LOCK TABLES `chuyendi` WRITE;
/*!40000 ALTER TABLE `chuyendi` DISABLE KEYS */;
INSERT INTO `chuyendi` VALUES (1,'Hồ Chí Minh - Tây Ninh',80000,'2022-07-01 01:00:00','2022-07-01 03:15:00'),(2,'Hồ Chí Minh - Tây Ninh',80000,'2022-07-01 01:30:00','2022-07-01 03:45:00'),(3,'Hồ Chí Minh - Tây Ninh',80000,'2022-07-01 08:30:00','2022-07-01 10:45:00'),(4,'Hồ Chí Minh - Vị Thanh',150000,'2022-07-01 02:00:00','2022-07-01 07:30:00'),(5,'Hồ Chí Minh - Vị Thanh',150000,'2022-07-01 05:00:00','2022-07-01 10:30:00'),(6,'Hồ Chí Minh - Bến Tre',100000,'2022-07-01 03:00:00','2022-07-01 05:45:00'),(7,'Hồ Chí Minh - Tiền Giang',120000,'2022-07-01 08:00:00','2022-07-01 11:00:00'),(8,'Hồ Chí Minh - Long An',65000,'2022-07-01 02:30:00','2022-07-01 03:45:00'),(9,'Hồ Chí Minh - Bình Dương',95000,'2022-07-01 03:30:00','2022-07-01 05:00:00'),(10,'Hồ Chí Minh - Bình Dương',95000,'2022-07-01 06:15:00','2022-07-01 07:45:00'),(11,'Hồ Chí Minh - Đồng Nai',135000,'2022-07-01 02:15:00','2022-07-01 05:15:00'),(12,'Hồ Chí Minh - Bình Phước',180000,'2022-07-01 06:30:00','2022-07-01 09:00:00'),(13,'Hồ Chí Minh - Bình Thuận',220000,'2022-07-01 01:15:00','2022-07-01 06:15:00'),(14,'Hồ Chí Minh - Bình Thuận',220000,'2022-07-01 06:15:00','2022-07-01 11:15:00'),(15,'Hồ Chí Minh - Vũng Tàu',100000,'2022-07-01 10:00:00','2022-07-01 12:00:00');
/*!40000 ALTER TABLE `chuyendi` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-07 21:52:29
