CREATE DATABASE  IF NOT EXISTS `hiedb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hiedb`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: hiedb
-- ------------------------------------------------------
-- Server version	5.6.13-enterprise-commercial-advanced

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Enterprise`
--

DROP TABLE IF EXISTS `Enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Enterprise` (
  `id_enterprise` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_name` varchar(150) NOT NULL,
  `enterprise_type` varchar(45) NOT NULL,
  `enterprise_balance` double NOT NULL,
  `enterprise_code` int(11) NOT NULL,
  `street` varchar(80) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `zip` varchar(15) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id_enterprise`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enterprise`
--

LOCK TABLES `Enterprise` WRITE;
/*!40000 ALTER TABLE `Enterprise` DISABLE KEYS */;
INSERT INTO `Enterprise` VALUES (1,'BosHIE','HIE',0,9658,'100 Huntington av.','Boston','MA','02151','989987987','boshie@gmail.com'),(2,'BosInsurance','Insurance',0,8728,'100 Huntington av.','Boston','MA','02151','989987987','boshie@gmail.com');
/*!40000 ALTER TABLE `Enterprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Enterprise_Product`
--

DROP TABLE IF EXISTS `Enterprise_Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Enterprise_Product` (
  `id_enterprise_product` int(11) NOT NULL AUTO_INCREMENT,
  `id_enterprise` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  PRIMARY KEY (`id_enterprise_product`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enterprise_Product`
--

LOCK TABLES `Enterprise_Product` WRITE;
/*!40000 ALTER TABLE `Enterprise_Product` DISABLE KEYS */;
INSERT INTO `Enterprise_Product` VALUES (1,1,1),(2,1,2),(3,2,1),(4,2,2);
/*!40000 ALTER TABLE `Enterprise_Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Order`
--

DROP TABLE IF EXISTS `Order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Order` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `id_user_account` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `total_amount` double NOT NULL,
  PRIMARY KEY (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Order`
--

LOCK TABLES `Order` WRITE;
/*!40000 ALTER TABLE `Order` DISABLE KEYS */;
/*!40000 ALTER TABLE `Order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Payment`
--

DROP TABLE IF EXISTS `Payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Payment` (
  `id_payment` int(11) NOT NULL AUTO_INCREMENT,
  `id_order` int(11) NOT NULL,
  `due_date` datetime NOT NULL,
  `pay_date` datetime DEFAULT NULL,
  `is_pay` tinyint(1) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`id_payment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payment`
--

LOCK TABLES `Payment` WRITE;
/*!40000 ALTER TABLE `Payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Privilege`
--

DROP TABLE IF EXISTS `Privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Privilege` (
  `id_privilege` int(11) NOT NULL AUTO_INCREMENT,
  `privilege_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_privilege`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Privilege`
--

LOCK TABLES `Privilege` WRITE;
/*!40000 ALTER TABLE `Privilege` DISABLE KEYS */;
INSERT INTO `Privilege` VALUES (1,'A'),(2,'B'),(3,'C'),(4,'D');
/*!40000 ALTER TABLE `Privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `id_product` int(11) NOT NULL AUTO_INCREMENT,
  `offer_name` varchar(45) NOT NULL,
  `co_Pay` double NOT NULL,
  `deductible` double NOT NULL,
  `co_Insurance` double NOT NULL,
  `offer_price` double NOT NULL,
  `description` varchar(200) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `target_market` varchar(100) NOT NULL,
  PRIMARY KEY (`id_product`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (1,'HIEF',500,1000,0.5,200,'NBJSKDFNJ',0,'LowIncomeMarket'),(2,'KDJE',1000,500,0.8,300,'BJKSDBFWE',1,'SeniorMarket');
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `role_type` varchar(50) NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'Customer','customer_role_entity'),(2,'Admin','admin_role_entity'),(3,'HieAdmin','hie_admin_role_entity'),(4,'InsuranceAdmin','insurance_admin_role_entity');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role_Privilege`
--

DROP TABLE IF EXISTS `Role_Privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role_Privilege` (
  `id_role_privilege` int(11) NOT NULL AUTO_INCREMENT,
  `id_privilege` int(11) NOT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_role_privilege`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role_Privilege`
--

LOCK TABLES `Role_Privilege` WRITE;
/*!40000 ALTER TABLE `Role_Privilege` DISABLE KEYS */;
INSERT INTO `Role_Privilege` VALUES (1,1,2),(2,2,2),(3,3,2),(4,4,2);
/*!40000 ALTER TABLE `Role_Privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_Account`
--

DROP TABLE IF EXISTS `User_Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_Account` (
  `id_user_account` int(11) NOT NULL AUTO_INCREMENT,
  `id_enterprise` int(11) DEFAULT NULL,
  `id_role` int(11) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `date_of_birth` datetime NOT NULL,
  `age` int(11) NOT NULL,
  `id_employee` int(11) DEFAULT NULL,
  `is_small_business` tinyint(1) NOT NULL,
  `is_family` tinyint(1) NOT NULL,
  `income_status` varchar(45) NOT NULL,
  `street` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `zip` varchar(15) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id_user_account`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_Account`
--

LOCK TABLES `User_Account` WRITE;
/*!40000 ALTER TABLE `User_Account` DISABLE KEYS */;
INSERT INTO `User_Account` VALUES (1,NULL,1,'aaa','aaaaa',1,'Yijun','Liu','1989-07-17 00:00:00',24,NULL,1,1,'none','100 Huntington av.','Boston','MA','02151','000000000','jj@gmail.com'),(2,NULL,2,'bbb','bbbbb',1,'Yijun','Liu','1989-07-17 00:00:00',24,NULL,1,1,'none','100 Huntington av.','Boston','MA','02151','000000000','jj@gmail.com'),(3,1,3,'ccc','ccccc',1,'Yijun','Liu','1989-07-17 00:00:00',24,NULL,1,1,'none','100 Huntington av.','Boston','MA','02151','000000000','jj@gmail.com'),(4,2,4,'ddd','ddddd',1,'Yijun','Liu','1989-07-17 00:00:00',24,NULL,1,1,'none','100 Huntington av.','Boston','MA','02151','000000000','jj@gmail.com');
/*!40000 ALTER TABLE `User_Account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-11 17:14:59