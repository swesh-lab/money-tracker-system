-- Created by Sweta Sharma on 10/07/2020
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
(100010, '2020-08-01', '16:00:00', NULL, NULL, NULL, 1000, 40010, 1000),
(100010, '2020-08-01', '16:02:00', 200, 70010, 'Birthday Celebration', NULL, NULL, 800);

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
) ENGINE=MyISAM AUTO_INCREMENT=70011 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deposit`
--

INSERT INTO `deposit` (`userID`, `depositID`, `depositDate`, `depositAmount`) VALUES
(100010, 40010, '2020-08-01', 1000);

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
) ENGINE=MyISAM AUTO_INCREMENT=70011 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expense`
--

INSERT INTO `expense` (`userID`, `expenseID`, `date`, `categoryID`, `amount`, `note`) VALUES
(100010, 70010, '2020-08-01', 9010, 200, 'Birthday Celebration');

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
) ENGINE=MyISAM AUTO_INCREMENT=9011 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expensecategory`
--

INSERT INTO `expensecategory` (`userID`, `categoryID`, `name`, `description`) VALUES
(100010, 9010, 'Bakery', 'Dark Forest Celebration');

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
) ENGINE=MyISAM AUTO_INCREMENT=100012 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userdetails`
--

INSERT INTO `userdetails` (`userID`, `name`, `contact`, `address`, `city`, `mail`, `gender`, `password`) VALUES
(100010, 'ItsMyVision', '8989898989', 'Lucknow, India', 'Lucknow', 'itsmyvision@gmail.com', 'female', 'p@ssword');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
