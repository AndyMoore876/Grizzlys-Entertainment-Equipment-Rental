-- phpMyAdmin SQL Dump
-- version 4.0.4.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 21, 2023 at 04:53 AM
-- Server version: 5.6.13
-- PHP Version: 5.4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `geer_db`
--
CREATE DATABASE IF NOT EXISTS `geer_db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `geer_db`;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `customerId` varchar(15) NOT NULL,
  `firstName` varchar(25) NOT NULL,
  `lastName` varchar(25) NOT NULL,
  `phoneNumber` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `balance` float NOT NULL,
  PRIMARY KEY (`customerId`),
  UNIQUE KEY `customerId` (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customerId`, `firstName`, `lastName`, `phoneNumber`, `email`, `balance`) VALUES
('CX1700540015', 'Andy', 'Moore', '876-123-9877', 'andy@moore.com', 0),
('CX1700540110', 'John', 'Brown', '876-123-9874', 'john@brown.com', 0),
('CX1700540577', 'Jane', 'Doe', '866-876-0000', 'jane@doe.com', 0);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `employeeId` varchar(15) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  PRIMARY KEY (`employeeId`),
  UNIQUE KEY `employeeId` (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employeeId`, `firstName`, `lastName`) VALUES
('A8uM879989', 'Andy', 'Moore'),
('A8uM8u79989', 'Andy', 'Moore'),
('A8uMopj9989', 'Andy', 'Moore'),
('A8uMopj99989', 'Andy', 'Moore'),
('A8uMopj99yu989', 'Andy', 'Moore'),
('AhhuM879989', 'Andy', 'Moore'),
('AhuM879989', 'Andy', 'Moore'),
('AM80012', 'Andy', 'Moore'),
('AM80018', 'Andy', 'Moore'),
('AM80019', 'Andy', 'Moore'),
('AM80029', 'Andy', 'Moore'),
('AM80089', 'Andy', 'Moore'),
('AM8012', 'Andy', 'Moore'),
('AM80789', 'Andy', 'Moore'),
('AM870989', 'Andy', 'Moore'),
('AM87789', 'Andy', 'Moore'),
('AM87989', 'Andy', 'Moore'),
('AM879989', 'Andy', 'Moore'),
('AuM879989', 'Andy', 'Moore'),
('Test0021', 'Test', 'Moore');

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE IF NOT EXISTS `equipment` (
  `equipmentId` varchar(15) NOT NULL,
  `equipmentName` varchar(50) NOT NULL,
  `equipmentType` varchar(50) NOT NULL,
  `customerId` varchar(15) DEFAULT NULL,
  `event` varchar(150) DEFAULT NULL,
  `rented` tinyint(1) NOT NULL,
  `dateRented` varchar(15) DEFAULT NULL,
  `returnDate` varchar(15) DEFAULT NULL,
  `costPerDay` float NOT NULL,
  UNIQUE KEY `equipmentId_2` (`equipmentId`),
  KEY `equipmentId` (`equipmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `equipment`
--

INSERT INTO `equipment` (`equipmentId`, `equipmentName`, `equipmentType`, `customerId`, `event`, `rented`, `dateRented`, `returnDate`, `costPerDay`) VALUES
('EQ1700540431', 'Speaker', 'Sound', NULL, NULL, 0, NULL, NULL, 2000),
('EQ1700540456', 'Stage', 'Staging', NULL, NULL, 1, '21/11/2023', '25/11/2023', 5000);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `messageId` varchar(15) NOT NULL,
  `messageContent` varchar(1000) NOT NULL,
  `customerId` varchar(15) NOT NULL,
  `employeeId` varchar(15) NOT NULL,
  `sentDate` varchar(40) NOT NULL,
  `sentTime` varchar(40) NOT NULL,
  `sender` varchar(15) NOT NULL,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`messageId`, `messageContent`, `customerId`, `employeeId`, `sentDate`, `sentTime`, `sender`) VALUES
('MES1700540149', 'Hi ', 'CX1700540110', '', '20/11/2023', '23:15', 'Employee'),
('MES1700540902', 'Hi, please review the request I just submit to have an equipment rented.', 'CX1700540577', '', '20/11/2023', '23:28', 'Customer'),
('MES1700541194', 'Hi, we will have your request reviewed', 'CX1700540577', 'Test0021', '20/11/2023', '23:33', 'Employee');

-- --------------------------------------------------------

--
-- Table structure for table `rental_request`
--

CREATE TABLE IF NOT EXISTS `rental_request` (
  `rentalRequestId` varchar(15) NOT NULL,
  `customerId` varchar(15) NOT NULL,
  `equipmentId` varchar(15) NOT NULL,
  `employeeId` varchar(15) DEFAULT NULL,
  `status` varchar(15) NOT NULL,
  `startDate` varchar(30) NOT NULL,
  `returnDate` varchar(30) NOT NULL,
  `totalCost` float NOT NULL,
  `event` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rental_request`
--

INSERT INTO `rental_request` (`rentalRequestId`, `customerId`, `equipmentId`, `employeeId`, `status`, `startDate`, `returnDate`, `totalCost`, `event`) VALUES
('RR1700540667', 'CX1700540577', 'EQ1700540456', 'Test0021', 'Completed', '21/11/2023', '25/11/2023', 25000, 'Party');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `transactionId` varchar(15) NOT NULL,
  `transactionDate` varchar(50) NOT NULL,
  `transactionTime` varchar(15) NOT NULL,
  `equipmentId` varchar(15) NOT NULL,
  `event` varchar(100) NOT NULL,
  `customerId` varchar(15) NOT NULL,
  `employeeId` varchar(15) NOT NULL,
  `totalAmount` float NOT NULL,
  `rentalRequestId` varchar(150) NOT NULL,
  PRIMARY KEY (`transactionId`),
  UNIQUE KEY `transactionId` (`transactionId`),
  KEY `customerId` (`customerId`,`employeeId`),
  KEY `employeeId` (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transactionId`, `transactionDate`, `transactionTime`, `equipmentId`, `event`, `customerId`, `employeeId`, `totalAmount`, `rentalRequestId`) VALUES
('EQ1700540991', '2023-11-20', '23:29', 'RR1700540667', 'Event', 'CX1700540577', 'Test0021', 25000, 'RR1700540667');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userId` varchar(15) NOT NULL,
  `passwordHash` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `passwordHash`) VALUES
('AM80018', 'null'),
('AM80019', 'null'),
('A8uMopj99yu989', 'passwuiuord'),
('Test0021', 'passwuiuord'),
('CX1700540015', '12345678'),
('CX1700540110', '12345678'),
('CX1700540577', '12345678');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON UPDATE CASCADE,
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
