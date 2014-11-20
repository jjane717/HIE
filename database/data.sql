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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enterprise`
--

LOCK TABLES `Enterprise` WRITE;
/*!40000 ALTER TABLE `Enterprise` DISABLE KEYS */;
INSERT INTO `Enterprise` VALUES (1,'BosHIE','HIE',0,9658,'100 Huntington av.','Boston','MA','02151','989987987','boshie@gmail.com'),(2,'BosInsurance','Insurance',0,9089,'100 Huntington av.','Boston','MA','02151','989987987','boshie@gmail.com'),(3,'Admin','Admin',0,8728,'100 Huntington av.','Boston','MA','02151','989987987','boshie@gmail.com'),(4,'ConnHIE','HIE',0,9090,'oooooooo','Cone','CT','00022','000000000','lll@g.com'),(5,'ConnInsurance','Insurance',0,9872,'ppppppppp','Cone','CT','02222','090929999','kk@g.com');
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
  `status` tinyint(1) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `target_market` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_enterprise_product`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enterprise_Product`
--

LOCK TABLES `Enterprise_Product` WRITE;
/*!40000 ALTER TABLE `Enterprise_Product` DISABLE KEYS */;
INSERT INTO `Enterprise_Product` VALUES (1,1,1,1,230,'LowIncomeMarket'),(2,1,2,1,300,'SeniorMarket'),(3,2,1,1,200,'LowIncomeMarket'),(4,2,2,1,300,'SeniorMarket'),(5,1,3,1,225,'AdultMarket'),(6,1,4,1,400,'FamilyMarket'),(7,1,5,1,300,'LowIncomeFamilyMarket'),(8,2,10,1,900,'LowIncomeSmallBusinessFamilyMarket'),(9,1,7,1,900,'SmallBusinessFamilyMarket'),(10,1,8,1,700,'SmallBusinessIndividualMarket'),(11,1,9,1,300,'LowIncomeSmallBusinessIndividualMarket'),(12,1,10,1,900,'LowIncomeSmallBusinessFamilyMarket'),(13,2,3,1,225,'AdultMarket'),(14,2,4,1,400,'FamilyMarket'),(15,2,5,1,300,'LowIncomeFamilyMarket'),(16,2,6,1,800,'LowIncomeSmallBusinessFamilyMarket'),(17,2,7,1,900,'SmallBusinessFamilyMarket'),(18,2,8,1,700,'SmallBusinessIndividualMarket'),(19,2,9,1,300,'LowIncomeSmallBusinessIndividualMarket'),(20,1,6,0,800,'LowIncomeSmallBusinessFamilyMarket'),(21,2,13,1,90,'AdultMarket'),(22,4,1,0,300,'LowIncomeMarket'),(23,4,2,0,300,'SeniorMarket'),(24,5,11,1,500,'SmallBusinessFamilyMarket'),(25,4,11,1,500,'SmallBusinessFamilyMarket'),(26,4,8,0,700,'SmallBusinessIndividualMarket'),(27,1,13,0,90,'AdultMarket'),(28,4,13,0,90,'AdultMarket');
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
  `id_user_account` int(11) DEFAULT NULL,
  `id_enterprise_product` int(11) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `payment_type` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  PRIMARY KEY (`id_order`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Order`
--

LOCK TABLES `Order` WRITE;
/*!40000 ALTER TABLE `Order` DISABLE KEYS */;
INSERT INTO `Order` VALUES (1,1,9,3,'Monthly','2014-11-19 00:02:06',2700),(2,1,25,8,'Quarterly','2014-11-19 00:02:29',4000),(3,1,9,30,'Annually','2014-11-19 00:02:46',27000),(4,1,9,1,'Monthly','2014-11-19 01:46:49',900);
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
  `is_pay` tinyint(1) DEFAULT '0',
  `amount` double NOT NULL,
  PRIMARY KEY (`id_payment`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payment`
--

LOCK TABLES `Payment` WRITE;
/*!40000 ALTER TABLE `Payment` DISABLE KEYS */;
INSERT INTO `Payment` VALUES (1,1,'2014-12-19 00:00:00','2014-11-19 20:25:53',1,900),(2,1,'2015-01-19 00:00:00',NULL,0,900),(3,1,'2015-02-19 00:00:00',NULL,0,900),(4,2,'2014-12-19 00:00:00','2014-11-19 03:17:26',1,1500),(5,2,'2015-03-19 00:00:00',NULL,0,1500),(6,2,'2015-06-19 00:00:00',NULL,0,1000),(7,3,'2014-12-03 00:00:00',NULL,0,10800),(8,3,'2015-12-03 00:00:00',NULL,0,10800),(9,3,'2016-12-03 00:00:00',NULL,0,5400),(10,4,'2014-12-19 00:00:00','2014-11-19 03:26:25',1,900);
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
  `privilege_file` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_privilege`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Privilege`
--

LOCK TABLES `Privilege` WRITE;
/*!40000 ALTER TABLE `Privilege` DISABLE KEYS */;
INSERT INTO `Privilege` VALUES (1,'Manage UserAccounts','manageUserAccounts'),(2,'Manage Employees','manageEmployees'),(3,'Manage Enterprises','manageEnterprises'),(4,'Manage Products','manageProducts'),(5,'Manage Offers','manageOffers'),(6,'View Account','viewAccount'),(7,'Place Products','placeProducts'),(8,'User Home','userHome'),(9,'View Market','viewMarket'),(10,'Order History','orderHistory'),(11,'Make Payment','makePayment');
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
  `offer_name` varchar(45) DEFAULT NULL,
  `co_Pay` double DEFAULT NULL,
  `deductible` double DEFAULT NULL,
  `co_Insurance` double DEFAULT NULL,
  `offer_price` double DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `target_market` varchar(100) DEFAULT NULL,
  `insurance_enterprise_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_product`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (1,'HIEF',500,1000,0.5,200,'NBJSKDFNJ','LowIncomeMarket','BosInsurance'),(2,'KDJE',1000,500,0.8,300,'BJKSDBFWE','SeniorMarket','BosInsurance'),(3,'UIED',800,900,0.2,225,'NSDFJEKKF','AdultMarket','BosInsurance'),(4,'UIWK',890,900,0.6,400,'NSDFJEKKF','FamilyMarket','BosInsurance'),(5,'KJSL',890,900,0.3,300,'NSDFJEKKF','LowIncomeFamilyMarket','BosInsurance'),(6,'KLWL',890,900,0.8,800,'NSDFJEKKF','LowIncomeSmallBusinessFamilyMarket','BosInsurance'),(7,'JLJK',900,200,0.8,900,'HJKSDFHJK','SmallBusinessFamilyMarket','BosInsurance'),(8,'HHII',900,1000,0.6,700,'HJKSDFHJK','SmallBusinessIndividualMarket','BosInsurance'),(9,'QJED',300,2000,0.6,300,'HJKSDFHJK','LowIncomeSmallBusinessIndividualMarket','BosInsurance'),(10,'JWIE',200,200,0.8,900,'HJKSDFHJK','LowIncomeSmallBusinessFamilyMarket','BosInsurance'),(11,'HIIE',500,200,0.6,500,'OSDFJEJJK','SmallBusinessFamilyMarket','ConnInsurance'),(13,'nlnkl',90,90,90,90,'nlnknl','AdultMarket','BosInsurance');
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
  `id_employee` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'Customer','customer_role_entity',NULL),(2,'Admin','admin_role_entity',NULL),(3,'HieAdmin','hie_admin_role_entity',NULL),(4,'InsuranceAdmin','insurance_admin_role_entity',NULL),(6,'Employee','employee_role_entity','96580003'),(7,'Employee','employee_role_entity','8728004'),(8,'Employee','employee_role_entity','9658004'),(9,'Employee','employee_role_entity','9658004'),(10,'Employee','employee_role_entity','90890003'),(11,'Employee','employee_role_entity','90900002'),(12,'Employee','employee_role_entity','96580005'),(13,'Employee','employee_role_entity','96580006'),(14,'Employee','employee_role_entity','96580007'),(15,'Employee','employee_role_entity','96580008'),(16,'Employee','employee_role_entity','90890004'),(17,'Employee','employee_role_entity','90890005'),(18,'Employee','employee_role_entity','98720002');
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role_Privilege`
--

LOCK TABLES `Role_Privilege` WRITE;
/*!40000 ALTER TABLE `Role_Privilege` DISABLE KEYS */;
INSERT INTO `Role_Privilege` VALUES (1,1,2),(3,3,2),(6,6,2),(8,2,3),(9,4,3),(10,6,3),(12,2,4),(13,5,4),(14,6,4),(15,7,4),(16,8,1),(17,6,1),(18,9,1),(19,10,1),(20,11,1),(21,4,6),(22,4,12),(23,6,12),(24,2,14);
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
  `id_enterprise` int(11) DEFAULT '3',
  `id_role` int(11) NOT NULL DEFAULT '1',
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `date_of_birth` varchar(25) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `is_small_business` tinyint(1) DEFAULT NULL,
  `is_family` tinyint(1) DEFAULT NULL,
  `income_status` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(15) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_user_account`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_Account`
--

LOCK TABLES `User_Account` WRITE;
/*!40000 ALTER TABLE `User_Account` DISABLE KEYS */;
INSERT INTO `User_Account` VALUES (1,3,1,'aaa','aaaaa',1,'Yijun','Liu','1989-07-17',25,1,1,'none','100 Huntington av.','Boston','MA','02151','000000000','jj@gmail.com'),(2,3,2,'bbb','bbbbb',1,'Yijun','Liu','1989-07-17',24,1,1,'none','100 Huntington av.','Boston','MA','02151','000000000','jj@gmail.com'),(3,1,3,'ccc','ccccc',1,'Yijun','Liu','1989-07-17',24,1,1,'none','100 Huntington av.','Boston','MA','02151','000000000','jj@gmail.com'),(4,2,4,'ddd','ddddd',1,'Yijun','Liu','1989-07-17',24,1,1,'none','100 Huntington av.','Boston','MA','02151','000000000','jj@gmail.com'),(5,3,2,'admin','admin',1,'Yijun','Liu','1989-07-17',24,1,1,'none','100 Huntington av.','Boston','MA','02151','000000000','jj@gmail.com'),(8,1,3,'eee','eeeee',1,'Yijun','Liu','1989-07-17',24,1,1,'none','100 Huntington av.','Boston','MA','02151','000000000','jj@gmail.com'),(9,1,6,'bosaaa','bosaaa',1,'Yijun','Liu','1989-09-09',25,NULL,NULL,NULL,'2 Hancock st','Quincy','Massachusetts','02171','8572046887','jjj@h.com'),(10,5,4,'ooo','ooooo',1,'Yijun','Liu','1980-12-01',25,NULL,NULL,NULL,'2 Hancock st','Quincy','Massachusetts','02171','8572046887','hh@h.com'),(11,3,7,'adadmin','adadmin',1,'Yijun','Liu','1989-09-09',25,NULL,NULL,NULL,'2 Hancock st','Quincy','Massachusetts','02171','8572046887','hhh@kk.com'),(14,4,3,'cohie','cohie',1,'Yijun','Liu','19890909',25,NULL,NULL,NULL,'2 Hancock st','Quincy','Massachusetts','02171','8572046887','gg@d.c'),(15,1,3,'1','1',1,'Yijun','Liu','19900909',24,NULL,NULL,NULL,'2 Hancock st','Quincy','Massachusetts','02171','8572046887','090@g.com'),(16,2,4,'2','2',1,'Yijun','Liu','19900909',24,NULL,NULL,NULL,'2 Hancock st','Quincy','Massachusetts','02171','8572046887','090@g.com'),(17,2,10,'3','3',1,'Yijun','Liu','19900909',24,NULL,NULL,NULL,'2 Hancock st','Quincy','Massachusetts','02171','8572046887','090@g.com'),(18,4,11,'4','4',1,'Yijun','Liu','19900909',24,NULL,NULL,NULL,'2 Hancock st','Quincy','Massachusetts','02171','8572046887','090@g.com'),(19,1,12,'5','5',1,'Yijun','Liu','19900909',24,NULL,NULL,NULL,'2 Hancock st','Quincy','Massachusetts','02171','8572046887','090@g.com'),(20,1,13,'6','6',1,'Yijun','Liu','19900909',24,NULL,NULL,NULL,'2 Hancock st','Quincy','Massachusetts','02171','8572046887','090@g.com'),(21,1,14,'7','7',1,'Yijun','Liu','19900909',24,NULL,NULL,NULL,'2 Hancock st','Quincy','Massachusetts','02171','8572046887','g@c.cw'),(22,1,15,'8','8',1,'Yijun','Liu','19900909',24,NULL,NULL,NULL,'2 Hancock st','Quincy','Massachusetts','02171','8572046887','g@c.cw'),(23,2,16,'9','9',1,'aaa','aaa','1989-09-09',24,NULL,NULL,NULL,'df','sf','sdf','sdf','000000000','sadaf@dsaf.com'),(25,5,18,'adfff','sadf',1,'Yijun','Liu','19890909',24,NULL,NULL,NULL,'2 Hancock st','Quincy','Massachusetts','02171','8572046887','adf@d.e');
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

-- Dump completed on 2014-11-20  4:53:43
