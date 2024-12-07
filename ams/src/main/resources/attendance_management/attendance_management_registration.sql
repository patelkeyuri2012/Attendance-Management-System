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
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `teacherid` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `contactnumber` char(10) NOT NULL,
  `dob` datetime(6) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `gvpemail` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `lname` varchar(255) NOT NULL,
  `mname` varchar(255) NOT NULL,
  `personalemail` varchar(255) NOT NULL,
  `qualification` varchar(255) NOT NULL,
  `departmentid` int DEFAULT NULL,
  `facultyid` int DEFAULT NULL,
  PRIMARY KEY (`teacherid`),
  KEY `FK2fftwvnhr655su87s9k49qh1t` (`departmentid`),
  KEY `FKfo5x1ngv3lg7gf6hsf3dtp6s4` (`facultyid`),
  CONSTRAINT `FK2fftwvnhr655su87s9k49qh1t` FOREIGN KEY (`departmentid`) REFERENCES `department` (`departmentid`),
  CONSTRAINT `FKfo5x1ngv3lg7gf6hsf3dtp6s4` FOREIGN KEY (`facultyid`) REFERENCES `faculty` (`facultyid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (1,'ahmedabad, gujrat','6282615143','1995-07-12 00:00:00.000000','yashvi','yashvi.gvp@gujaratvidyapith.org','/assets/img/user_20241122_164959.png','joshi','shailesh','yashvijoshi0395@gmail.com','BCA, MCA',1,1),(2,'vadodoara, gujrat','9823146372','1995-07-12 00:00:00.000000','diya','diya.gvp@gujaratvidyapith.org','/assets/img/user_20241118_074743.jpg','patel','ravikumar','diyap3459@gmail.com','BBA',2,3),(3,'vadodara, gujrat','9812341516','2002-12-20 00:00:00.000000','jamin','jamin.gvp@gujaratvidyapith.org','/assets/img/user_20241106_212309.jpg','patel','vijaybhai','pateljaimin2000@gmail.com','MBA, BBA',1,1),(4,'katch','9854321234','1987-02-02 00:00:00.000000','prey','prey.gvp@gujaratvidyapith.org','/assets/img/user_20241106_212309.jpg','vyas','vinodbhai','pateljainil2000@gmail.com','MScIT',5,2),(5,'surat','9812341516','1985-12-20 00:00:00.000000','richi','richi.gvp@gujaratvidyapith.org','/assets/img/user_20241106_212309.jpg','sharma','mohanbhai','patelrichi1999@gmail.com','BCA, MCA',1,1),(6,'ahmedabad','8712536121','1980-10-12 00:00:00.000000','kavya','kavya.gvp@gujaratvidyapith.org','/assets/img/user_20241106_212309.jpg','modi','kumarbhai','diyap3459@gmail.com','BCA',1,1),(7,'rajkot','7901827251','1982-06-12 00:00:00.000000','hetvi','hetvi.gvp@gujaratvidyapith.org','/assets/img/user_20241106_212309.jpg','patel','kamleshkumar','yashvijoshi0395@gmail.com','BCA, MCA',1,1);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-04 10:43:24
