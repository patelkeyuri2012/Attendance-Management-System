-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: attendance_management
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `departmentid` int NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `createdby` varchar(255) NOT NULL,
  `createdon` datetime(6) NOT NULL,
  `department` varchar(255) NOT NULL,
  `details` varchar(255) NOT NULL,
  `modifiedby` varchar(255) NOT NULL,
  `updatedon` datetime(6) NOT NULL,
  `facultyid` int DEFAULT NULL,
  PRIMARY KEY (`departmentid`),
  KEY `FKbuprolapm2ktbf57blnq4h64m` (`facultyid`),
  CONSTRAINT `FKbuprolapm2ktbf57blnq4h64m` FOREIGN KEY (`facultyid`) REFERENCES `faculty` (`facultyid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,_binary '','1','2024-10-13 20:03:32.000000','Computer','Computer Department','1','2024-10-13 20:03:32.000000',1),(2,_binary '','1','2024-10-13 20:03:32.000000','Management','Management Department','1','2024-10-13 20:03:32.000000',3),(3,_binary '','1','2024-10-13 20:03:32.000000','Kala & Arts','Department of Kala & Arts','1','2024-10-13 20:03:32.000000',4),(4,_binary '','1','2024-10-13 20:03:32.000000','Aadivasi','Aadivasi Department','1','2024-10-13 20:03:32.000000',4),(5,_binary '','1','2024-10-13 20:03:32.000000','Home Science','Home Science Department','1','2024-10-31 10:14:26.153000',2),(6,_binary '','1','2024-10-31 10:15:01.503000','Sport','Sport Department','1','2024-10-31 10:15:01.503000',5);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-04 10:43:22
