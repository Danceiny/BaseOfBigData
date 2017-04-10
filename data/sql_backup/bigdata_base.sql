/*
SQLyog Ultimate v11.24 (64 bit)
MySQL - 5.7.17-log : Database - bigdata_base
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bigdata_base` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bigdata_base`;

/*Table structure for table `dbtest` */

DROP TABLE IF EXISTS `dbtest`;

CREATE TABLE `dbtest` (
  `title` varchar(45) DEFAULT NULL,
  `companyName` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 KEY_BLOCK_SIZE=1 COMMENT='测试各编程语言的mysql驱动';

/*Data for the table `dbtest` */

insert  into `dbtest`(`title`,`companyName`,`url`,`id`) values ('百度','baidu','www.baidu.com',1);

/*Table structure for table `qianchengwuyou` */

DROP TABLE IF EXISTS `qianchengwuyou`;

CREATE TABLE `qianchengwuyou` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `companyName` varchar(45) NOT NULL,
  `jobBenefits` varchar(45) DEFAULT NULL,
  `salary` varchar(45) DEFAULT NULL,
  `jobInfo` varchar(45) DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  `field` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `scale` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`url`,`title`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `qianchengwuyou` */

insert  into `qianchengwuyou`(`id`,`url`,`title`,`companyName`,`jobBenefits`,`salary`,`jobInfo`,`place`,`field`,`type`,`scale`) values (1,'url','title','companyName','jobBenefits','salary','jobInfo','place','field','type','scale'),(2,'url','title','companyName','jobBenefits','salary','jobInfo','place','field','type','scale'),(3,'url','title','companyName','jobBenefits','salary','jobInfo','place','field','type','scale');

/*Table structure for table `thebigdatacn` */

DROP TABLE IF EXISTS `thebigdatacn`;

CREATE TABLE `thebigdatacn` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(40) NOT NULL,
  `text` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `thebigdatacn` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
