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
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `teacherid` int NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `contactno` char(10) NOT NULL,
  `createdby` varchar(255) NOT NULL,
  `createdon` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `mname` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) NOT NULL,
  `modifiedon` datetime(6) NOT NULL,
  `password` varchar(255) NOT NULL,
  `qualification` varchar(255) NOT NULL,
  `registrationid` varchar(255) NOT NULL,
  `departmentid` int DEFAULT NULL,
  `facultyid` int DEFAULT NULL,
  PRIMARY KEY (`teacherid`),
  KEY `FKltkmtolib56ou8qj2q0gelk7t` (`departmentid`),
  KEY `FKaq2ntjb7pk9kivtmpr6nskdv6` (`facultyid`),
  CONSTRAINT `FKaq2ntjb7pk9kivtmpr6nskdv6` FOREIGN KEY (`facultyid`) REFERENCES `faculty` (`facultyid`),
  CONSTRAINT `FKltkmtolib56ou8qj2q0gelk7t` FOREIGN KEY (`departmentid`) REFERENCES `department` (`departmentid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,_binary '','6282615143','1','2024-10-31 11:30:56.872000','yashvi.gvp@gujaratvidyapith.org','yashvi','joshi','shailesh','1','2024-11-10 09:18:10.067000','yashvi@28','mca','1',1,1),(2,_binary '','9823146372','1','2024-10-31 11:31:30.484000','diya.gvp@gujaratvidyapith.org','diya','patel','ravikumar','1','2024-11-03 20:59:11.177000','diyapatel@19','bca','2',1,1),(3,_binary '','9812341516','1','2024-11-03 22:46:22.948000','jamin.gvp@gujaratvidyapith.org','jamin','patel','vijaybhai','1','2024-11-03 22:46:22.948000','NXvlrNfGZE','mba, bba','3',2,3),(4,_binary '','9854321234','1','2024-11-04 09:46:42.200000','prey.gvp@gujaratvidyapith.org','prey','vyas','vinodbhai','1','2024-11-07 08:56:13.223000','prey@2002','MScIT','4',5,2),(5,_binary '','9812341516','1','2024-11-04 19:23:38.086000','richi.gvp@gujaratvidyapith.org','richi','sharma','mohanbhai','1','2024-11-07 08:56:25.389000','AOmM6cgOXC','BCA, MCA','5',1,1),(7,_binary '','8712536121','1','2024-11-04 19:29:58.539000','kavya.gvp@gujaratvidyapith.org','kavya','modi','kumarbhai','1','2024-11-06 22:38:19.084000','r5i7KNnHrJ','BCA','6',1,1),(8,_binary '','7901827251','1','2024-11-04 19:39:28.545000','hetvi.gvp@gujaratvidyapith.org','hetvi','patel','kamleshkumar','1','2024-11-07 08:56:38.432000','QXjxmyaaqf','BCA, MCA','7',1,1);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
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
