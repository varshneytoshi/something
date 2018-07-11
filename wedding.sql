-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2018 at 08:13 AM
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
  `cart_id` int(10) NOT NULL,
  `venue_id` int(10) NOT NULL,
  `menu_id` int(10) NOT NULL,
  `del_flag` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cart_id`, `venue_id`, `menu_id`, `del_flag`) VALUES
(1, 1, 1, 0),
(2, 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `catering`
--

CREATE TABLE `catering` (
  `menu_id` int(11) NOT NULL,
  `package_name` varchar(50) DEFAULT NULL,
  `menu_description` varchar(1000) DEFAULT NULL,
  `price_per_plate` double DEFAULT NULL,
  `culture_id` int(11) NOT NULL,
  `package_type` int(2) DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `catering`
--

INSERT INTO `catering` (`menu_id`, `package_name`, `menu_description`, `price_per_plate`, `culture_id`, `package_type`, `del_flag`) VALUES
(1, 'veg dinner', NULL, 100, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `culture`
--

CREATE TABLE `culture` (
  `culture_id` int(10) NOT NULL,
  `culture_name` varchar(20) NOT NULL,
  `culture_creation_id` int(10) DEFAULT NULL,
  `culture_creation_date` date DEFAULT NULL,
  `culture_modification_id` int(10) DEFAULT NULL,
  `culture_modification_date` date DEFAULT NULL,
  `del_flag` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `culture`
--

INSERT INTO `culture` (`culture_id`, `culture_name`, `culture_creation_id`, `culture_creation_date`, `culture_modification_id`, `culture_modification_date`, `del_flag`) VALUES
(1, 'hindu', 1, '2018-07-02', 1, '2018-07-02', 0),
(2, 'Punjabi', 1, '2018-07-02', 1, '2018-07-02', 0),
(3, 'Christian', 1, '2018-07-02', 1, '2018-07-02', 0),
(4, 'Islam', 1, '2018-07-02', 1, '2018-07-02', 0),
(5, 'Marathi', 1, '2018-07-02', 1, '2018-07-02', 0),
(6, 'Jain', 1, '2018-07-02', 1, '2018-07-02', 0);

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `event_id` int(11) NOT NULL,
  `event_name` varchar(30) DEFAULT NULL,
  `culture_id` int(11) NOT NULL,
  `del_flag` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`event_id`, `event_name`, `culture_id`, `del_flag`) VALUES
(1, 'Mehendi', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `event_item_mapper`
--

CREATE TABLE `event_item_mapper` (
  `ei_mapper_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `del_flag` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event_item_mapper`
--

INSERT INTO `event_item_mapper` (`ei_mapper_id`, `event_id`, `item_id`, `cart_id`, `del_flag`) VALUES
(1, 1, 1, 1, 0),
(2, 1, 1, 2, 0),
(3, 1, 2, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `item_id` int(10) NOT NULL,
  `item_name` varchar(30) DEFAULT NULL,
  `item_type` varchar(20) DEFAULT NULL,
  `item_description` varchar(100) DEFAULT NULL,
  `item_price` double NOT NULL,
  `item_images` varchar(1000) DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`item_id`, `item_name`, `item_type`, `item_description`, `item_price`, `item_images`, `del_flag`) VALUES
(1, 'lamp', 'mehendi', 'light', 100, NULL, 0),
(2, 'flowers', 'decor', 'decoration', 100, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `venue_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `total_price` double DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `order_item_mapper`
--

CREATE TABLE `order_item_mapper` (
  `oi_mapper_id` int(11) NOT NULL,
  `order_id` varchar(20) NOT NULL,
  `item_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `del_flag` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `usermail_id` varchar(255) DEFAULT NULL,
  `culture` int(11) DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL,
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
(1, 'gayu@gmail', 1, 0, 120000, 120, 2, '12134', 'gayu', '123', '2018-07-25', 1),
(2, 'toshi@gmail', 1, 0, 1200000, 1000, 2, '234143', 'toshi', '12345', '2018-07-26', 2);

-- --------------------------------------------------------

--
-- Table structure for table `venue`
--

CREATE TABLE `venue` (
  `venue_id` int(11) NOT NULL,
  `venue_name` varchar(100) DEFAULT NULL,
  `venue_type` varchar(50) DEFAULT NULL,
  `venue_location` varchar(1000) DEFAULT NULL,
  `venue_price` double DEFAULT NULL,
  `venue_capacity` int(10) DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `venue`
--

INSERT INTO `venue` (`venue_id`, `venue_name`, `venue_type`, `venue_location`, `venue_price`, `venue_capacity`, `del_flag`) VALUES
(1, 'goa hall', 'destination', 'goa', 100000, 1000, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `MenuId` (`menu_id`),
  ADD KEY `VenueId` (`venue_id`);

--
-- Indexes for table `catering`
--
ALTER TABLE `catering`
  ADD PRIMARY KEY (`menu_id`);

--
-- Indexes for table `culture`
--
ALTER TABLE `culture`
  ADD PRIMARY KEY (`culture_id`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `culture_id` (`culture_id`);

--
-- Indexes for table `event_item_mapper`
--
ALTER TABLE `event_item_mapper`
  ADD PRIMARY KEY (`ei_mapper_id`),
  ADD KEY `EventId` (`event_id`),
  ADD KEY `ItemId` (`item_id`),
  ADD KEY `cart_id` (`cart_id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `MenuId` (`menu_id`),
  ADD KEY `VenueID` (`venue_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `order_item_mapper`
--
ALTER TABLE `order_item_mapper`
  ADD PRIMARY KEY (`oi_mapper_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `item_id` (`item_id`),
  ADD KEY `order_id` (`order_id`);

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
  ADD PRIMARY KEY (`venue_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `catering`
--
ALTER TABLE `catering`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `culture`
--
ALTER TABLE `culture`
  MODIFY `culture_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `event_item_mapper`
--
ALTER TABLE `event_item_mapper`
  MODIFY `ei_mapper_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `item_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `order_item_mapper`
--
ALTER TABLE `order_item_mapper`
  MODIFY `oi_mapper_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `venue`
--
ALTER TABLE `venue`
  MODIFY `venue_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `catering` (`menu_id`),
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`venue_id`) REFERENCES `venue` (`venue_id`);

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `events_ibfk_1` FOREIGN KEY (`culture_id`) REFERENCES `culture` (`culture_id`);

--
-- Constraints for table `event_item_mapper`
--
ALTER TABLE `event_item_mapper`
  ADD CONSTRAINT `event_item_mapper_ibfk_2` FOREIGN KEY (`event_id`) REFERENCES `events` (`event_id`),
  ADD CONSTRAINT `event_item_mapper_ibfk_3` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`),
  ADD CONSTRAINT `event_item_mapper_ibfk_4` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `catering` (`menu_id`),
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`venue_id`) REFERENCES `venue` (`venue_id`),
  ADD CONSTRAINT `orders_ibfk_4` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `order_item_mapper`
--
ALTER TABLE `order_item_mapper`
  ADD CONSTRAINT `order_item_mapper_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `order_item_mapper_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`),
  ADD CONSTRAINT `order_item_mapper_ibfk_3` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
