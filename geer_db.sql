-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 04, 2023 at 02:40 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `geer_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customerId` varchar(15) NOT NULL,
  `firstName` varchar(25) NOT NULL,
  `lastName` varchar(25) NOT NULL,
  `phoneNumber` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `balance` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employeeId` varchar(15) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE `equipment` (
  `equipmentId` varchar(15) NOT NULL,
  `equipmentName` varchar(50) NOT NULL,
  `equipmentType` varchar(50) NOT NULL,
  `customerId` varchar(15) DEFAULT NULL,
  `eventId` varchar(15) DEFAULT NULL,
  `rented` tinyint(1) NOT NULL,
  `dateRented` date NOT NULL,
  `returnDate` date NOT NULL,
  `costPerDay` float NOT NULL,
  `totalCost` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `eventId` varchar(15) NOT NULL,
  `eventName` varchar(60) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `customerId` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `messageId` varchar(15) NOT NULL,
  `messageContent` varchar(1000) NOT NULL,
  `customerId` varchar(15) NOT NULL,
  `employeeId` varchar(15) NOT NULL,
  `sentDate` date NOT NULL,
  `sentTime` time NOT NULL,
  `responseDate` date NOT NULL,
  `responseTime` time NOT NULL,
  `respond` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transactionId` varchar(15) NOT NULL,
  `transactionDate` date NOT NULL,
  `transactionTime` date NOT NULL,
  `customerId` varchar(15) NOT NULL,
  `employeeId` varchar(15) NOT NULL,
  `totalAmount` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transaction_equipment`
--

CREATE TABLE `transaction_equipment` (
  `transactionId` varchar(15) NOT NULL,
  `equipmentId` varchar(15) NOT NULL,
  `transactionDate` date NOT NULL,
  `transactionTime` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transaction_event`
--

CREATE TABLE `transaction_event` (
  `transactionId` varchar(15) NOT NULL,
  `eventId` varchar(15) NOT NULL,
  `transactionDate` date NOT NULL,
  `transactionTime` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerId`),
  ADD UNIQUE KEY `customerId` (`customerId`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employeeId`),
  ADD UNIQUE KEY `employeeId` (`employeeId`);

--
-- Indexes for table `equipment`
--
ALTER TABLE `equipment`
  ADD UNIQUE KEY `equipmentId_2` (`equipmentId`),
  ADD KEY `equipmentId` (`equipmentId`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`eventId`),
  ADD UNIQUE KEY `eventId` (`eventId`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`messageId`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transactionId`),
  ADD UNIQUE KEY `transactionId` (`transactionId`),
  ADD UNIQUE KEY `transactionId_2` (`transactionId`),
  ADD KEY `customerId` (`customerId`,`employeeId`),
  ADD KEY `employeeId` (`employeeId`);

--
-- Indexes for table `transaction_equipment`
--
ALTER TABLE `transaction_equipment`
  ADD PRIMARY KEY (`transactionId`,`equipmentId`,`transactionDate`,`transactionTime`),
  ADD KEY `equipmentId` (`equipmentId`);

--
-- Indexes for table `transaction_event`
--
ALTER TABLE `transaction_event`
  ADD PRIMARY KEY (`transactionId`,`eventId`,`transactionDate`,`transactionTime`),
  ADD KEY `eventId` (`eventId`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON UPDATE CASCADE,
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`) ON UPDATE CASCADE;

--
-- Constraints for table `transaction_equipment`
--
ALTER TABLE `transaction_equipment`
  ADD CONSTRAINT `transaction_equipment_ibfk_1` FOREIGN KEY (`transactionId`) REFERENCES `transaction` (`transactionId`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `transaction_equipment_ibfk_2` FOREIGN KEY (`equipmentId`) REFERENCES `equipment` (`equipmentId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaction_event`
--
ALTER TABLE `transaction_event`
  ADD CONSTRAINT `transaction_event_ibfk_1` FOREIGN KEY (`transactionId`) REFERENCES `transaction` (`transactionId`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `transaction_event_ibfk_2` FOREIGN KEY (`eventId`) REFERENCES `event` (`eventId`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
