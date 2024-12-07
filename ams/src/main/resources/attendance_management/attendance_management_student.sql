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
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `studentid` int NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `contactnumber` char(10) NOT NULL,
  `createdby` varchar(255) NOT NULL,
  `createdon` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `entrollmentno` varchar(255) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `gvpemail` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `mname` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) NOT NULL,
  `modifiedon` datetime(6) NOT NULL,
  `password` varchar(255) NOT NULL,
  `departmentid` int DEFAULT NULL,
  `facultyid` int DEFAULT NULL,
  `programid` int DEFAULT NULL,
  `semesterid` int DEFAULT NULL,
  PRIMARY KEY (`studentid`),
  KEY `FK6t2f8w87kl6e5wx9h5qt86l0g` (`departmentid`),
  KEY `FK7swihpwrnrdhj55q4j0bmp3sx` (`facultyid`),
  KEY `FKctikvpf83nvfnkgai47abb1ki` (`programid`),
  KEY `FKn6jxb6sm73ytp5idwgfx0lva1` (`semesterid`),
  CONSTRAINT `FK6t2f8w87kl6e5wx9h5qt86l0g` FOREIGN KEY (`departmentid`) REFERENCES `department` (`departmentid`),
  CONSTRAINT `FK7swihpwrnrdhj55q4j0bmp3sx` FOREIGN KEY (`facultyid`) REFERENCES `faculty` (`facultyid`),
  CONSTRAINT `FKctikvpf83nvfnkgai47abb1ki` FOREIGN KEY (`programid`) REFERENCES `program` (`programid`),
  CONSTRAINT `FKn6jxb6sm73ytp5idwgfx0lva1` FOREIGN KEY (`semesterid`) REFERENCES `semester` (`semesterid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,_binary '','9876543210','1','2024-11-12 23:14:12.510000','patelkeyuri2002@gmail.com','212308001','Keyuri','212308001.gvp@gujaratvidyapith.org','Patel','Vijaybhai','1','2024-11-12 23:14:12.510000','vlS6Hhjto0',1,1,1,1),(2,_binary '','8712645127','1','2024-11-12 23:14:21.558000','pateljainil2000@gmail.com','212308002','Jainil','212308002.gvp@gujaratvidyapith.org','Shah','Darshndas','1','2024-11-12 23:14:21.558000','LnCC9z0luP',1,1,1,1),(3,_binary '','8710294827','1','2024-11-12 23:14:30.174000','raj11vora@gmail.com','212308003','Raj','212308003.gvp@gujaratvidyapith.org','Jain','Shubhbhai','1','2024-11-12 23:14:30.174000','QfYJpwaXPx',5,2,4,3),(4,_binary '','6918372971','1','2024-11-12 23:14:38.628000','patelpriti9428@gmail.com','212308004','Priti','212308004.gvp@gujaratvidyapith.org','Popat','Lalabhai','1','2024-11-12 23:14:38.628000','hxrbl5AWbH',5,2,4,3),(5,_binary '','7019283726','1','2024-11-12 23:14:47.258000','patelvijay00@gmail.com','212308005','Vijay','212308005.gvp@gujaratvidyapith.org','Panchal','Jaybhai','1','2024-11-12 23:14:47.258000','hrWcENwAjd',2,3,6,1),(6,_binary '','6102947262','1','2024-11-12 23:21:11.123000','patelrichi1999@gmail.com','212308006','Daxa','212308006.gvp@gujaratvidyapith.org','Jaidas','Prabhudas','1','2024-11-12 23:21:11.123000','qP8fPeaeAB',1,1,2,1),(7,_binary '','7101928628','1','2024-11-13 08:18:32.127000','pateljainil2000@gmail.com','212308007','Vikas','212308007.gvp@gujaratvidyapith.org','Patel','Vinodbhai','1','2024-11-13 08:18:32.127000','JDKanp8aPT',1,1,2,1),(8,_binary '','8019283182','1','2024-11-13 08:19:01.286000','diyap3459@gmail.com','212308008','Mahir','212308008.gvp@gujaratvidyapith.org','Vyas','Kaushalbhai','1','2024-11-13 08:19:01.286000','yUj8qj1gU4',2,3,6,1),(9,_binary '','9101372738','1','2024-11-13 08:19:11.977000','patelkeyuri2002@gmail.com','212308009','Jainil','212308009.gvp@gujaratvidyapith.org','Veda','Panditji','1','2024-11-13 08:19:11.977000','7lR5csVkH7',1,1,1,3),(10,_binary '','7101837428','1','2024-11-13 08:19:22.565000','yashvijoshi0395@gmail.com','212308010','Kreena','212308010.gvp@gujaratvidyapith.org','Kavaiya','Bnasidas','1','2024-11-13 08:19:22.565000','0ZcL6Jun91',1,1,1,3),(11,_binary '','8018264282','1','2024-11-13 08:19:33.101000','patelrichi1999@gmail.com','212308011','Mona','212308011.gvp@gujaratvidyapith.org','Vyas','Manankumar','1','2024-11-13 08:19:33.101000','npLfKX2bYk',1,1,1,1),(12,_binary '','6101827362','1','2024-11-13 08:19:40.364000','patelkeyuri2002@gmail.com','212308012','Pritesh','212308012.gvp@gujaratvidyapith.org','Shah','Visalbhai','1','2024-11-13 08:19:40.364000','lXZvHN92di',1,1,2,1),(13,_binary '','8172910273','1','2024-11-13 08:19:48.501000','212308013.gvp@gujaratvidyapith.org','212308013','Maya','212308013.gvp@gujaratvidyapith.org','Bhagat','Mohan','1','2024-11-13 08:21:31.750000','xc3CZJFTAs',1,1,3,3),(14,_binary '','8172910273','1','2024-11-13 08:19:59.989000','212308014.gvp@gujaratvidyapith.org','212308014','Neel','212308014.gvp@gujaratvidyapith.org','Bhagat','Mohan','1','2024-11-13 08:21:23.305000','SsSIwuZOlx',1,1,3,3);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-04 10:43:25
