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
-- Table structure for table `xe`
--

DROP TABLE IF EXISTS `xe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xe` (
  `MaXE` int NOT NULL,
  `TenXe` varchar(45) NOT NULL,
  `Bienso` varchar(45) NOT NULL,
  `Trangthai` int NOT NULL,
  `MaChuyen` int DEFAULT NULL,
  PRIMARY KEY (`MaXE`),
  UNIQUE KEY `MaXE_UNIQUE` (`MaXE`),
  KEY `MaXChuyen_idx` (`MaChuyen`),
  CONSTRAINT `MaXChuyen` FOREIGN KEY (`MaChuyen`) REFERENCES `chuyendi` (`MaChuyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xe`
--

LOCK TABLES `xe` WRITE;
/*!40000 ALTER TABLE `xe` DISABLE KEYS */;
INSERT INTO `xe` VALUES (1,'Ford Transit','51H-571.49',0,1),(2,'Ford Transit','51H-607.52',0,15),(3,'Ford Transit','51H-863.23',0,15),(4,'Toyota Hiace','51H-546.21',0,10),(5,'Toyota Hiace','51H-278.84',0,7),(6,'Toyota Hiace','51H-767.75',0,4),(7,'Toyota Hiace','51H-457.74',0,2),(8,'Nissan NV350 Urvan','51H-883.17',0,2),(9,'Nissan NV350 Urvan','51H-529.15',0,14),(10,'Nissan NV350 Urvan','51H-402.58',0,11),(11,'Mercedes-Benz Sprinter','51H-174.46',0,3),(12,'Mercedes-Benz Sprinter','51H-928.72',0,12),(13,'Mercedes-Benz Sprinter','51H-176.75',0,6),(14,'Mercedes-Benz Sprinter','51H-543.27',0,8),(15,'Mercedes-Benz Sprinter','51H-219.58',0,9),(16,'Hyundai Solati','51H-914.56',0,5),(17,'Hyundai Solati','51H-877.98',0,13),(18,'Hyundai Solati','51H-118.91',0,13),(19,'Hyundai Solati','51H-933.37',0,15),(20,'Ford Transit (2022)','51H-527.19',0,14),(21,'Ford Transit (2022)','51H-446.21',0,NULL),(22,'Nissan NV350 Urvan (2021)','51H-761.22',0,NULL),(23,'Nissan NV350 Urvan (2021)','51H-126.54',0,NULL),(24,'Toyota Hiace','51H-125.29',0,NULL);
/*!40000 ALTER TABLE `xe` ENABLE KEYS */;
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
