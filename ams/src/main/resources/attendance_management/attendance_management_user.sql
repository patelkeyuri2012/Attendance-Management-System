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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `blocked` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `roleid` int NOT NULL,
  PRIMARY KEY (`userid`),
  KEY `FK2ovmsl4hvm5vu1w8i308r5j6w` (`roleid`),
  CONSTRAINT `FK2ovmsl4hvm5vu1w8i308r5j6w` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,_binary '\0','rahul.shah@gujaratvidyapith.org','rahul@123','rahul kumar shah',1),(3,_binary '\0','yashvi.gvp@gujaratvidyapith.org','yashvi@28','yashvi shaileshkumar joshi',2),(4,_binary '\0','diya.gvp@gujaratvidyapith.org','diyapatel@19','diya ravibhai patel',3),(6,_binary '\0','jamin.gvp@gujaratvidyapith.org','NXvlrNfGZE','jamin vjaykumar patel',3),(7,_binary '\0','prey.gvp@gujaratvidyapith.org','prey@2002','prey vinodbhai vyas',2),(11,_binary '\0','richi.gvp@gujaratvidyapith.org','AOmM6cgOXC','richi mohanbhai sharma',3),(12,_binary '\0','kavya.gvp@gujaratvidyapith.org','r5i7KNnHrJ','kavya kumarbhai modi',3),(16,_binary '\0','hetvi.gvp@gujaratvidyapith.org','QXjxmyaaqf','hetvi kamleshkumar patel',3),(38,_binary '\0','212308001.gvp@gujaratvidyapith.org','vlS6Hhjto0','Keyuri Vijaybhai Patel',4),(39,_binary '\0','212308002.gvp@gujaratvidyapith.org','LnCC9z0luP','Jainil Darshndas Shah',4),(40,_binary '\0','212308003.gvp@gujaratvidyapith.org','QfYJpwaXPx','Raj Shubhbhai Jain',4),(41,_binary '\0','212308004.gvp@gujaratvidyapith.org','hxrbl5AWbH','Priti Lalabhai Popat',4),(42,_binary '\0','212308005.gvp@gujaratvidyapith.org','hrWcENwAjd','Vijay Jaybhai Panchal',4),(43,_binary '\0','212308006.gvp@gujaratvidyapith.org','212308006@gvp','Daxa Prabhudas Jaidas',4),(44,_binary '\0','212308007.gvp@gujaratvidyapith.org','JDKanp8aPT','Vikas Vinodbhai Patel',4),(45,_binary '\0','212308008.gvp@gujaratvidyapith.org','yUj8qj1gU4','Mahir Kaushalbhai Vyas',4),(46,_binary '\0','212308009.gvp@gujaratvidyapith.org','7lR5csVkH7','Jainil Panditji Veda',4),(47,_binary '\0','212308010.gvp@gujaratvidyapith.org','0ZcL6Jun91','Kreena Bnasidas Kavaiya',4),(48,_binary '\0','212308011.gvp@gujaratvidyapith.org','npLfKX2bYk','Mona Manankumar Vyas',4),(49,_binary '\0','212308012.gvp@gujaratvidyapith.org','lXZvHN92di','Pritesh Visalbhai Shah',4),(50,_binary '\0','212308013.gvp@gujaratvidyapith.org','xc3CZJFTAs','Maya Mohan Bhagat',4),(51,_binary '\0','212308014.gvp@gujaratvidyapith.org','SsSIwuZOlx','Neel Mohan Bhagat',4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
