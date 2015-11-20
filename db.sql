-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.24 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  8.2.0.4675
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 tmp 的数据库结构
DROP DATABASE IF EXISTS `tmp`;
CREATE DATABASE IF NOT EXISTS `tmp` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tmp`;


-- 导出  表 tmp.component 结构
DROP TABLE IF EXISTS `component`;
CREATE TABLE IF NOT EXISTS `component` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) NOT NULL,
  `parentUid` varchar(50) NOT NULL COMMENT '组件所属云的uid',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COMMENT='云组件表，组件是云的组成元素';

-- 正在导出表  tmp.component 的数据：~6 rows (大约)
DELETE FROM `component`;
/*!40000 ALTER TABLE `component` DISABLE KEYS */;
INSERT INTO `component` (`id`, `uid`, `parentUid`) VALUES
	(1, 'component1', 'provider1'),
	(2, 'component2', 'provider1'),
	(3, 'component3', 'provider1'),
	(4, 'component4', 'provider2'),
	(5, 'component5', 'provider2'),
	(6, 'component6', 'provider2');
/*!40000 ALTER TABLE `component` ENABLE KEYS */;


-- 导出  表 tmp.componenthistory 结构
DROP TABLE IF EXISTS `componenthistory`;
CREATE TABLE IF NOT EXISTS `componenthistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) NOT NULL,
  `trustorUid` varchar(50) NOT NULL,
  `trusteeUid` varchar(50) NOT NULL,
  `trustValue` decimal(5,4) NOT NULL,
  `actionTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `actionType` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1表示层内交互，2表示跨层交互，0为默认',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`),
  KEY `trustorUid` (`trustorUid`),
  KEY `trusteeUid` (`trusteeUid`),
  KEY `actionType` (`actionType`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='组件交互历史表，有评估方Uid与被评估方Uid';

-- 正在导出表  tmp.componenthistory 的数据：~0 rows (大约)
DELETE FROM `componenthistory`;
/*!40000 ALTER TABLE `componenthistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `componenthistory` ENABLE KEYS */;


-- 导出  表 tmp.componentreputation 结构
DROP TABLE IF EXISTS `componentreputation`;
CREATE TABLE IF NOT EXISTS `componentreputation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) NOT NULL,
  `componentUid` varchar(50) NOT NULL,
  `reputationValue` decimal(5,4) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`),
  KEY `componentUid` (`componentUid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='组件声誉表';

-- 正在导出表  tmp.componentreputation 的数据：~0 rows (大约)
DELETE FROM `componentreputation`;
/*!40000 ALTER TABLE `componentreputation` DISABLE KEYS */;
/*!40000 ALTER TABLE `componentreputation` ENABLE KEYS */;


-- 导出  表 tmp.componenttrustvalue 结构
DROP TABLE IF EXISTS `componenttrustvalue`;
CREATE TABLE IF NOT EXISTS `componenttrustvalue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) NOT NULL,
  `trustorUid` varchar(50) NOT NULL,
  `trusteeUid` varchar(50) NOT NULL,
  `trustValue` decimal(5,4) NOT NULL,
  `actionType` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1表示层内交互，2表示跨层交互，0为默认',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`),
  KEY `trustorUid` (`trustorUid`),
  KEY `trusteeUid` (`trusteeUid`),
  KEY `actionType` (`actionType`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='组件信任值表';

-- 正在导出表  tmp.componenttrustvalue 的数据：~0 rows (大约)
DELETE FROM `componenttrustvalue`;
/*!40000 ALTER TABLE `componenttrustvalue` DISABLE KEYS */;
/*!40000 ALTER TABLE `componenttrustvalue` ENABLE KEYS */;


-- 导出  表 tmp.provider 结构
DROP TABLE IF EXISTS `provider`;
CREATE TABLE IF NOT EXISTS `provider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='云表';

-- 正在导出表  tmp.provider 的数据：~2 rows (大约)
DELETE FROM `provider`;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
INSERT INTO `provider` (`id`, `uid`) VALUES
	(1, 'provider1'),
	(2, 'provider2');
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;


-- 导出  表 tmp.providertrustvalue 结构
DROP TABLE IF EXISTS `providertrustvalue`;
CREATE TABLE IF NOT EXISTS `providertrustvalue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) NOT NULL,
  `providerUid` varchar(50) NOT NULL,
  `trustValue` decimal(5,4) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`),
  KEY `providerUid` (`providerUid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='云信任值表';

-- 正在导出表  tmp.providertrustvalue 的数据：~0 rows (大约)
DELETE FROM `providertrustvalue`;
/*!40000 ALTER TABLE `providertrustvalue` DISABLE KEYS */;
/*!40000 ALTER TABLE `providertrustvalue` ENABLE KEYS */;


-- 导出  表 tmp.renter 结构
DROP TABLE IF EXISTS `renter`;
CREATE TABLE IF NOT EXISTS `renter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COMMENT='租户表';

-- 正在导出表  tmp.renter 的数据：~4 rows (大约)
DELETE FROM `renter`;
/*!40000 ALTER TABLE `renter` DISABLE KEYS */;
INSERT INTO `renter` (`id`, `uid`) VALUES
	(1, 'renter1'),
	(3, 'renter2'),
	(4, 'renter3'),
	(5, 'renter4');
/*!40000 ALTER TABLE `renter` ENABLE KEYS */;


-- 导出  表 tmp.renterhistory 结构
DROP TABLE IF EXISTS `renterhistory`;
CREATE TABLE IF NOT EXISTS `renterhistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) NOT NULL,
  `trustorUid` varchar(50) NOT NULL,
  `trusteeUid` varchar(50) NOT NULL,
  `trustValue` decimal(5,4) NOT NULL,
  `actionTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `actionType` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1表示层内交互，2表示跨层交互，0为默认',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`),
  KEY `trustorUid` (`trustorUid`),
  KEY `trusteeUid` (`trusteeUid`),
  KEY `actionType` (`actionType`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='租户交互历史表，有评估方Uid与被评估方Uid';

-- 正在导出表  tmp.renterhistory 的数据：~0 rows (大约)
DELETE FROM `renterhistory`;
/*!40000 ALTER TABLE `renterhistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `renterhistory` ENABLE KEYS */;


-- 导出  表 tmp.renterreputation 结构
DROP TABLE IF EXISTS `renterreputation`;
CREATE TABLE IF NOT EXISTS `renterreputation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) NOT NULL,
  `renterUid` varchar(50) NOT NULL,
  `reputationValue` decimal(5,4) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`),
  KEY `renterUid` (`renterUid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='租户声誉表';

-- 正在导出表  tmp.renterreputation 的数据：~0 rows (大约)
DELETE FROM `renterreputation`;
/*!40000 ALTER TABLE `renterreputation` DISABLE KEYS */;
/*!40000 ALTER TABLE `renterreputation` ENABLE KEYS */;


-- 导出  表 tmp.rentertrustvalue 结构
DROP TABLE IF EXISTS `rentertrustvalue`;
CREATE TABLE IF NOT EXISTS `rentertrustvalue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) NOT NULL,
  `trustorUid` varchar(50) NOT NULL,
  `trusteeUid` varchar(50) NOT NULL,
  `trustValue` decimal(5,4) NOT NULL,
  `actionType` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '1表示层内交互，2表示跨层交互，0为默认',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`),
  KEY `trustorUid` (`trustorUid`),
  KEY `trusteeUid` (`trusteeUid`),
  KEY `actionType` (`actionType`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='租户信任值表';

-- 正在导出表  tmp.rentertrustvalue 的数据：~0 rows (大约)
DELETE FROM `rentertrustvalue`;
/*!40000 ALTER TABLE `rentertrustvalue` DISABLE KEYS */;
/*!40000 ALTER TABLE `rentertrustvalue` ENABLE KEYS */;


-- 导出  表 tmp.user_t 结构
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE IF NOT EXISTS `user_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  tmp.user_t 的数据：~2 rows (大约)
DELETE FROM `user_t`;
/*!40000 ALTER TABLE `user_t` DISABLE KEYS */;
INSERT INTO `user_t` (`id`, `user_name`, `password`, `age`) VALUES
	(1, '测试', 'sfasgfaf', 24),
	(2, 'ddd', 'ddd', 1);
/*!40000 ALTER TABLE `user_t` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
