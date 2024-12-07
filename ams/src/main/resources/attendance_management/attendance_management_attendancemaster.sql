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
-- Table structure for table `attendancemaster`
--

DROP TABLE IF EXISTS `attendancemaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendancemaster` (
  `attendancemasterid` int NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `departmentid` int DEFAULT NULL,
  `programid` int DEFAULT NULL,
  `semesterid` int DEFAULT NULL,
  `subjectid` int DEFAULT NULL,
  `teacherid` int DEFAULT NULL,
  `timetableid` int DEFAULT NULL,
  PRIMARY KEY (`attendancemasterid`),
  KEY `FKt6d8xk977sub1eoe1ffxmdt72` (`departmentid`),
  KEY `FKq0bie3octfq1n99738t14od0n` (`programid`),
  KEY `FKshw63tdqnkl6mj5yni009blxv` (`semesterid`),
  KEY `FKq00s45tqt50ibyijtyqnkouhy` (`subjectid`),
  KEY `FK2rsv80othu4oi44x7i6vjdyr4` (`teacherid`),
  KEY `FKh798hxsre6easyuccj1vr22la` (`timetableid`),
  CONSTRAINT `FK2rsv80othu4oi44x7i6vjdyr4` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`),
  CONSTRAINT `FKh798hxsre6easyuccj1vr22la` FOREIGN KEY (`timetableid`) REFERENCES `timetable` (`timetableid`),
  CONSTRAINT `FKq00s45tqt50ibyijtyqnkouhy` FOREIGN KEY (`subjectid`) REFERENCES `subject` (`subjectid`),
  CONSTRAINT `FKq0bie3octfq1n99738t14od0n` FOREIGN KEY (`programid`) REFERENCES `program` (`programid`),
  CONSTRAINT `FKshw63tdqnkl6mj5yni009blxv` FOREIGN KEY (`semesterid`) REFERENCES `semester` (`semesterid`),
  CONSTRAINT `FKt6d8xk977sub1eoe1ffxmdt72` FOREIGN KEY (`departmentid`) REFERENCES `department` (`departmentid`)
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendancemaster`
--

LOCK TABLES `attendancemaster` WRITE;
/*!40000 ALTER TABLE `attendancemaster` DISABLE KEYS */;
INSERT INTO `attendancemaster` VALUES (1,'2024-07-05 00:00:00.000000',1,2,1,6,1,1),(2,'2024-07-12 00:00:00.000000',1,2,1,6,1,1),(3,'2024-07-19 00:00:00.000000',1,2,1,6,1,1),(4,'2024-07-26 00:00:00.000000',1,2,1,6,1,1),(5,'2024-08-02 00:00:00.000000',1,2,1,6,1,1),(6,'2024-08-09 00:00:00.000000',1,2,1,6,1,1),(7,'2024-08-16 00:00:00.000000',1,2,1,6,1,1),(8,'2024-08-23 00:00:00.000000',1,2,1,6,1,1),(9,'2024-08-30 00:00:00.000000',1,2,1,6,1,1),(10,'2024-09-06 00:00:00.000000',1,2,1,6,1,1),(11,'2024-09-13 00:00:00.000000',1,2,1,6,1,1),(12,'2024-09-20 00:00:00.000000',1,2,1,6,1,1),(13,'2024-09-27 00:00:00.000000',1,2,1,6,1,1),(14,'2024-10-04 00:00:00.000000',1,2,1,6,1,1),(15,'2024-10-11 00:00:00.000000',1,2,1,6,1,1),(16,'2024-10-18 00:00:00.000000',1,2,1,6,1,1),(17,'2024-10-25 00:00:00.000000',1,2,1,6,1,1),(18,'2024-11-01 00:00:00.000000',1,2,1,6,1,1),(19,'2024-11-08 00:00:00.000000',1,2,1,6,1,1),(20,'2024-11-15 00:00:00.000000',1,2,1,6,1,1),(21,'2024-11-22 00:00:00.000000',1,2,1,6,1,1),(22,'2024-11-29 00:00:00.000000',1,2,1,6,1,1),(23,'2024-12-06 00:00:00.000000',1,2,1,6,1,1),(24,'2024-12-13 00:00:00.000000',1,2,1,6,1,1),(25,'2024-12-20 00:00:00.000000',1,2,1,6,1,1),(26,'2024-12-27 00:00:00.000000',1,2,1,6,1,1),(27,'2024-07-05 00:00:00.000000',1,1,1,1,2,2),(28,'2024-07-12 00:00:00.000000',1,1,1,1,2,2),(29,'2024-07-19 00:00:00.000000',1,1,1,1,2,2),(30,'2024-07-26 00:00:00.000000',1,1,1,1,2,2),(31,'2024-08-02 00:00:00.000000',1,1,1,1,2,2),(32,'2024-08-09 00:00:00.000000',1,1,1,1,2,2),(33,'2024-08-16 00:00:00.000000',1,1,1,1,2,2),(34,'2024-08-23 00:00:00.000000',1,1,1,1,2,2),(35,'2024-08-30 00:00:00.000000',1,1,1,1,2,2),(36,'2024-09-06 00:00:00.000000',1,1,1,1,2,2),(37,'2024-09-13 00:00:00.000000',1,1,1,1,2,2),(38,'2024-09-20 00:00:00.000000',1,1,1,1,2,2),(39,'2024-09-27 00:00:00.000000',1,1,1,1,2,2),(40,'2024-10-04 00:00:00.000000',1,1,1,1,2,2),(41,'2024-10-11 00:00:00.000000',1,1,1,1,2,2),(42,'2024-10-18 00:00:00.000000',1,1,1,1,2,2),(43,'2024-10-25 00:00:00.000000',1,1,1,1,2,2),(44,'2024-11-01 00:00:00.000000',1,1,1,1,2,2),(45,'2024-11-08 00:00:00.000000',1,1,1,1,2,2),(46,'2024-11-15 00:00:00.000000',1,1,1,1,2,2),(47,'2024-11-22 00:00:00.000000',1,1,1,1,2,2),(48,'2024-11-29 00:00:00.000000',1,1,1,1,2,2),(49,'2024-12-06 00:00:00.000000',1,1,1,1,2,2),(50,'2024-12-13 00:00:00.000000',1,1,1,1,2,2),(51,'2024-12-20 00:00:00.000000',1,1,1,1,2,2),(52,'2024-12-27 00:00:00.000000',1,1,1,1,2,2),(53,'2024-07-05 00:00:00.000000',1,2,1,7,2,3),(54,'2024-07-12 00:00:00.000000',1,2,1,7,2,3),(55,'2024-07-19 00:00:00.000000',1,2,1,7,2,3),(56,'2024-07-26 00:00:00.000000',1,2,1,7,2,3),(57,'2024-08-02 00:00:00.000000',1,2,1,7,2,3),(58,'2024-08-09 00:00:00.000000',1,2,1,7,2,3),(59,'2024-08-16 00:00:00.000000',1,2,1,7,2,3),(60,'2024-08-23 00:00:00.000000',1,2,1,7,2,3),(61,'2024-08-30 00:00:00.000000',1,2,1,7,2,3),(62,'2024-09-06 00:00:00.000000',1,2,1,7,2,3),(63,'2024-09-13 00:00:00.000000',1,2,1,7,2,3),(64,'2024-09-20 00:00:00.000000',1,2,1,7,2,3),(65,'2024-09-27 00:00:00.000000',1,2,1,7,2,3),(66,'2024-10-04 00:00:00.000000',1,2,1,7,2,3),(67,'2024-10-11 00:00:00.000000',1,2,1,7,2,3),(68,'2024-10-18 00:00:00.000000',1,2,1,7,2,3),(69,'2024-10-25 00:00:00.000000',1,2,1,7,2,3),(70,'2024-11-01 00:00:00.000000',1,2,1,7,2,3),(71,'2024-11-08 00:00:00.000000',1,2,1,7,2,3),(72,'2024-11-15 00:00:00.000000',1,2,1,7,2,3),(73,'2024-11-22 00:00:00.000000',1,2,1,7,2,3),(74,'2024-11-29 00:00:00.000000',1,2,1,7,2,3),(75,'2024-12-06 00:00:00.000000',1,2,1,7,2,3),(76,'2024-12-13 00:00:00.000000',1,2,1,7,2,3),(77,'2024-12-20 00:00:00.000000',1,2,1,7,2,3),(78,'2024-12-27 00:00:00.000000',1,2,1,7,2,3),(79,'2024-07-05 00:00:00.000000',1,1,1,2,1,4),(80,'2024-07-12 00:00:00.000000',1,1,1,2,1,4),(81,'2024-07-19 00:00:00.000000',1,1,1,2,1,4),(82,'2024-07-26 00:00:00.000000',1,1,1,2,1,4),(83,'2024-08-02 00:00:00.000000',1,1,1,2,1,4),(84,'2024-08-09 00:00:00.000000',1,1,1,2,1,4),(85,'2024-08-16 00:00:00.000000',1,1,1,2,1,4),(86,'2024-08-23 00:00:00.000000',1,1,1,2,1,4),(87,'2024-08-30 00:00:00.000000',1,1,1,2,1,4),(88,'2024-09-06 00:00:00.000000',1,1,1,2,1,4),(89,'2024-09-13 00:00:00.000000',1,1,1,2,1,4),(90,'2024-09-20 00:00:00.000000',1,1,1,2,1,4),(91,'2024-09-27 00:00:00.000000',1,1,1,2,1,4),(92,'2024-10-04 00:00:00.000000',1,1,1,2,1,4),(93,'2024-10-11 00:00:00.000000',1,1,1,2,1,4),(94,'2024-10-18 00:00:00.000000',1,1,1,2,1,4),(95,'2024-10-25 00:00:00.000000',1,1,1,2,1,4),(96,'2024-11-01 00:00:00.000000',1,1,1,2,1,4),(97,'2024-11-08 00:00:00.000000',1,1,1,2,1,4),(98,'2024-11-15 00:00:00.000000',1,1,1,2,1,4),(99,'2024-11-22 00:00:00.000000',1,1,1,2,1,4),(100,'2024-11-29 00:00:00.000000',1,1,1,2,1,4),(101,'2024-12-06 00:00:00.000000',1,1,1,2,1,4),(102,'2024-12-13 00:00:00.000000',1,1,1,2,1,4),(103,'2024-12-20 00:00:00.000000',1,1,1,2,1,4),(104,'2024-12-27 00:00:00.000000',1,1,1,2,1,4),(105,'2024-07-04 00:00:00.000000',1,2,1,35,1,5),(106,'2024-07-11 00:00:00.000000',1,2,1,35,1,5),(107,'2024-07-18 00:00:00.000000',1,2,1,35,1,5),(108,'2024-07-25 00:00:00.000000',1,2,1,35,1,5),(109,'2024-08-01 00:00:00.000000',1,2,1,35,1,5),(110,'2024-08-08 00:00:00.000000',1,2,1,35,1,5),(111,'2024-08-15 00:00:00.000000',1,2,1,35,1,5),(112,'2024-08-22 00:00:00.000000',1,2,1,35,1,5),(113,'2024-08-29 00:00:00.000000',1,2,1,35,1,5),(114,'2024-09-05 00:00:00.000000',1,2,1,35,1,5),(115,'2024-09-12 00:00:00.000000',1,2,1,35,1,5),(116,'2024-09-19 00:00:00.000000',1,2,1,35,1,5),(117,'2024-09-26 00:00:00.000000',1,2,1,35,1,5),(118,'2024-10-03 00:00:00.000000',1,2,1,35,1,5),(119,'2024-10-10 00:00:00.000000',1,2,1,35,1,5),(120,'2024-10-17 00:00:00.000000',1,2,1,35,1,5),(121,'2024-10-24 00:00:00.000000',1,2,1,35,1,5),(122,'2024-10-31 00:00:00.000000',1,2,1,35,1,5),(123,'2024-11-07 00:00:00.000000',1,2,1,35,1,5),(124,'2024-11-14 00:00:00.000000',1,2,1,35,1,5),(125,'2024-11-21 00:00:00.000000',1,2,1,35,1,5),(126,'2024-11-28 00:00:00.000000',1,2,1,35,1,5),(127,'2024-12-05 00:00:00.000000',1,2,1,35,1,5),(128,'2024-12-12 00:00:00.000000',1,2,1,35,1,5),(129,'2024-12-19 00:00:00.000000',1,2,1,35,1,5),(130,'2024-12-26 00:00:00.000000',1,2,1,35,1,5),(131,'2024-07-04 00:00:00.000000',1,1,1,3,2,6),(132,'2024-07-11 00:00:00.000000',1,1,1,3,2,6),(133,'2024-07-18 00:00:00.000000',1,1,1,3,2,6),(134,'2024-07-25 00:00:00.000000',1,1,1,3,2,6),(135,'2024-08-01 00:00:00.000000',1,1,1,3,2,6),(136,'2024-08-08 00:00:00.000000',1,1,1,3,2,6),(137,'2024-08-15 00:00:00.000000',1,1,1,3,2,6),(138,'2024-08-22 00:00:00.000000',1,1,1,3,2,6),(139,'2024-08-29 00:00:00.000000',1,1,1,3,2,6),(140,'2024-09-05 00:00:00.000000',1,1,1,3,2,6),(141,'2024-09-12 00:00:00.000000',1,1,1,3,2,6),(142,'2024-09-19 00:00:00.000000',1,1,1,3,2,6),(143,'2024-09-26 00:00:00.000000',1,1,1,3,2,6),(144,'2024-10-03 00:00:00.000000',1,1,1,3,2,6),(145,'2024-10-10 00:00:00.000000',1,1,1,3,2,6),(146,'2024-10-17 00:00:00.000000',1,1,1,3,2,6),(147,'2024-10-24 00:00:00.000000',1,1,1,3,2,6),(148,'2024-10-31 00:00:00.000000',1,1,1,3,2,6),(149,'2024-11-07 00:00:00.000000',1,1,1,3,2,6),(150,'2024-11-14 00:00:00.000000',1,1,1,3,2,6),(151,'2024-11-21 00:00:00.000000',1,1,1,3,2,6),(152,'2024-11-28 00:00:00.000000',1,1,1,3,2,6),(153,'2024-12-05 00:00:00.000000',1,1,1,3,2,6),(154,'2024-12-12 00:00:00.000000',1,1,1,3,2,6),(155,'2024-12-19 00:00:00.000000',1,1,1,3,2,6),(156,'2024-12-26 00:00:00.000000',1,1,1,3,2,6),(157,'2024-07-05 00:00:00.000000',1,1,1,2,1,7),(158,'2024-07-12 00:00:00.000000',1,1,1,2,1,7),(159,'2024-07-19 00:00:00.000000',1,1,1,2,1,7),(160,'2024-07-26 00:00:00.000000',1,1,1,2,1,7),(161,'2024-08-02 00:00:00.000000',1,1,1,2,1,7),(162,'2024-08-09 00:00:00.000000',1,1,1,2,1,7),(163,'2024-08-16 00:00:00.000000',1,1,1,2,1,7),(164,'2024-08-23 00:00:00.000000',1,1,1,2,1,7),(165,'2024-08-30 00:00:00.000000',1,1,1,2,1,7),(166,'2024-09-06 00:00:00.000000',1,1,1,2,1,7),(167,'2024-09-13 00:00:00.000000',1,1,1,2,1,7),(168,'2024-09-20 00:00:00.000000',1,1,1,2,1,7),(169,'2024-09-27 00:00:00.000000',1,1,1,2,1,7),(170,'2024-10-04 00:00:00.000000',1,1,1,2,1,7),(171,'2024-10-11 00:00:00.000000',1,1,1,2,1,7),(172,'2024-10-18 00:00:00.000000',1,1,1,2,1,7),(173,'2024-10-25 00:00:00.000000',1,1,1,2,1,7),(174,'2024-11-01 00:00:00.000000',1,1,1,2,1,7),(175,'2024-11-08 00:00:00.000000',1,1,1,2,1,7),(176,'2024-11-15 00:00:00.000000',1,1,1,2,1,7),(177,'2024-11-22 00:00:00.000000',1,1,1,2,1,7),(178,'2024-11-29 00:00:00.000000',1,1,1,2,1,7),(179,'2024-12-06 00:00:00.000000',1,1,1,2,1,7),(180,'2024-12-13 00:00:00.000000',1,1,1,2,1,7),(181,'2024-12-20 00:00:00.000000',1,1,1,2,1,7),(182,'2024-12-27 00:00:00.000000',1,1,1,2,1,7),(183,'2024-07-05 00:00:00.000000',1,2,1,35,2,8),(184,'2024-07-12 00:00:00.000000',1,2,1,35,2,8),(185,'2024-07-19 00:00:00.000000',1,2,1,35,2,8),(186,'2024-07-26 00:00:00.000000',1,2,1,35,2,8),(187,'2024-08-02 00:00:00.000000',1,2,1,35,2,8),(188,'2024-08-09 00:00:00.000000',1,2,1,35,2,8),(189,'2024-08-16 00:00:00.000000',1,2,1,35,2,8),(190,'2024-08-23 00:00:00.000000',1,2,1,35,2,8),(191,'2024-08-30 00:00:00.000000',1,2,1,35,2,8),(192,'2024-09-06 00:00:00.000000',1,2,1,35,2,8),(193,'2024-09-13 00:00:00.000000',1,2,1,35,2,8),(194,'2024-09-20 00:00:00.000000',1,2,1,35,2,8),(195,'2024-09-27 00:00:00.000000',1,2,1,35,2,8),(196,'2024-10-04 00:00:00.000000',1,2,1,35,2,8),(197,'2024-10-11 00:00:00.000000',1,2,1,35,2,8),(198,'2024-10-18 00:00:00.000000',1,2,1,35,2,8),(199,'2024-10-25 00:00:00.000000',1,2,1,35,2,8),(200,'2024-11-01 00:00:00.000000',1,2,1,35,2,8),(201,'2024-11-08 00:00:00.000000',1,2,1,35,2,8),(202,'2024-11-15 00:00:00.000000',1,2,1,35,2,8),(203,'2024-11-22 00:00:00.000000',1,2,1,35,2,8),(204,'2024-11-29 00:00:00.000000',1,2,1,35,2,8),(205,'2024-12-06 00:00:00.000000',1,2,1,35,2,8),(206,'2024-12-13 00:00:00.000000',1,2,1,35,2,8),(207,'2024-12-20 00:00:00.000000',1,2,1,35,2,8),(208,'2024-12-27 00:00:00.000000',1,2,1,35,2,8),(209,'2024-07-06 00:00:00.000000',1,2,1,8,2,9),(210,'2024-07-13 00:00:00.000000',1,2,1,8,2,9),(211,'2024-07-20 00:00:00.000000',1,2,1,8,2,9),(212,'2024-07-27 00:00:00.000000',1,2,1,8,2,9),(213,'2024-08-03 00:00:00.000000',1,2,1,8,2,9),(214,'2024-08-10 00:00:00.000000',1,2,1,8,2,9),(215,'2024-08-17 00:00:00.000000',1,2,1,8,2,9),(216,'2024-08-24 00:00:00.000000',1,2,1,8,2,9),(217,'2024-08-31 00:00:00.000000',1,2,1,8,2,9),(218,'2024-09-07 00:00:00.000000',1,2,1,8,2,9),(219,'2024-09-14 00:00:00.000000',1,2,1,8,2,9),(220,'2024-09-21 00:00:00.000000',1,2,1,8,2,9),(221,'2024-09-28 00:00:00.000000',1,2,1,8,2,9),(222,'2024-10-05 00:00:00.000000',1,2,1,8,2,9),(223,'2024-10-12 00:00:00.000000',1,2,1,8,2,9),(224,'2024-10-19 00:00:00.000000',1,2,1,8,2,9),(225,'2024-10-26 00:00:00.000000',1,2,1,8,2,9),(226,'2024-11-02 00:00:00.000000',1,2,1,8,2,9),(227,'2024-11-09 00:00:00.000000',1,2,1,8,2,9),(228,'2024-11-16 00:00:00.000000',1,2,1,8,2,9),(229,'2024-11-23 00:00:00.000000',1,2,1,8,2,9),(230,'2024-11-30 00:00:00.000000',1,2,1,8,2,9),(231,'2024-07-06 00:00:00.000000',1,1,1,1,1,10),(232,'2024-07-13 00:00:00.000000',1,1,1,1,1,10),(233,'2024-07-20 00:00:00.000000',1,1,1,1,1,10),(234,'2024-07-27 00:00:00.000000',1,1,1,1,1,10),(235,'2024-08-03 00:00:00.000000',1,1,1,1,1,10),(236,'2024-08-10 00:00:00.000000',1,1,1,1,1,10),(237,'2024-08-17 00:00:00.000000',1,1,1,1,1,10),(238,'2024-08-24 00:00:00.000000',1,1,1,1,1,10),(239,'2024-08-31 00:00:00.000000',1,1,1,1,1,10),(240,'2024-09-07 00:00:00.000000',1,1,1,1,1,10),(241,'2024-09-14 00:00:00.000000',1,1,1,1,1,10),(242,'2024-09-21 00:00:00.000000',1,1,1,1,1,10),(243,'2024-09-28 00:00:00.000000',1,1,1,1,1,10),(244,'2024-10-05 00:00:00.000000',1,1,1,1,1,10),(245,'2024-10-12 00:00:00.000000',1,1,1,1,1,10),(246,'2024-10-19 00:00:00.000000',1,1,1,1,1,10),(247,'2024-10-26 00:00:00.000000',1,1,1,1,1,10),(248,'2024-11-02 00:00:00.000000',1,1,1,1,1,10),(249,'2024-11-09 00:00:00.000000',1,1,1,1,1,10),(250,'2024-11-16 00:00:00.000000',1,1,1,1,1,10),(251,'2024-11-23 00:00:00.000000',1,1,1,1,1,10),(252,'2024-11-30 00:00:00.000000',1,1,1,1,1,10),(253,'2024-12-07 00:00:00.000000',1,1,1,1,1,10),(254,'2024-12-14 00:00:00.000000',1,1,1,1,1,10),(255,'2024-12-21 00:00:00.000000',1,1,1,1,1,10),(256,'2024-12-28 00:00:00.000000',1,1,1,1,1,10),(257,'2024-07-01 00:00:00.000000',1,1,1,1,2,11),(258,'2024-07-08 00:00:00.000000',1,1,1,1,2,11),(259,'2024-07-15 00:00:00.000000',1,1,1,1,2,11),(260,'2024-07-22 00:00:00.000000',1,1,1,1,2,11),(261,'2024-07-29 00:00:00.000000',1,1,1,1,2,11),(262,'2024-08-05 00:00:00.000000',1,1,1,1,2,11),(263,'2024-08-12 00:00:00.000000',1,1,1,1,2,11),(264,'2024-08-19 00:00:00.000000',1,1,1,1,2,11),(265,'2024-08-26 00:00:00.000000',1,1,1,1,2,11),(266,'2024-09-02 00:00:00.000000',1,1,1,1,2,11),(267,'2024-09-09 00:00:00.000000',1,1,1,1,2,11),(268,'2024-09-16 00:00:00.000000',1,1,1,1,2,11),(269,'2024-09-23 00:00:00.000000',1,1,1,1,2,11),(270,'2024-09-30 00:00:00.000000',1,1,1,1,2,11),(271,'2024-10-07 00:00:00.000000',1,1,1,1,2,11),(272,'2024-10-14 00:00:00.000000',1,1,1,1,2,11),(273,'2024-10-21 00:00:00.000000',1,1,1,1,2,11),(274,'2024-10-28 00:00:00.000000',1,1,1,1,2,11),(275,'2024-11-04 00:00:00.000000',1,1,1,1,2,11),(276,'2024-11-11 00:00:00.000000',1,1,1,1,2,11),(277,'2024-11-18 00:00:00.000000',1,1,1,1,2,11),(278,'2024-11-25 00:00:00.000000',1,1,1,1,2,11),(279,'2024-12-02 00:00:00.000000',1,1,1,1,2,11),(280,'2024-12-09 00:00:00.000000',1,1,1,1,2,11),(281,'2024-12-16 00:00:00.000000',1,1,1,1,2,11),(282,'2024-12-23 00:00:00.000000',1,1,1,1,2,11),(283,'2024-12-30 00:00:00.000000',1,1,1,1,2,11),(284,'2024-07-01 00:00:00.000000',1,1,1,2,1,12),(285,'2024-07-08 00:00:00.000000',1,1,1,2,1,12),(286,'2024-07-15 00:00:00.000000',1,1,1,2,1,12),(287,'2024-07-22 00:00:00.000000',1,1,1,2,1,12),(288,'2024-07-29 00:00:00.000000',1,1,1,2,1,12),(289,'2024-08-05 00:00:00.000000',1,1,1,2,1,12),(290,'2024-08-12 00:00:00.000000',1,1,1,2,1,12),(291,'2024-08-19 00:00:00.000000',1,1,1,2,1,12),(292,'2024-08-26 00:00:00.000000',1,1,1,2,1,12),(293,'2024-09-02 00:00:00.000000',1,1,1,2,1,12),(294,'2024-09-09 00:00:00.000000',1,1,1,2,1,12),(295,'2024-09-16 00:00:00.000000',1,1,1,2,1,12),(296,'2024-09-23 00:00:00.000000',1,1,1,2,1,12),(297,'2024-09-30 00:00:00.000000',1,1,1,2,1,12),(298,'2024-10-07 00:00:00.000000',1,1,1,2,1,12),(299,'2024-10-14 00:00:00.000000',1,1,1,2,1,12),(300,'2024-10-21 00:00:00.000000',1,1,1,2,1,12),(301,'2024-10-28 00:00:00.000000',1,1,1,2,1,12),(302,'2024-11-04 00:00:00.000000',1,1,1,2,1,12),(303,'2024-11-11 00:00:00.000000',1,1,1,2,1,12),(304,'2024-11-18 00:00:00.000000',1,1,1,2,1,12),(305,'2024-11-25 00:00:00.000000',1,1,1,2,1,12),(306,'2024-12-02 00:00:00.000000',1,1,1,2,1,12),(307,'2024-12-09 00:00:00.000000',1,1,1,2,1,12),(308,'2024-12-16 00:00:00.000000',1,1,1,2,1,12),(309,'2024-12-23 00:00:00.000000',1,1,1,2,1,12),(310,'2024-12-30 00:00:00.000000',1,1,1,2,1,12);
/*!40000 ALTER TABLE `attendancemaster` ENABLE KEYS */;
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
