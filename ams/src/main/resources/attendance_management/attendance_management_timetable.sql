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
-- Table structure for table `timetable`
--

DROP TABLE IF EXISTS `timetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timetable` (
  `timetableid` int NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) NOT NULL,
  `createdon` datetime(6) NOT NULL,
  `day` varchar(255) NOT NULL,
  `endtime` time(6) NOT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `starttime` time(6) NOT NULL,
  `updatedon` datetime(6) DEFAULT NULL,
  `programid` int DEFAULT NULL,
  `semesterid` int DEFAULT NULL,
  `subjectid` int DEFAULT NULL,
  `teacherid` int DEFAULT NULL,
  PRIMARY KEY (`timetableid`),
  KEY `FK1oql6sjajtm2em5hh9vavalmi` (`programid`),
  KEY `FKlss1ihh2us23dslm9p955scs1` (`semesterid`),
  KEY `FKjne9d4idmettsdfc726y038qj` (`subjectid`),
  KEY `FKhhfxiyhiqhqjqvtqa7vtt41gr` (`teacherid`),
  CONSTRAINT `FK1oql6sjajtm2em5hh9vavalmi` FOREIGN KEY (`programid`) REFERENCES `program` (`programid`),
  CONSTRAINT `FKhhfxiyhiqhqjqvtqa7vtt41gr` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`),
  CONSTRAINT `FKjne9d4idmettsdfc726y038qj` FOREIGN KEY (`subjectid`) REFERENCES `subject` (`subjectid`),
  CONSTRAINT `FKlss1ihh2us23dslm9p955scs1` FOREIGN KEY (`semesterid`) REFERENCES `semester` (`semesterid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timetable`
--

LOCK TABLES `timetable` WRITE;
/*!40000 ALTER TABLE `timetable` DISABLE KEYS */;
INSERT INTO `timetable` VALUES (1,'1','2024-11-29 15:10:54.216000','Friday','16:00:00.000000','1','15:00:00.000000','2024-11-29 15:10:54.216000',2,1,6,1),(2,'1','2024-11-29 15:11:11.069000','Friday','16:00:00.000000','1','15:00:00.000000','2024-11-29 15:11:11.069000',1,1,1,2),(3,'1','2024-11-29 15:11:26.335000','Friday','15:00:00.000000','1','14:00:00.000000','2024-11-29 15:11:26.335000',2,1,7,2),(4,'1','2024-11-29 15:11:40.917000','Friday','15:00:00.000000','1','14:00:00.000000','2024-11-29 15:11:40.917000',1,1,2,1),(5,'1','2024-11-29 15:11:59.003000','Thursday','17:00:00.000000','1','16:00:00.000000','2024-11-29 15:11:59.003000',2,1,35,1),(6,'1','2024-11-29 15:12:18.301000','Thursday','17:00:00.000000','1','16:00:00.000000','2024-11-29 15:12:18.301000',1,1,3,2),(7,'1','2024-11-29 15:12:31.248000','Friday','17:00:00.000000','1','16:00:00.000000','2024-11-29 15:12:31.248000',1,1,2,1),(8,'1','2024-11-29 15:12:44.218000','Friday','12:00:00.000000','1','11:00:00.000000','2024-12-02 10:49:26.934000',2,1,35,2),(9,'1','2024-11-30 17:54:09.305000','Saturday','16:00:00.000000','1','15:00:00.000000','2024-11-30 17:54:09.305000',2,1,8,2),(10,'1','2024-11-30 17:54:28.144000','Saturday','15:00:00.000000','1','14:00:00.000000','2024-11-30 17:54:28.144000',1,1,1,1),(11,'1','2024-12-01 23:16:23.291000','Monday','13:00:00.000000','1','12:00:00.000000','2024-12-01 23:16:23.291000',1,1,1,2),(12,'1','2024-12-03 14:57:24.112000','Monday','14:00:00.000000','1','13:00:00.000000','2024-12-03 14:57:24.112000',1,1,2,1);
/*!40000 ALTER TABLE `timetable` ENABLE KEYS */;
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
