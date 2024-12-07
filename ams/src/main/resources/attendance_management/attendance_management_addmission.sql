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
-- Table structure for table `addmission`
--

DROP TABLE IF EXISTS `addmission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addmission` (
  `studentid` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `contactnumber` char(10) NOT NULL,
  `dob` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `entrollmentno` varchar(255) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `lname` varchar(255) NOT NULL,
  `mname` varchar(255) NOT NULL,
  `departmentid` int DEFAULT NULL,
  `facultyid` int DEFAULT NULL,
  `programid` int DEFAULT NULL,
  `semesterid` int DEFAULT NULL,
  PRIMARY KEY (`studentid`),
  KEY `FK8cqwrbtqm6ratw2mo0a5jlttu` (`departmentid`),
  KEY `FKfeqs88we58u00g1ho0xlbg41p` (`facultyid`),
  KEY `FKqnplowqsvabat1l9yjt5a28n9` (`programid`),
  KEY `FK4ujepvxy8r6s2haac988u10ns` (`semesterid`),
  CONSTRAINT `FK4ujepvxy8r6s2haac988u10ns` FOREIGN KEY (`semesterid`) REFERENCES `semester` (`semesterid`),
  CONSTRAINT `FK8cqwrbtqm6ratw2mo0a5jlttu` FOREIGN KEY (`departmentid`) REFERENCES `department` (`departmentid`),
  CONSTRAINT `FKfeqs88we58u00g1ho0xlbg41p` FOREIGN KEY (`facultyid`) REFERENCES `faculty` (`facultyid`),
  CONSTRAINT `FKqnplowqsvabat1l9yjt5a28n9` FOREIGN KEY (`programid`) REFERENCES `program` (`programid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addmission`
--

LOCK TABLES `addmission` WRITE;
/*!40000 ALTER TABLE `addmission` DISABLE KEYS */;
INSERT INTO `addmission` VALUES (1,'ahmedabad','9876543210','2000-01-01 00:00:00.000000','patelkeyuri2002@gmail.com','212308001','Keyuri','/assets/img/user_20241106_212114.jpg','Patel','Vijaybhai',1,1,1,1),(2,'vadodara','8712645127','2003-11-10 00:00:00.000000','pateljainil2000@gmail.com','212308002','Jainil','/assets/img/user_20241106_212114.jpg','Shah','Darshndas',1,1,1,1),(3,'surat','8710294827','2001-02-28 00:00:00.000000','raj11vora@gmail.com','212308003','Raj','/assets/img/user_20241106_212114.jpg','Jain','Shubhbhai',5,2,4,3),(4,'rajkot','6918372971','2003-12-04 00:00:00.000000','patelpriti9428@gmail.com','212308004','Priti','/assets/img/user_20241106_212114.jpg','Popat','Lalabhai',5,2,4,3),(5,'ahmedabad','7019283726','2002-06-15 00:00:00.000000','patelvijay00@gmail.com','212308005','Vijay','/assets/img/user_20241106_212114.jpg','Panchal','Jaybhai',2,3,6,1),(6,'bhavnagar','6102947262','2002-07-19 00:00:00.000000','patelrichi1999@gmail.com','212308006','Daxa','/assets/img/user_20241106_212114.jpg','Jaidas','Prabhudas',1,1,2,1),(7,'surat','7101928628','2004-05-21 00:00:00.000000','pateljainil2000@gmail.com','212308007','Vikas','/assets/img/user_20241106_212114.jpg','Patel','Vinodbhai',1,1,2,1),(8,'aanand','8019283182','2003-03-03 00:00:00.000000','diyap3459@gmail.com','212308008','Mahir','/assets/img/user_20241106_212114.jpg','Vyas','Kaushalbhai',2,3,6,1),(9,'katch','9101372738','2002-04-30 00:00:00.000000','patelkeyuri2002@gmail.com','212308009','Jainil','/assets/img/user_20241106_212114.jpg','Veda','Panditji',1,1,1,3),(10,'ahmedabad','7101837428','2001-04-01 00:00:00.000000','yashvijoshi0395@gmail.com','212308010','Kreena','/assets/img/user_20241106_212114.jpg','Kavaiya','Bnasidas',1,1,1,3),(11,'rajkot','8018264282','2003-01-25 00:00:00.000000','patelrichi1999@gmail.com','212308011','Mona','/assets/img/user_20241106_212114.jpg','Vyas','Manankumar',1,1,1,1),(12,'bharuch','6101827362','2002-10-17 00:00:00.000000','patelkeyuri2002@gmail.com','212308012','Pritesh','/assets/img/user_20241106_212114.jpg','Shah','Visalbhai',1,1,2,1),(13,'rajkot','8172910273','2002-03-29 00:00:00.000000','pateljainil2000@gmail.com','212308013','Maya','/assets/img/user_20241106_212114.jpg','Bhagat','Mohan',1,1,2,1),(14,'rajkot','8172910273','2002-03-29 00:00:00.000000','pateljainil2000@gmail.com','212308014','Neel','/assets/img/user_20241106_212114.jpg','Bhagat','Mohan',1,1,2,1);
/*!40000 ALTER TABLE `addmission` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-04 10:43:20
