CREATE DATABASE  IF NOT EXISTS `credibanco` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `credibanco`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: credibanco
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `card_history`
--

DROP TABLE IF EXISTS `card_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `card_holder` varchar(255) DEFAULT NULL,
  `card_type` varchar(255) DEFAULT NULL,
  `document_number` varchar(255) DEFAULT NULL,
  `id_card` bigint DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  `pan` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `validation_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_history`
--

LOCK TABLES `card_history` WRITE;
/*!40000 ALTER TABLE `card_history` DISABLE KEYS */;
INSERT INTO `card_history` VALUES (1,'John Due','CREDITO','1234567895',1,'325fab3c15d0d661e64c8b302824dcc5be8b7476b0eeb729c285b67ea071548a','1234563333345623','1234567890','CREADA','84');
/*!40000 ALTER TABLE `card_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cards`
--

DROP TABLE IF EXISTS `cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cards` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `card_holder` varchar(255) DEFAULT NULL,
  `card_type` varchar(255) DEFAULT NULL,
  `document_number` varchar(255) DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  `pan` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `validation_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cards`
--

LOCK TABLES `cards` WRITE;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
INSERT INTO `cards` VALUES (1,'2023-03-06 03:56:33.107181','2023-03-06 03:56:33.107181','John Due','CREDITO','1234567895','325fab3c15d0d661e64c8b302824dcc5be8b7476b0eeb729c285b67ea071548a','1234563333345623','1234567890','CREADA','84');
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_history`
--

DROP TABLE IF EXISTS `transaction_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `id_transaction` bigint DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  `reference_number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_history`
--

LOCK TABLES `transaction_history` WRITE;
/*!40000 ALTER TABLE `transaction_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  `reference_number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'credibanco'
--

--
-- Dumping routines for database 'credibanco'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-05 23:03:43
