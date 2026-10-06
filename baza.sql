/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 8.0.44 : Database - iznajmljivanje_konzola
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`iznajmljivanje_konzola` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `iznajmljivanje_konzola`;

/*Table structure for table `clanskakartica` */

DROP TABLE IF EXISTS `clanskakartica`;

CREATE TABLE `clanskakartica` (
  `idClanskaKartica` int NOT NULL AUTO_INCREMENT,
  `kategorija` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idClanskaKartica`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `clanskakartica` */

insert  into `clanskakartica`(`idClanskaKartica`,`kategorija`) values 
(1,'Standard'),
(2,'Standard'),
(3,'Standard'),
(4,'Premium'),
(5,'Premium'),
(6,'VIP'),
(7,'VIP'),
(8,'Standard');

/*Table structure for table `iznajmljivanje` */

DROP TABLE IF EXISTS `iznajmljivanje`;

CREATE TABLE `iznajmljivanje` (
  `idIznajmljivanje` int NOT NULL AUTO_INCREMENT,
  `datumIzdavanja` date NOT NULL,
  `fiksnaNaknada` decimal(10,2) NOT NULL,
  `ukupanIznos` decimal(10,2) NOT NULL,
  `idOperater` int NOT NULL,
  `idKlijent` int NOT NULL,
  PRIMARY KEY (`idIznajmljivanje`),
  KEY `idx_iznajmljivanje_operater` (`idOperater`),
  KEY `idx_iznajmljivanje_klijent` (`idKlijent`),
  CONSTRAINT `fk_iznajmljivanje_klijent` FOREIGN KEY (`idKlijent`) REFERENCES `klijent` (`idKlijent`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_iznajmljivanje_operater` FOREIGN KEY (`idOperater`) REFERENCES `operater` (`idOperater`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `iznajmljivanje` */

insert  into `iznajmljivanje`(`idIznajmljivanje`,`datumIzdavanja`,`fiksnaNaknada`,`ukupanIznos`,`idOperater`,`idKlijent`) values 
(1,'2026-08-20',300.00,1760.00,1,1),
(2,'2026-08-21',150.00,950.00,2,2),
(3,'2026-08-22',300.00,1740.00,1,3),
(4,'2026-08-23',150.00,830.00,3,4),
(5,'2026-08-24',300.00,2000.00,2,5),
(6,'2026-08-27',300.00,1400.00,1,1),
(7,'2026-09-03',150.00,1350.00,1,1);

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `idKlijent` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prezime` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefon` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sifra` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `korisnickoIme` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `omiljenaPlatforma` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `datumRegistracije` date NOT NULL,
  `zabranaDo` date DEFAULT NULL,
  `idClanskaKartica` int NOT NULL,
  PRIMARY KEY (`idKlijent`),
  UNIQUE KEY `uq_klijent_korisnickoIme` (`korisnickoIme`),
  UNIQUE KEY `uq_klijent_email` (`email`),
  UNIQUE KEY `uq_klijent_clanskaKartica` (`idClanskaKartica`),
  KEY `idx_klijent_clanskaKartica` (`idClanskaKartica`),
  CONSTRAINT `fk_klijent_clanskaKartica` FOREIGN KEY (`idClanskaKartica`) REFERENCES `clanskakartica` (`idClanskaKartica`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `klijent` */

insert  into `klijent`(`idKlijent`,`ime`,`prezime`,`telefon`,`email`,`sifra`,`korisnickoIme`,`omiljenaPlatforma`,`datumRegistracije`,`zabranaDo`,`idClanskaKartica`) values 
(1,'Aleksandar','Aleksic','0601112233','aleksandar.a@gmail.com','klijent123','aleksandar.a','Playstation','2025-01-05',NULL,1),
(2,'Teodora','Todorovic','0611223344','teodora.t@gmail.com','klijent123','teodora.t','Nintendo','2025-02-18',NULL,2),
(3,'Filip','Filipovic','0621334455','filip.f@gmail.com','klijent123','filip.f','Xbox','2025-04-22',NULL,3),
(4,'Katarina','Kostic','0631445566','katarina.k@gmail.com','klijent123','katarina.k','PC','2025-06-10','2026-11-30',4),
(5,'Luka','Lukic','0641556677','luka.l@gmail.com','klijent123','luka.l','PlayStation','2025-08-01',NULL,5),
(7,'Petar','Petrovic','06123456','petar@gmail.com','petar','petar','Steam deck','2026-08-27',NULL,8);

/*Table structure for table `konzola` */

DROP TABLE IF EXISTS `konzola`;

CREATE TABLE `konzola` (
  `idKonzola` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `proizvodjac` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cenaPoSatu` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idKonzola`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `konzola` */

insert  into `konzola`(`idKonzola`,`naziv`,`proizvodjac`,`cenaPoSatu`) values 
(1,'PlayStation 5','Sony',300.00),
(2,'PlayStation 5 Pro','Sony',400.00),
(3,'PlayStation 4 Pro','Sony',200.00),
(4,'Xbox Series X','Microsoft',280.00),
(5,'Xbox Series S','Microsoft',200.00),
(6,'Nintendo Switch OLED','Nintendo',180.00),
(7,'Nintendo Switch 2','Nintendo',250.00),
(8,'Steam Deck','Valve',250.00);

/*Table structure for table `operater` */

DROP TABLE IF EXISTS `operater`;

CREATE TABLE `operater` (
  `idOperater` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prezime` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `datumZaposlenja` date NOT NULL,
  `korisnickoIme` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sifra` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idOperater`),
  UNIQUE KEY `uq_operater_korisnickoIme` (`korisnickoIme`),
  UNIQUE KEY `uq_operater_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `operater` */

insert  into `operater`(`idOperater`,`ime`,`prezime`,`email`,`datumZaposlenja`,`korisnickoIme`,`sifra`) values 
(1,'Milan','Ilic','milan.ilic@iznajmi.rs','2023-09-01','milan.i','pass123'),
(2,'Jelena','Jovic','jelena.jovic@iznajmi.rs','2024-02-14','jelena.j','pass123'),
(3,'Nikola','Nikolic','nikola.nikolic@iznajmi.rs','2025-04-05','nikola.n','pass123');

/*Table structure for table `operater_terminsmene` */

DROP TABLE IF EXISTS `operater_terminsmene`;

CREATE TABLE `operater_terminsmene` (
  `idOperater` int NOT NULL,
  `idTerminSmene` int NOT NULL,
  `datum` date NOT NULL,
  `brojSati` int NOT NULL,
  `potvrdjeno` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idOperater`,`idTerminSmene`,`datum`),
  KEY `fk_optersmene_terminsmene` (`idTerminSmene`),
  CONSTRAINT `fk_optersmene_operater` FOREIGN KEY (`idOperater`) REFERENCES `operater` (`idOperater`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_optersmene_terminsmene` FOREIGN KEY (`idTerminSmene`) REFERENCES `terminsmene` (`idTerminSmene`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `operater_terminsmene` */

insert  into `operater_terminsmene`(`idOperater`,`idTerminSmene`,`datum`,`brojSati`,`potvrdjeno`) values 
(1,1,'2026-09-01',8,1),
(1,2,'2026-09-02',6,1),
(2,3,'2026-09-06',10,0),
(2,5,'2026-09-08',8,0),
(3,1,'2026-09-03',8,1),
(3,4,'2026-09-04',7,0);

/*Table structure for table `stavkaiznajmljivanja` */

DROP TABLE IF EXISTS `stavkaiznajmljivanja`;

CREATE TABLE `stavkaiznajmljivanja` (
  `idIznajmljivanje` int NOT NULL,
  `rb` int NOT NULL,
  `pocetakIznajmljivanja` datetime NOT NULL,
  `krajIznajmljivanja` datetime NOT NULL,
  `brojSati` int NOT NULL,
  `cena` decimal(10,2) NOT NULL,
  `iznos` decimal(10,2) NOT NULL,
  `idKonzola` int NOT NULL,
  PRIMARY KEY (`idIznajmljivanje`,`rb`),
  KEY `idx_stavka_konzola` (`idKonzola`),
  CONSTRAINT `fk_stavka_iznajmljivanje` FOREIGN KEY (`idIznajmljivanje`) REFERENCES `iznajmljivanje` (`idIznajmljivanje`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_stavka_konzola` FOREIGN KEY (`idKonzola`) REFERENCES `konzola` (`idKonzola`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `stavkaiznajmljivanja` */

insert  into `stavkaiznajmljivanja`(`idIznajmljivanje`,`rb`,`pocetakIznajmljivanja`,`krajIznajmljivanja`,`brojSati`,`cena`,`iznos`,`idKonzola`) values 
(1,1,'2026-08-20 10:00:00','2026-08-20 13:00:00',3,300.00,900.00,1),
(1,2,'2026-09-01 11:00:00','2026-09-01 13:00:00',2,280.00,560.00,4),
(2,1,'2026-08-21 09:00:00','2026-08-21 13:00:00',4,180.00,720.00,6),
(3,1,'2026-08-22 15:00:00','2026-08-22 18:00:00',3,280.00,840.00,4),
(3,2,'2026-08-22 18:00:00','2026-08-22 20:00:00',2,300.00,600.00,4),
(4,1,'2026-08-23 11:00:00','2026-08-23 13:00:00',2,250.00,500.00,8),
(4,2,'2026-08-23 14:00:00','2026-08-23 16:00:00',2,200.00,180.00,3),
(5,1,'2026-08-24 12:00:00','2026-08-24 16:00:00',4,400.00,1600.00,2),
(5,2,'2026-08-24 16:00:00','2026-08-24 17:00:00',1,300.00,300.00,1),
(6,1,'2026-09-01 11:00:00','2026-09-01 13:00:00',2,300.00,600.00,1),
(6,2,'2026-09-01 15:00:00','2026-09-01 16:45:00',2,250.00,500.00,8),
(7,1,'2027-12-12 14:00:00','2027-12-12 18:00:00',4,300.00,1200.00,1);

/*Table structure for table `terminsmene` */

DROP TABLE IF EXISTS `terminsmene`;

CREATE TABLE `terminsmene` (
  `idTerminSmene` int NOT NULL AUTO_INCREMENT,
  `napomena` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tipTermina` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idTerminSmene`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `terminsmene` */

insert  into `terminsmene`(`idTerminSmene`,`napomena`,`tipTermina`) values 
(1,'Jutarnja smena, radni dani','Prepodne'),
(2,'Popodnevna smena, radni dani','Popodne'),
(3,'Vikend smena, ceo dan','Vikend'),
(4,'Nocna smena','Nocna'),
(5,'Prazniena smena','Praznik'),
(6,'',''),
(7,'Nema posla','Prazan');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
