-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 20, 2020 at 09:02 AM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `moneytracker`
--

-- --------------------------------------------------------

--
-- Table structure for table `balancesheet`
--

DROP TABLE IF EXISTS `balancesheet`;
CREATE TABLE IF NOT EXISTS `balancesheet` (
  `userID` int(20) NOT NULL,
  `transactionDate` date NOT NULL,
  `transactionTime` time NOT NULL,
  `expenseAmount` float DEFAULT NULL,
  `expenseID` int(20) DEFAULT NULL,
  `expenseNote` varchar(255) DEFAULT NULL,
  `depositAmount` float DEFAULT NULL,
  `depositID` int(20) DEFAULT NULL,
  `balanceAmount` float NOT NULL,
  PRIMARY KEY (`userID`,`transactionDate`,`transactionTime`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `balancesheet`
--

INSERT INTO `balancesheet` (`userID`, `transactionDate`, `transactionTime`, `expenseAmount`, `expenseID`, `expenseNote`, `depositAmount`, `depositID`, `balanceAmount`) VALUES
(100012, '2020-06-09', '14:02:53', NULL, NULL, NULL, 1000, 40092, 1000),
(100012, '2020-06-09', '14:03:10', NULL, NULL, NULL, 200, 40093, 1200),
(100012, '2020-06-09', '14:03:59', 500, 70044, 'Gifted Book', NULL, NULL, 700),
(100012, '2020-06-09', '14:04:21', NULL, NULL, NULL, 300, 40094, 1000),
(100012, '2020-06-09', '14:05:33', 800, 70045, 'White and Black Suit', NULL, NULL, 200),
(100012, '2020-06-09', '14:05:49', NULL, NULL, NULL, 2000, 40095, 2200),
(100012, '2020-06-10', '14:07:53', NULL, NULL, NULL, 1000, 40096, 3200),
(100012, '2020-06-10', '14:09:01', 450, 70046, 'Dry Fruits & Juice', NULL, NULL, 2750),
(100012, '2020-06-10', '14:49:06', NULL, NULL, NULL, 250, 40098, 3000),
(100012, '2020-06-16', '13:56:16', NULL, NULL, NULL, 150.25, 40101, 3000.55),
(100012, '2020-06-16', '13:51:00', NULL, NULL, NULL, 100, 40100, 2850.3),
(100012, '2020-06-16', '13:45:11', 350.25, 70047, 'Earphone for Sister', NULL, NULL, 2750.3),
(100012, '2020-06-16', '13:43:03', NULL, NULL, NULL, 100.55, 40099, 3100.55),
(100013, '2020-06-18', '11:10:56', NULL, NULL, NULL, 1000.55, 40102, 1000.55),
(100013, '2020-06-18', '11:11:11', NULL, NULL, NULL, 100.12, 40103, 1100.67),
(100013, '2020-06-18', '11:11:33', NULL, NULL, NULL, 500.33, 40104, 1601),
(100013, '2020-06-18', '11:12:03', 800.16, 70048, 'Paid Bill', NULL, NULL, 800.84),
(100012, '2020-06-18', '11:14:15', NULL, NULL, NULL, 500.55, 40105, 3501.1),
(100012, '2020-06-18', '11:16:43', 1050.55, 70049, 'Electricty Bill Paid', NULL, NULL, 2450.55),
(100012, '2020-06-18', '11:16:58', NULL, NULL, NULL, 100, 40106, 2550.55),
(100012, '2020-06-18', '14:40:39', NULL, NULL, NULL, 100.55, 40107, 2651.1),
(100012, '2020-06-18', '14:40:51', NULL, NULL, NULL, 100, 40108, 2751.1),
(100012, '2020-06-18', '14:42:33', 500, 70050, 'Birthday', NULL, NULL, 2251.1),
(100012, '2020-06-18', '14:42:53', 100, 70051, '', NULL, NULL, 2151.1),
(100012, '2020-06-19', '14:49:01', NULL, NULL, NULL, 200.2, 40109, 2351.3),
(100014, '2020-06-18', '14:55:48', NULL, NULL, NULL, 1000, 40110, 1000),
(100014, '2020-06-18', '14:56:02', 1000, 70052, '', NULL, NULL, 0),
(100015, '2020-08-04', '17:09:52', NULL, NULL, NULL, 500, 40111, 500),
(100015, '2020-08-04', '17:10:07', NULL, NULL, NULL, 1000, 40112, 1500),
(100015, '2020-08-04', '17:11:52', 1500, 70053, '', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `deposit`
--

DROP TABLE IF EXISTS `deposit`;
CREATE TABLE IF NOT EXISTS `deposit` (
  `userID` int(20) NOT NULL,
  `depositID` int(20) NOT NULL AUTO_INCREMENT,
  `depositDate` date NOT NULL,
  `depositAmount` float NOT NULL,
  PRIMARY KEY (`depositID`)
) ENGINE=MyISAM AUTO_INCREMENT=40125 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deposit`
--

INSERT INTO `deposit` (`userID`, `depositID`, `depositDate`, `depositAmount`) VALUES
(100012, 40068, '2020-06-01', 50),
(100012, 40092, '2020-06-09', 1000),
(100012, 40093, '2020-06-09', 200),
(100012, 40094, '2020-06-09', 300),
(100012, 40095, '2020-06-09', 2000),
(100012, 40096, '2020-06-10', 1000),
(100012, 40098, '2020-06-10', 250),
(100012, 40101, '2020-06-16', 150.25),
(100012, 40100, '2020-06-16', 100),
(100012, 40099, '2020-06-16', 100.55),
(100013, 40102, '2020-06-18', 1000.55),
(100013, 40103, '2020-06-18', 100.12),
(100013, 40104, '2020-06-18', 500.33),
(100012, 40105, '2020-06-18', 500.55),
(100012, 40106, '2020-06-18', 100),
(100012, 40107, '2020-06-18', 100.55),
(100012, 40108, '2020-06-18', 100),
(100012, 40109, '2020-06-19', 200.2),
(100014, 40110, '2020-06-18', 1000),
(100015, 40111, '2020-08-04', 500),
(100015, 40112, '2020-08-04', 1000);

-- --------------------------------------------------------

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
CREATE TABLE IF NOT EXISTS `expense` (
  `userID` int(20) NOT NULL,
  `expenseID` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `categoryID` int(11) NOT NULL,
  `amount` float NOT NULL,
  `note` varchar(255) NOT NULL,
  PRIMARY KEY (`expenseID`)
) ENGINE=MyISAM AUTO_INCREMENT=70057 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expense`
--

INSERT INTO `expense` (`userID`, `expenseID`, `date`, `categoryID`, `amount`, `note`) VALUES
(100012, 70044, '2020-06-09', 9025, 500, 'Gifted Book'),
(100012, 70045, '2020-06-09', 9026, 800, 'White and Black Suit'),
(100012, 70046, '2020-06-10', 9028, 450, 'Dry Fruits & Juice'),
(100012, 70047, '2020-06-16', 9027, 350.25, 'Earphone for Sister'),
(100013, 70048, '2020-06-18', 9029, 800.16, 'Paid Bill'),
(100012, 70049, '2020-06-18', 9030, 1050.55, 'Electricty Bill Paid'),
(100012, 70050, '2020-06-18', 9032, 500, 'Birthday'),
(100012, 70051, '2020-06-18', 9026, 100, ''),
(100014, 70052, '2020-06-18', 9033, 1000, ''),
(100015, 70053, '2020-08-04', 9035, 1500, '');

-- --------------------------------------------------------

--
-- Table structure for table `expensecategory`
--

DROP TABLE IF EXISTS `expensecategory`;
CREATE TABLE IF NOT EXISTS `expensecategory` (
  `userID` int(20) NOT NULL,
  `categoryID` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=MyISAM AUTO_INCREMENT=9036 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expensecategory`
--

INSERT INTO `expensecategory` (`userID`, `categoryID`, `name`, `description`) VALUES
(100012, 9026, 'Dress', 'Formal Suit / Casual Wear'),
(100012, 9025, 'Book', 'Fiction, Comics'),
(100012, 9027, 'Electronics', 'Electronic Gadgets (Earphone, Desktop, Modem, Mobile)'),
(100012, 9028, 'Grocery', ''),
(100013, 9029, 'Bill', 'Telephone & Elecreicity Bill'),
(100012, 9030, 'Bill', 'Telephone Bill\nElectricity Bill'),
(100012, 9031, 'Servicing', 'Car/MotorBike'),
(100012, 9032, 'Cake', '----'),
(100014, 9033, 'Electornic', 'Gadgets'),
(100015, 9034, 'Gadgets', 'Earphone...'),
(100015, 9035, 'Trip', 'sdf df sdf');

-- --------------------------------------------------------

--
-- Table structure for table `userdetails`
--

DROP TABLE IF EXISTS `userdetails`;
CREATE TABLE IF NOT EXISTS `userdetails` (
  `userID` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `contact` varchar(10) NOT NULL,
  `address` varchar(100) NOT NULL,
  `city` varchar(20) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=MyISAM AUTO_INCREMENT=100054 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userdetails`
--

INSERT INTO `userdetails` (`userID`, `name`, `contact`, `address`, `city`, `mail`, `gender`, `password`) VALUES
(100012, 'Sweta', '7878787878', 'CK 65 262C Chetganj', 'Varanasi', 'sweta@gmail.com', 'Female', '100012'),
(100013, 'Riya', '3232323232', 'Address 1001', 'City', 'riya@gmail.com', 'Male', '100013'),
(100014, 'Swasti Maam', '9999999999', 'Address 202', 'Lucknow', 'swasti@gmail.com', 'Female', 'swasti');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
