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
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `program` (
  `programid` int NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `createdby` varchar(255) NOT NULL,
  `createdon` datetime(6) NOT NULL,
  `details` varchar(255) NOT NULL,
  `modifiedby` varchar(255) NOT NULL,
  `program` varchar(255) NOT NULL,
  `updatedon` datetime(6) NOT NULL,
  `departmentid` int DEFAULT NULL,
  `facultyid` int DEFAULT NULL,
  `semesterid` int DEFAULT NULL,
  PRIMARY KEY (`programid`),
  KEY `FKd57667bkogmepcjv5vum9be7e` (`departmentid`),
  KEY `FKpbyj974yfe2g5mw6gtvph4534` (`facultyid`),
  KEY `FKqxr47sqfod3d3tvi13siny4aj` (`semesterid`),
  CONSTRAINT `FKd57667bkogmepcjv5vum9be7e` FOREIGN KEY (`departmentid`) REFERENCES `department` (`departmentid`),
  CONSTRAINT `FKpbyj974yfe2g5mw6gtvph4534` FOREIGN KEY (`facultyid`) REFERENCES `faculty` (`facultyid`),
  CONSTRAINT `FKqxr47sqfod3d3tvi13siny4aj` FOREIGN KEY (`semesterid`) REFERENCES `semester` (`semesterid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
INSERT INTO `program` VALUES (1,_binary '','1','2024-10-13 20:14:57.000000','Master of Computer Applications','1','MCA','2024-11-13 11:48:02.832000',1,1,4),(2,_binary '','1','2024-10-13 20:14:57.000000','Bachelor of Computer Applications','1','BCA','2024-10-13 20:14:57.000000',1,1,6),(3,_binary '','1','2024-10-13 20:14:57.000000','Post Graduate Diploma in Computer Applications','1','PGDCA','2024-10-13 20:14:57.000000',1,1,2),(4,_binary '','1','2024-10-13 20:14:57.000000','Master of Science in Information Technology','1','MSC - science','2024-11-03 20:47:19.759000',5,2,4),(5,_binary '','1','2024-10-13 20:14:57.000000','Bachelor of Science in Information Technology','1','BSC - science','2024-11-03 20:47:34.798000',5,2,6),(6,_binary '','1','2024-10-13 20:14:57.000000','Master of Business Administration','1','MBA','2024-10-13 20:14:57.000000',2,3,4),(7,_binary '','1','2024-10-13 20:14:57.000000','Bachelor of Business Administration','1','BBA','2024-10-13 20:14:57.000000',2,3,6),(8,_binary '','1','2024-10-13 20:14:57.000000','Bachelor of Arts','1','BA','2024-10-13 20:14:57.000000',3,4,6),(9,_binary '','1','2024-10-13 20:14:57.000000','Master of Arts','1','MA','2024-10-13 20:14:57.000000',3,4,4),(10,_binary '','1','2024-10-13 20:14:57.000000','Tribal Studies Program','1','Tribal Studies','2024-10-13 20:14:57.000000',4,4,2),(11,_binary '','1','2024-10-13 20:14:57.000000','Hindi Literature Program','1','Hindi Literature','2024-11-03 20:48:08.858000',4,4,2);
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
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
