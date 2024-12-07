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
-- Table structure for table `studentleave`
--

DROP TABLE IF EXISTS `studentleave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentleave` (
  `leaveid` int NOT NULL AUTO_INCREMENT,
  `enddate` datetime(6) NOT NULL,
  `reason` text NOT NULL,
  `startdate` datetime(6) NOT NULL,
  `supportingdocumentpath` varchar(255) DEFAULT NULL,
  `leaveby` int DEFAULT NULL,
  `leavestatusid` int DEFAULT NULL,
  `leavetypeid` int DEFAULT NULL,
  PRIMARY KEY (`leaveid`),
  KEY `FKngmssttnai7vifgvvihuntu2i` (`leaveby`),
  KEY `FKslqx35wncg32ak1yuvs3q25b5` (`leavestatusid`),
  KEY `FKi92ec1u623bpp19y98xo56mm9` (`leavetypeid`),
  CONSTRAINT `FKi92ec1u623bpp19y98xo56mm9` FOREIGN KEY (`leavetypeid`) REFERENCES `leavetype` (`leavetypeid`),
  CONSTRAINT `FKngmssttnai7vifgvvihuntu2i` FOREIGN KEY (`leaveby`) REFERENCES `student` (`studentid`),
  CONSTRAINT `FKslqx35wncg32ak1yuvs3q25b5` FOREIGN KEY (`leavestatusid`) REFERENCES `leavestatus` (`leavestatusid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentleave`
--

LOCK TABLES `studentleave` WRITE;
/*!40000 ALTER TABLE `studentleave` DISABLE KEYS */;
INSERT INTO `studentleave` VALUES (1,'2024-11-25 00:00:00.000000','for marriage','2024-11-22 00:00:00.000000','assets\\doc\\user_20241123_073853.pdf',7,1,1),(2,'2024-12-03 00:00:00.000000','ebl leave','2024-11-30 00:00:00.000000','assets\\doc\\user_20241123_074343.pdf',6,1,5),(3,'2024-12-05 00:00:00.000000','ddncddgn','2024-11-26 00:00:00.000000','assets\\doc\\user_20241128_133606.pdf',6,1,2);
/*!40000 ALTER TABLE `studentleave` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-04 10:43:23
