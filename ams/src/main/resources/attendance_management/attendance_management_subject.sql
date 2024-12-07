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
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subjectid` int NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `createdby` varchar(255) NOT NULL,
  `createdon` datetime(6) NOT NULL,
  `details` varchar(255) NOT NULL,
  `elective` bit(1) NOT NULL,
  `modifiedby` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `subjectcode` varchar(255) NOT NULL,
  `updatedon` datetime(6) NOT NULL,
  `departmentid` int DEFAULT NULL,
  `facultyid` int DEFAULT NULL,
  `programid` int DEFAULT NULL,
  `semesterid` int DEFAULT NULL,
  PRIMARY KEY (`subjectid`),
  KEY `FK3yp5yhipoheu0coop0uwdl82k` (`departmentid`),
  KEY `FK7ty1k6s8ul0ixgklg8257w76u` (`facultyid`),
  KEY `FK6ef6tah9kmjymu23y15bssb52` (`programid`),
  KEY `FK9hd889ajhkxygwcd79pjula1q` (`semesterid`),
  CONSTRAINT `FK3yp5yhipoheu0coop0uwdl82k` FOREIGN KEY (`departmentid`) REFERENCES `department` (`departmentid`),
  CONSTRAINT `FK6ef6tah9kmjymu23y15bssb52` FOREIGN KEY (`programid`) REFERENCES `program` (`programid`),
  CONSTRAINT `FK7ty1k6s8ul0ixgklg8257w76u` FOREIGN KEY (`facultyid`) REFERENCES `faculty` (`facultyid`),
  CONSTRAINT `FK9hd889ajhkxygwcd79pjula1q` FOREIGN KEY (`semesterid`) REFERENCES `semester` (`semesterid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,_binary '','1','2024-10-31 10:28:15.000000','Advanced Programming in C++',_binary '\0','1','Advanced Programming in C++','MCA-101','2024-10-31 10:28:15.000000',1,1,1,1),(2,_binary '','1','2024-10-31 10:28:15.000000','Data Structures',_binary '\0','1','Data Structures','MCA-102','2024-10-31 10:28:15.000000',1,1,1,1),(3,_binary '','1','2024-10-31 10:28:15.000000','Database Management Systems',_binary '\0','1','DBMS','MCA-103','2024-10-31 10:28:15.000000',1,1,1,1),(4,_binary '','1','2024-10-31 10:28:15.000000','Computer Networks',_binary '\0','1','Computer Networks','MCA-301','2024-11-07 08:54:45.677000',1,1,1,3),(5,_binary '','1','2024-10-31 10:28:15.000000','Operating Systems',_binary '\0','1','Operating Systems','MCA-302','2024-11-07 08:54:56.982000',1,1,1,3),(6,_binary '','1','2024-10-31 10:28:15.000000','Introduction to Computer Science',_binary '\0','1','Intro to Computer Science','BCA-101','2024-10-31 10:28:15.000000',1,1,2,1),(7,_binary '','1','2024-10-31 10:28:15.000000','Programming Fundamentals',_binary '','1','Programming Fundamentals','BCA-102','2024-11-12 10:04:48.044000',1,1,2,1),(8,_binary '','1','2024-10-31 10:28:15.000000','Mathematics for Computing',_binary '\0','1','Mathematics for Computing','BCA-103','2024-10-31 10:28:15.000000',1,1,2,1),(9,_binary '','1','2024-10-31 10:28:15.000000','Digital Logic Design',_binary '\0','1','Digital Logic Design','BCA-301','2024-11-07 08:55:07.342000',1,1,2,3),(10,_binary '','1','2024-10-31 10:28:15.000000','Web Development Basics',_binary '\0','1','Web Development Basics','BCA-302','2024-11-07 08:55:15.973000',1,1,2,3),(11,_binary '','1','2024-10-31 10:28:15.000000','Introduction to Programming',_binary '\0','1','Intro to Programming','PGDCA-101','2024-10-31 10:28:15.000000',1,1,3,1),(12,_binary '','1','2024-10-31 10:28:15.000000','Basics of Database Systems',_binary '\0','1','Basics of DBMS','PGDCA-102','2024-10-31 10:28:15.000000',1,1,3,1),(13,_binary '','1','2024-10-31 10:28:15.000000','Fundamentals of Web Development',_binary '\0','1','Web Fundamentals','PGDCA-103','2024-10-31 10:28:15.000000',1,1,3,1),(14,_binary '','1','2024-10-31 10:28:15.000000','Software Engineering',_binary '\0','1','Software Engineering','MSCIT-301','2024-11-07 08:55:26.317000',5,2,4,3),(15,_binary '','1','2024-10-31 10:28:15.000000','Advanced Java Programming',_binary '\0','1','Advanced Java','BSCIT-102','2024-11-03 20:50:30.562000',5,2,5,1),(16,_binary '','1','2024-10-31 10:28:15.000000','Data Mining',_binary '\0','1','Data Mining','MSCIT-301','2024-11-07 08:55:36.948000',5,2,4,3),(17,_binary '','1','2024-10-31 10:28:15.000000','Business Strategy',_binary '\0','1','Business Strategy','MBA-101','2024-10-31 10:28:15.000000',2,3,6,1),(18,_binary '','1','2024-10-31 10:28:15.000000','Financial Management',_binary '\0','1','Financial Management','MBA-102','2024-10-31 10:28:15.000000',2,3,6,1),(19,_binary '','1','2024-10-31 10:28:15.000000','Marketing Management',_binary '\0','1','Marketing Management','MBA-103','2024-10-31 10:28:15.000000',2,3,6,1),(20,_binary '','1','2024-10-31 10:28:15.000000','Introduction to Sociology',_binary '\0','1','Intro to Sociology','BA-101','2024-10-31 10:28:15.000000',3,4,8,1),(21,_binary '','1','2024-10-31 10:28:15.000000','Psychology Basics',_binary '\0','1','Psychology Basics','BA-102','2024-10-31 10:28:15.000000',3,4,8,1),(22,_binary '','1','2024-10-31 10:28:15.000000','World History',_binary '\0','1','World History','BA-103','2024-10-31 10:28:15.000000',3,4,8,1),(23,_binary '','1','2024-10-31 10:28:15.000000','Research Methodology',_binary '\0','1','Research Methodology','MA-101','2024-10-31 10:28:15.000000',3,4,9,1),(24,_binary '','1','2024-10-31 10:28:15.000000','Philosophy of Education',_binary '\0','1','Philosophy of Education','MA-102','2024-10-31 10:28:15.000000',3,4,9,1),(25,_binary '','1','2024-10-31 10:28:15.000000','Cultural Studies',_binary '\0','1','Cultural Studies','MA-103','2024-10-31 10:28:15.000000',3,4,9,1),(26,_binary '','1','2024-10-31 10:28:15.000000','Introduction to Tribal Studies',_binary '\0','1','Intro to Tribal Studies','TS-101','2024-10-31 10:28:15.000000',4,4,10,1),(27,_binary '','1','2024-10-31 10:28:15.000000','Tribal Anthropology',_binary '\0','1','Tribal Anthropology','TS-102','2024-10-31 10:28:15.000000',4,4,10,1),(28,_binary '','1','2024-10-31 10:28:15.000000','Cultural Heritage of Tribes',_binary '\0','1','Cultural Heritage','TS-103','2024-10-31 10:28:15.000000',4,4,10,1),(29,_binary '','1','2024-10-31 10:28:15.000000','Classical Hindi Literature',_binary '\0','1','Classical Hindi','HL-101','2024-11-03 20:49:46.650000',4,4,11,1),(30,_binary '','1','2024-10-31 10:28:15.000000','Modern Hindi Poetry',_binary '\0','1','Modern Hindi Poetry','HL-302','2024-11-07 08:55:49.309000',4,4,11,3),(31,_binary '','1','2024-10-31 10:28:15.000000','Hindi Prose',_binary '\0','1','Hindi Prose','HL-303','2024-11-07 08:55:58.129000',4,4,11,3),(32,_binary '','1','2024-11-11 22:18:02.734000','Cyber Security',_binary '\0','1','Cyber Security','BCA-301','2024-11-12 10:04:07.937000',1,1,2,3),(33,_binary '','1','2024-11-11 22:18:02.734000','Object Oriented',_binary '','1','Python','MCA-101','2024-11-12 10:04:23.028000',1,1,1,1),(34,_binary '','1','2024-11-11 22:18:02.734000','Internet of Things',_binary '\0','1','IoT','MCA-301','2024-11-11 22:18:02.734000',1,1,1,3),(35,_binary '','1','2024-11-13 08:11:51.000000','Programming Fundamentals',_binary '','1','Programming Concepts','BCA-102','2024-11-13 08:16:01.958000',1,1,2,1);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
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
