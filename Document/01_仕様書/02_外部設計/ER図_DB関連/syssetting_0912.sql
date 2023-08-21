CREATE DATABASE  IF NOT EXISTS `webedi3` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `webedi`;
-- MySQL dump 10.13  Distrib 5.6.19, for Win32 (x86)
--
-- Host: localhost    Database: webedi3
-- ------------------------------------------------------
-- Server version	5.6.20-enterprise-commercial-advanced-log

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
-- Table structure for table `syssetting`
--

DROP TABLE IF EXISTS `syssetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `syssetting` (
  `sys_setting_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '設定ID',
  `sys_cd` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT 'システムC',
  `major_class_cd` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '大分類C',
  `mid_class_cd` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '中分類C',
  `minor_class_cd` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '小分類C',
  `str_val1` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '文字列1',
  `str_val2` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '文字列2',
  `str_val3` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '文字列3',
  `str_val4` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '文字列4',
  `str_val5` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '文字列5',
  `num_val1` decimal(10,3) DEFAULT NULL COMMENT '数値1',
  `num_val2` decimal(10,3) DEFAULT NULL COMMENT '数値2',
  `num_val3` decimal(10,3) DEFAULT NULL COMMENT '数値3',
  `num_val4` decimal(10,3) DEFAULT NULL COMMENT '数値4',
  `num_val5` decimal(10,3) DEFAULT NULL COMMENT '数値5',
  `remark1` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '備考1',
  `remark2` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '備考2',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`sys_setting_id`),
  UNIQUE KEY `sysSetting_u1` (`sys_cd`,`major_class_cd`,`mid_class_cd`,`minor_class_cd`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='設定';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `syssetting`
--

LOCK TABLES `syssetting` WRITE;
/*!40000 ALTER TABLE `syssetting` DISABLE KEYS */;
INSERT INTO `syssetting` VALUES (1,'WEBEDI','CREATE_K','','1','通常更新','','','','',NULL,NULL,NULL,NULL,NULL,'','','0000000001','2017-09-09 11:21:30','0000000001','0000000001','2017-09-09 11:21:30','0000000001'),(2,'WEBEDI','CREATE_K','','2','新規','','','','',NULL,NULL,NULL,NULL,NULL,'','','0000000002','2017-09-09 11:21:30','0000000002','0000000002','2017-09-09 11:21:30','0000000002'),(3,'WEBEDI','CREATE_K','','3','条件変更','','','','',NULL,NULL,NULL,NULL,NULL,'','','0000000003','2017-09-09 11:21:30','0000000003','0000000003','2017-09-09 11:21:30','0000000003'),(4,'WEBEDI','CREATE_K','','4','正社員化','','','','',NULL,NULL,NULL,NULL,NULL,'','','0000000004','2017-09-09 11:21:30','0000000004','0000000004','2017-09-09 11:21:30','0000000004'),(5,'WEBEDI','CREATE_K','','5','退職','','','','',NULL,NULL,NULL,NULL,NULL,'','','0000000005','2017-09-09 11:21:30','0000000005','0000000005','2017-09-09 11:21:30','0000000005'),(6,'WEBEDI','CREATE_K','','6','再販','','','','',NULL,NULL,NULL,NULL,NULL,'','','0000000006','2017-09-09 11:21:30','0000000006','0000000006','2017-09-09 11:21:30','0000000006'),(7,'WEBEDI','APPR_ST','','1','申請待','','','','',NULL,NULL,NULL,NULL,NULL,'稟議書データ登録直後','','0000000007','2017-09-12 22:52:00','0000000007','0000000007','2017-09-12 22:52:00','0000000007'),(8,'WEBEDI','APPR_ST','','2','承認待','','','','',NULL,NULL,NULL,NULL,NULL,'稟議書データ承認WFを申請後　※申請後、承認中前、申請取消が可能','','0000000008','2017-09-12 22:52:00','0000000008','0000000008','2017-09-12 22:52:00','0000000008'),(9,'WEBEDI','APPR_ST','','3','承認中','','','','',NULL,NULL,NULL,NULL,NULL,'稟議書承認WFがスタート後','','0000000009','2017-09-12 22:52:00','0000000009','0000000009','2017-09-12 22:52:00','0000000009'),(10,'WEBEDI','APPR_ST','','4','承認済','','','','',NULL,NULL,NULL,NULL,NULL,'承認経路の承認者がすべて承認済、','','0000000010','2017-09-12 22:52:00','0000000010','0000000010','2017-09-12 22:52:00','0000000010'),(11,'WEBEDI','APPR_ST','','5','契約済','','','','',NULL,NULL,NULL,NULL,NULL,'受注先と合意済の後','','0000000011','2017-09-12 22:52:00','0000000011','0000000011','2017-09-12 22:52:00','0000000011'),(12,'WEBEDI','APPR_ST','','A','取消','','','','',NULL,NULL,NULL,NULL,NULL,'ユーザー取消（画面上で検索可能）','','0000000012','2017-09-12 22:52:00','0000000012','0000000012','2017-09-12 22:52:00','0000000012');
/*!40000 ALTER TABLE `syssetting` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-12 23:25:56
