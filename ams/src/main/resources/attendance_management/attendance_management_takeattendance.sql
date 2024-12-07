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
-- Table structure for table `takeattendance`
--

DROP TABLE IF EXISTS `takeattendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `takeattendance` (
  `takeattendanceid` int NOT NULL AUTO_INCREMENT,
  `attendanceon` datetime(6) NOT NULL,
  `attendanceby` int DEFAULT NULL,
  `attendancemasterid` int DEFAULT NULL,
  `takeattendancestatusid` int DEFAULT NULL,
  PRIMARY KEY (`takeattendanceid`),
  KEY `FKpoubhtvbpgrcro4lfjx6hvhai` (`attendanceby`),
  KEY `FK4ebx018sjpmarutjcbg9ktvge` (`attendancemasterid`),
  KEY `FK89c79a75vie2or6peql2lvu01` (`takeattendancestatusid`),
  CONSTRAINT `FK4ebx018sjpmarutjcbg9ktvge` FOREIGN KEY (`attendancemasterid`) REFERENCES `attendancemaster` (`attendancemasterid`),
  CONSTRAINT `FK89c79a75vie2or6peql2lvu01` FOREIGN KEY (`takeattendancestatusid`) REFERENCES `takeattendancestatus` (`takeattendancestatusid`),
  CONSTRAINT `FKpoubhtvbpgrcro4lfjx6hvhai` FOREIGN KEY (`attendanceby`) REFERENCES `teacher` (`teacherid`)
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `takeattendance`
--

LOCK TABLES `takeattendance` WRITE;
/*!40000 ALTER TABLE `takeattendance` DISABLE KEYS */;
INSERT INTO `takeattendance` VALUES (1,'2024-07-05 00:00:00.000000',1,1,2),(2,'2024-07-12 00:00:00.000000',1,2,2),(3,'2024-07-19 00:00:00.000000',1,3,2),(4,'2024-07-26 00:00:00.000000',1,4,2),(5,'2024-08-02 00:00:00.000000',1,5,2),(6,'2024-08-09 00:00:00.000000',1,6,2),(7,'2024-08-16 00:00:00.000000',1,7,2),(8,'2024-08-23 00:00:00.000000',1,8,2),(9,'2024-08-30 00:00:00.000000',1,9,2),(10,'2024-09-06 00:00:00.000000',1,10,2),(11,'2024-09-13 00:00:00.000000',1,11,2),(12,'2024-09-20 00:00:00.000000',1,12,2),(13,'2024-09-27 00:00:00.000000',1,13,2),(14,'2024-10-04 00:00:00.000000',1,14,2),(15,'2024-10-11 00:00:00.000000',1,15,2),(16,'2024-10-18 00:00:00.000000',1,16,2),(17,'2024-10-25 00:00:00.000000',1,17,2),(18,'2024-11-01 00:00:00.000000',1,18,2),(19,'2024-11-08 00:00:00.000000',1,19,2),(20,'2024-11-15 00:00:00.000000',1,20,2),(21,'2024-11-22 00:00:00.000000',1,21,2),(22,'2024-11-29 15:56:19.782000',1,22,1),(23,'2024-12-06 00:00:00.000000',1,23,2),(24,'2024-12-13 00:00:00.000000',1,24,2),(25,'2024-12-20 00:00:00.000000',1,25,2),(26,'2024-12-27 00:00:00.000000',1,26,2),(27,'2024-07-05 00:00:00.000000',2,27,2),(28,'2024-07-12 00:00:00.000000',2,28,2),(29,'2024-07-19 00:00:00.000000',2,29,2),(30,'2024-07-26 00:00:00.000000',2,30,2),(31,'2024-08-02 00:00:00.000000',2,31,2),(32,'2024-08-09 00:00:00.000000',2,32,2),(33,'2024-08-16 00:00:00.000000',2,33,2),(34,'2024-08-23 00:00:00.000000',2,34,2),(35,'2024-08-30 00:00:00.000000',2,35,2),(36,'2024-09-06 00:00:00.000000',2,36,2),(37,'2024-09-13 00:00:00.000000',2,37,2),(38,'2024-09-20 00:00:00.000000',2,38,2),(39,'2024-09-27 00:00:00.000000',2,39,2),(40,'2024-10-04 00:00:00.000000',2,40,2),(41,'2024-10-11 00:00:00.000000',2,41,2),(42,'2024-10-18 00:00:00.000000',2,42,2),(43,'2024-10-25 00:00:00.000000',2,43,2),(44,'2024-11-01 00:00:00.000000',2,44,2),(45,'2024-11-08 00:00:00.000000',2,45,2),(46,'2024-11-15 00:00:00.000000',2,46,2),(47,'2024-11-22 00:00:00.000000',2,47,2),(48,'2024-11-29 00:00:00.000000',2,48,2),(49,'2024-12-06 00:00:00.000000',2,49,2),(50,'2024-12-13 00:00:00.000000',2,50,2),(51,'2024-12-20 00:00:00.000000',2,51,2),(52,'2024-12-27 00:00:00.000000',2,52,2),(53,'2024-07-05 00:00:00.000000',2,53,3),(54,'2024-07-12 00:00:00.000000',2,54,3),(55,'2024-07-19 00:00:00.000000',2,55,3),(56,'2024-07-26 00:00:00.000000',2,56,3),(57,'2024-08-02 00:00:00.000000',2,57,3),(58,'2024-08-09 00:00:00.000000',2,58,3),(59,'2024-08-16 00:00:00.000000',2,59,3),(60,'2024-08-23 00:00:00.000000',2,60,3),(61,'2024-08-30 00:00:00.000000',2,61,3),(62,'2024-09-06 00:00:00.000000',2,62,3),(63,'2024-09-13 00:00:00.000000',2,63,3),(64,'2024-09-20 00:00:00.000000',2,64,3),(65,'2024-09-27 00:00:00.000000',2,65,3),(66,'2024-10-04 00:00:00.000000',2,66,3),(67,'2024-10-11 00:00:00.000000',2,67,3),(68,'2024-10-18 00:00:00.000000',2,68,3),(69,'2024-10-25 00:00:00.000000',2,69,3),(70,'2024-11-01 00:00:00.000000',2,70,3),(71,'2024-11-08 00:00:00.000000',2,71,3),(72,'2024-11-15 00:00:00.000000',2,72,3),(73,'2024-11-22 00:00:00.000000',2,73,3),(74,'2024-11-29 16:47:23.950000',2,74,1),(75,'2024-12-06 00:00:00.000000',2,75,3),(76,'2024-12-13 00:00:00.000000',2,76,3),(77,'2024-12-20 00:00:00.000000',2,77,3),(78,'2024-12-27 00:00:00.000000',2,78,3),(79,'2024-07-05 00:00:00.000000',1,79,3),(80,'2024-07-12 00:00:00.000000',1,80,3),(81,'2024-07-19 00:00:00.000000',1,81,3),(82,'2024-07-26 00:00:00.000000',1,82,3),(83,'2024-08-02 00:00:00.000000',1,83,3),(84,'2024-08-09 00:00:00.000000',1,84,3),(85,'2024-08-16 00:00:00.000000',1,85,3),(86,'2024-08-23 00:00:00.000000',1,86,3),(87,'2024-08-30 00:00:00.000000',1,87,3),(88,'2024-09-06 00:00:00.000000',1,88,3),(89,'2024-09-13 00:00:00.000000',1,89,3),(90,'2024-09-20 00:00:00.000000',1,90,3),(91,'2024-09-27 00:00:00.000000',1,91,3),(92,'2024-10-04 00:00:00.000000',1,92,3),(93,'2024-10-11 00:00:00.000000',1,93,3),(94,'2024-10-18 00:00:00.000000',1,94,3),(95,'2024-10-25 00:00:00.000000',1,95,3),(96,'2024-11-01 00:00:00.000000',1,96,3),(97,'2024-11-08 00:00:00.000000',1,97,3),(98,'2024-11-15 00:00:00.000000',1,98,3),(99,'2024-11-22 00:00:00.000000',1,99,3),(100,'2024-11-29 15:21:09.147000',1,100,1),(101,'2024-12-06 00:00:00.000000',1,101,3),(102,'2024-12-13 00:00:00.000000',1,102,3),(103,'2024-12-20 00:00:00.000000',1,103,3),(104,'2024-12-27 00:00:00.000000',1,104,3),(105,'2024-07-04 00:00:00.000000',1,105,4),(106,'2024-07-11 00:00:00.000000',1,106,4),(107,'2024-07-18 00:00:00.000000',1,107,4),(108,'2024-07-25 00:00:00.000000',1,108,4),(109,'2024-08-01 00:00:00.000000',1,109,4),(110,'2024-08-08 00:00:00.000000',1,110,4),(111,'2024-08-15 00:00:00.000000',1,111,4),(112,'2024-08-22 00:00:00.000000',1,112,4),(113,'2024-08-29 00:00:00.000000',1,113,4),(114,'2024-09-05 00:00:00.000000',1,114,4),(115,'2024-09-12 00:00:00.000000',1,115,4),(116,'2024-09-19 00:00:00.000000',1,116,4),(117,'2024-09-26 00:00:00.000000',1,117,4),(118,'2024-10-03 00:00:00.000000',1,118,4),(119,'2024-10-10 00:00:00.000000',1,119,4),(120,'2024-10-17 00:00:00.000000',1,120,4),(121,'2024-10-24 00:00:00.000000',1,121,4),(122,'2024-10-31 00:00:00.000000',1,122,4),(123,'2024-11-07 00:00:00.000000',1,123,4),(124,'2024-11-14 00:00:00.000000',1,124,4),(125,'2024-11-21 00:00:00.000000',1,125,4),(126,'2024-11-29 15:51:40.164000',1,126,1),(127,'2024-12-05 00:00:00.000000',1,127,4),(128,'2024-12-12 00:00:00.000000',1,128,4),(129,'2024-12-19 00:00:00.000000',1,129,4),(130,'2024-12-26 00:00:00.000000',1,130,4),(131,'2024-07-04 00:00:00.000000',2,131,4),(132,'2024-07-11 00:00:00.000000',2,132,4),(133,'2024-07-18 00:00:00.000000',2,133,4),(134,'2024-07-25 00:00:00.000000',2,134,4),(135,'2024-08-01 00:00:00.000000',2,135,4),(136,'2024-08-08 00:00:00.000000',2,136,4),(137,'2024-08-15 00:00:00.000000',2,137,4),(138,'2024-08-22 00:00:00.000000',2,138,4),(139,'2024-08-29 00:00:00.000000',2,139,4),(140,'2024-09-05 00:00:00.000000',2,140,4),(141,'2024-09-12 00:00:00.000000',2,141,4),(142,'2024-09-19 00:00:00.000000',2,142,4),(143,'2024-09-26 00:00:00.000000',2,143,4),(144,'2024-10-03 00:00:00.000000',2,144,4),(145,'2024-10-10 00:00:00.000000',2,145,4),(146,'2024-10-17 00:00:00.000000',2,146,4),(147,'2024-10-24 00:00:00.000000',2,147,4),(148,'2024-10-31 00:00:00.000000',2,148,4),(149,'2024-11-07 00:00:00.000000',2,149,4),(150,'2024-11-14 00:00:00.000000',2,150,4),(151,'2024-11-21 00:00:00.000000',2,151,4),(152,'2024-11-28 00:00:00.000000',2,152,4),(153,'2024-12-05 00:00:00.000000',2,153,4),(154,'2024-12-12 00:00:00.000000',2,154,4),(155,'2024-12-19 00:00:00.000000',2,155,4),(156,'2024-12-26 00:00:00.000000',2,156,4),(157,'2024-07-05 00:00:00.000000',1,157,4),(158,'2024-07-12 00:00:00.000000',1,158,4),(159,'2024-07-19 00:00:00.000000',1,159,4),(160,'2024-07-26 00:00:00.000000',1,160,4),(161,'2024-08-02 00:00:00.000000',1,161,4),(162,'2024-08-09 00:00:00.000000',1,162,4),(163,'2024-08-16 00:00:00.000000',1,163,4),(164,'2024-08-23 00:00:00.000000',1,164,4),(165,'2024-08-30 00:00:00.000000',1,165,4),(166,'2024-09-06 00:00:00.000000',1,166,4),(167,'2024-09-13 00:00:00.000000',1,167,4),(168,'2024-09-20 00:00:00.000000',1,168,4),(169,'2024-09-27 00:00:00.000000',1,169,4),(170,'2024-10-04 00:00:00.000000',1,170,4),(171,'2024-10-11 00:00:00.000000',1,171,4),(172,'2024-10-18 00:00:00.000000',1,172,4),(173,'2024-10-25 00:00:00.000000',1,173,4),(174,'2024-11-01 00:00:00.000000',1,174,4),(175,'2024-11-08 00:00:00.000000',1,175,4),(176,'2024-11-15 00:00:00.000000',1,176,4),(177,'2024-11-22 00:00:00.000000',1,177,4),(178,'2024-11-29 16:37:36.615000',1,178,1),(179,'2024-12-06 00:00:00.000000',1,179,4),(180,'2024-12-13 00:00:00.000000',1,180,4),(181,'2024-12-20 00:00:00.000000',1,181,4),(182,'2024-12-27 00:00:00.000000',1,182,4),(183,'2024-07-05 00:00:00.000000',2,183,3),(184,'2024-07-12 00:00:00.000000',2,184,3),(185,'2024-07-19 00:00:00.000000',2,185,3),(186,'2024-07-26 00:00:00.000000',2,186,3),(187,'2024-08-02 00:00:00.000000',2,187,3),(188,'2024-08-09 00:00:00.000000',2,188,3),(189,'2024-08-16 00:00:00.000000',2,189,3),(190,'2024-08-23 00:00:00.000000',2,190,3),(191,'2024-08-30 00:00:00.000000',2,191,3),(192,'2024-09-06 00:00:00.000000',2,192,3),(193,'2024-09-13 00:00:00.000000',2,193,3),(194,'2024-09-20 00:00:00.000000',2,194,3),(195,'2024-09-27 00:00:00.000000',2,195,3),(196,'2024-10-04 00:00:00.000000',2,196,3),(197,'2024-10-11 00:00:00.000000',2,197,3),(198,'2024-10-18 00:00:00.000000',2,198,3),(199,'2024-10-25 00:00:00.000000',2,199,3),(200,'2024-11-01 00:00:00.000000',2,200,3),(201,'2024-11-08 00:00:00.000000',2,201,3),(202,'2024-11-15 00:00:00.000000',2,202,3),(203,'2024-11-22 00:00:00.000000',2,203,3),(204,'2024-11-29 00:00:00.000000',2,204,3),(205,'2024-12-06 00:00:00.000000',2,205,3),(206,'2024-12-13 00:00:00.000000',2,206,3),(207,'2024-12-20 00:00:00.000000',2,207,3),(208,'2024-12-27 00:00:00.000000',2,208,3),(209,'2024-07-06 00:00:00.000000',2,209,2),(210,'2024-07-13 00:00:00.000000',2,210,2),(211,'2024-07-20 00:00:00.000000',2,211,2),(212,'2024-07-27 00:00:00.000000',2,212,2),(213,'2024-08-03 00:00:00.000000',2,213,2),(214,'2024-08-10 00:00:00.000000',2,214,2),(215,'2024-08-17 00:00:00.000000',2,215,2),(216,'2024-08-24 00:00:00.000000',2,216,2),(217,'2024-08-31 00:00:00.000000',2,217,2),(218,'2024-09-07 00:00:00.000000',2,218,2),(219,'2024-09-14 00:00:00.000000',2,219,2),(220,'2024-09-21 00:00:00.000000',2,220,2),(221,'2024-09-28 00:00:00.000000',2,221,2),(222,'2024-10-05 00:00:00.000000',2,222,2),(223,'2024-10-12 00:00:00.000000',2,223,2),(224,'2024-10-19 00:00:00.000000',2,224,2),(225,'2024-10-26 00:00:00.000000',2,225,2),(226,'2024-11-02 00:00:00.000000',2,226,2),(227,'2024-11-09 00:00:00.000000',2,227,2),(228,'2024-11-16 00:00:00.000000',2,228,2),(229,'2024-11-23 00:00:00.000000',2,229,2),(230,'2024-11-30 00:00:00.000000',2,230,2),(231,'2024-07-06 00:00:00.000000',1,231,3),(232,'2024-07-13 00:00:00.000000',1,232,3),(233,'2024-07-20 00:00:00.000000',1,233,3),(234,'2024-07-27 00:00:00.000000',1,234,3),(235,'2024-08-03 00:00:00.000000',1,235,3),(236,'2024-08-10 00:00:00.000000',1,236,3),(237,'2024-08-17 00:00:00.000000',1,237,3),(238,'2024-08-24 00:00:00.000000',1,238,3),(239,'2024-08-31 00:00:00.000000',1,239,3),(240,'2024-09-07 00:00:00.000000',1,240,3),(241,'2024-09-14 00:00:00.000000',1,241,3),(242,'2024-09-21 00:00:00.000000',1,242,3),(243,'2024-09-28 00:00:00.000000',1,243,3),(244,'2024-10-05 00:00:00.000000',1,244,3),(245,'2024-10-12 00:00:00.000000',1,245,3),(246,'2024-10-19 00:00:00.000000',1,246,3),(247,'2024-10-26 00:00:00.000000',1,247,3),(248,'2024-11-02 00:00:00.000000',1,248,3),(249,'2024-11-09 00:00:00.000000',1,249,3),(250,'2024-11-16 00:00:00.000000',1,250,3),(251,'2024-11-23 00:00:00.000000',1,251,3),(252,'2024-11-30 00:00:00.000000',1,252,3),(253,'2024-12-07 00:00:00.000000',1,253,3),(254,'2024-12-14 00:00:00.000000',1,254,3),(255,'2024-12-21 00:00:00.000000',1,255,3),(256,'2024-12-28 00:00:00.000000',1,256,3),(257,'2024-07-01 00:00:00.000000',2,257,3),(258,'2024-07-08 00:00:00.000000',2,258,3),(259,'2024-07-15 00:00:00.000000',2,259,3),(260,'2024-07-22 00:00:00.000000',2,260,3),(261,'2024-07-29 00:00:00.000000',2,261,3),(262,'2024-08-05 00:00:00.000000',2,262,3),(263,'2024-08-12 00:00:00.000000',2,263,3),(264,'2024-08-19 00:00:00.000000',2,264,3),(265,'2024-08-26 00:00:00.000000',2,265,3),(266,'2024-09-02 00:00:00.000000',2,266,3),(267,'2024-09-09 00:00:00.000000',2,267,3),(268,'2024-09-16 00:00:00.000000',2,268,3),(269,'2024-09-23 00:00:00.000000',2,269,3),(270,'2024-09-30 00:00:00.000000',2,270,3),(271,'2024-10-07 00:00:00.000000',2,271,3),(272,'2024-10-14 00:00:00.000000',2,272,3),(273,'2024-10-21 00:00:00.000000',2,273,3),(274,'2024-10-28 00:00:00.000000',2,274,3),(275,'2024-11-04 00:00:00.000000',2,275,3),(276,'2024-11-11 00:00:00.000000',2,276,3),(277,'2024-11-18 00:00:00.000000',2,277,3),(278,'2024-11-25 00:00:00.000000',2,278,3),(279,'2024-12-02 00:00:00.000000',2,279,3),(280,'2024-12-09 00:00:00.000000',2,280,3),(281,'2024-12-16 00:00:00.000000',2,281,3),(282,'2024-12-23 00:00:00.000000',2,282,3),(283,'2024-12-30 00:00:00.000000',2,283,3),(284,'2024-07-01 00:00:00.000000',1,284,3),(285,'2024-07-08 00:00:00.000000',1,285,3),(286,'2024-07-15 00:00:00.000000',1,286,3),(287,'2024-07-22 00:00:00.000000',1,287,3),(288,'2024-07-29 00:00:00.000000',1,288,3),(289,'2024-08-05 00:00:00.000000',1,289,3),(290,'2024-08-12 00:00:00.000000',1,290,3),(291,'2024-08-19 00:00:00.000000',1,291,3),(292,'2024-08-26 00:00:00.000000',1,292,3),(293,'2024-09-02 00:00:00.000000',1,293,3),(294,'2024-09-09 00:00:00.000000',1,294,3),(295,'2024-09-16 00:00:00.000000',1,295,3),(296,'2024-09-23 00:00:00.000000',1,296,3),(297,'2024-09-30 00:00:00.000000',1,297,3),(298,'2024-10-07 00:00:00.000000',1,298,3),(299,'2024-10-14 00:00:00.000000',1,299,3),(300,'2024-10-21 00:00:00.000000',1,300,3),(301,'2024-10-28 00:00:00.000000',1,301,3),(302,'2024-11-04 00:00:00.000000',1,302,3),(303,'2024-11-11 00:00:00.000000',1,303,3),(304,'2024-11-18 00:00:00.000000',1,304,3),(305,'2024-11-25 00:00:00.000000',1,305,3),(306,'2024-12-02 00:00:00.000000',1,306,3),(307,'2024-12-09 00:00:00.000000',1,307,3),(308,'2024-12-16 00:00:00.000000',1,308,3),(309,'2024-12-23 00:00:00.000000',1,309,3),(310,'2024-12-30 00:00:00.000000',1,310,3);
/*!40000 ALTER TABLE `takeattendance` ENABLE KEYS */;
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
