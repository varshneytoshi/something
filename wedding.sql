-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2018 at 04:19 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 5.6.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wedding`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `CartId` int(10) NOT NULL,
  `VenueId` int(10) NOT NULL,
  `MenuId` int(10) NOT NULL,
  `DelFlag` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`CartId`, `VenueId`, `MenuId`, `DelFlag`) VALUES
(1, 1, 1, 'f');

-- --------------------------------------------------------

--
-- Table structure for table `catering`
--

CREATE TABLE `catering` (
  `MenuId` int(11) NOT NULL,
  `PackageName` varchar(50) DEFAULT NULL,
  `MenuDescription` varchar(1000) DEFAULT NULL,
  `PricePerPlate` double DEFAULT NULL,
  `CultureId` int(11) NOT NULL,
  `PackageType` int(2) DEFAULT NULL,
  `DelFlag` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `catering`
--

INSERT INTO `catering` (`MenuId`, `PackageName`, `MenuDescription`, `PricePerPlate`, `CultureId`, `PackageType`, `DelFlag`) VALUES
(1, 'veg dinner', NULL, 100, 1, 0, 'f');

-- --------------------------------------------------------

--
-- Table structure for table `culture`
--

CREATE TABLE `culture` (
  `CultureId` int(10) NOT NULL,
  `CultureName` varchar(20) NOT NULL,
  `Culture_Creation_Id` int(10) DEFAULT NULL,
  `Culture_Creation_Date` date DEFAULT NULL,
  `Culture_Modification_Id` int(10) DEFAULT NULL,
  `Culture_Modification_Date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `culture`
--

INSERT INTO `culture` (`CultureId`, `CultureName`, `Culture_Creation_Id`, `Culture_Creation_Date`, `Culture_Modification_Id`, `Culture_Modification_Date`) VALUES
(1, 'hindu', 1, '2018-07-02', 1, '2018-07-02');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `ItemId` int(10) NOT NULL,
  `ItemName` varchar(30) DEFAULT NULL,
  `ItemType` varchar(20) DEFAULT NULL,
  `ItemDesription` varchar(100) DEFAULT NULL,
  `ItemPrice` double NOT NULL,
  `ItemImages` varchar(1000) DEFAULT NULL,
  `DelFlag` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`ItemId`, `ItemName`, `ItemType`, `ItemDesription`, `ItemPrice`, `ItemImages`, `DelFlag`) VALUES
(1, 'lamp', 'mehendi', 'light', 100, NULL, 'f');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `OrderId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `VenueID` int(11) NOT NULL,
  `MenuId` int(11) NOT NULL,
  `TotalPrice` double DEFAULT NULL,
  `DelFlag` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`OrderId`, `UserId`, `VenueID`, `MenuId`, `TotalPrice`, `DelFlag`) VALUES
(1, 1, 1, 1, 110000, 'f');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `usermail_id` varchar(255) DEFAULT NULL,
  `culture` int(11) DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  `est_budget` double DEFAULT NULL,
  `no_of_guest` double DEFAULT NULL,
  `no_of_wedding_days` double DEFAULT NULL,
  `user_contact` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_pass` varchar(255) DEFAULT NULL,
  `wedding_date` date DEFAULT NULL,
  `cart_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `usermail_id`, `culture`, `del_flag`, `est_budget`, `no_of_guest`, `no_of_wedding_days`, `user_contact`, `user_name`, `user_pass`, `wedding_date`, `cart_id`) VALUES
(1, 'gayu@gmail', 1, 'f', 120000, 120, 2, '12134', 'gayu', '123', '2018-07-25', 1);

-- --------------------------------------------------------

--
-- Table structure for table `venue`
--

CREATE TABLE `venue` (
  `VenueId` int(11) NOT NULL,
  `VenueName` varchar(100) DEFAULT NULL,
  `VenueType` varchar(50) DEFAULT NULL,
  `VenueLocation` varchar(1000) DEFAULT NULL,
  `VenuePrice` double DEFAULT NULL,
  `VenueCapacity` int(10) DEFAULT NULL,
  `DelFlag` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `venue`
--

INSERT INTO `venue` (`VenueId`, `VenueName`, `VenueType`, `VenueLocation`, `VenuePrice`, `VenueCapacity`, `DelFlag`) VALUES
(1, 'goa hall', 'destination', 'goa', 100000, 1000, 'f');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`CartId`),
  ADD KEY `MenuId` (`MenuId`),
  ADD KEY `VenueId` (`VenueId`);

--
-- Indexes for table `catering`
--
ALTER TABLE `catering`
  ADD PRIMARY KEY (`MenuId`);

--
-- Indexes for table `culture`
--
ALTER TABLE `culture`
  ADD PRIMARY KEY (`CultureId`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`ItemId`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`OrderId`),
  ADD KEY `UserId` (`UserId`),
  ADD KEY `MenuId` (`MenuId`),
  ADD KEY `VenueID` (`VenueID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `CartId` (`cart_id`);

--
-- Indexes for table `venue`
--
ALTER TABLE `venue`
  ADD PRIMARY KEY (`VenueId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `catering`
--
ALTER TABLE `catering`
  MODIFY `MenuId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `culture`
--
ALTER TABLE `culture`
  MODIFY `CultureId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `ItemId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `OrderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `venue`
--
ALTER TABLE `venue`
  MODIFY `VenueId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`MenuId`) REFERENCES `catering` (`MenuId`),
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`VenueId`) REFERENCES `venue` (`VenueId`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`MenuId`) REFERENCES `catering` (`MenuId`),
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`VenueID`) REFERENCES `venue` (`VenueId`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`CartId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
