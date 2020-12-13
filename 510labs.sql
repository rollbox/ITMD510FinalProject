-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: 2020-12-12 01:38:48
-- 服务器版本： 5.7.31-log
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `510labs`
--

-- --------------------------------------------------------

--
-- 表的结构 `qy_gx_admin`
--

CREATE TABLE `qy_gx_admin` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `account` varchar(100) DEFAULT NULL COMMENT 'account',
  `password` varchar(999) DEFAULT NULL COMMENT 'password',
  `salt` varchar(32) NOT NULL COMMENT 'salt',
  `role` varchar(255) DEFAULT NULL COMMENT 'role'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='qy_gx_admin';

--
-- 转存表中的数据 `qy_gx_admin`
--

INSERT INTO `qy_gx_admin` (`id`, `account`, `password`, `salt`, `role`) VALUES
(18, '1', '890e444fdb6ee39aecb8b50266b0c515949aa28a160e494c4ddd4f19f63f8f03', 'salt9335', 'CustomerService'),
(19, '5', '2b77752423030d06d990f0d077c8774bc7add34698fc1c9254933118b1e0b2fb', 'salt6008', '1'),
(20, 'test', 'e7009d2fe142ac774e8e47ba7cf69a72b4b1cab42bac1ef549a381751e9195b5', 'salt4857', 'CustomerService'),
(21, 'admin', '5cf449027aa8f25c616e28a28c55151788a614e389f48a3804c2c5fb7c658e4d', 'salt7279', 'CustomerService');

-- --------------------------------------------------------

--
-- 表的结构 `qy_gx_all_cust`
--

CREATE TABLE `qy_gx_all_cust` (
  `phone` varchar(99) NOT NULL COMMENT 'phone',
  `name` varchar(999) DEFAULT NULL COMMENT 'name',
  `address` varchar(999) DEFAULT NULL COMMENT 'address',
  `products` varchar(99) DEFAULT NULL COMMENT 'products',
  `sex` varchar(99) DEFAULT NULL COMMENT 'sex',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `group_id` varchar(99) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='qy_gx_all_cust';

--
-- 转存表中的数据 `qy_gx_all_cust`
--

INSERT INTO `qy_gx_all_cust` (`phone`, `name`, `address`, `products`, `sex`, `createtime`, `group_id`) VALUES
('1370001', 'name0001', 'add0001', 'p1,p2', 'sex0001', '2020-12-11 18:32:27', '1'),
('1370002', 'name0002', 'add0002', 'p1,p2', 'sex0002', '2020-12-11 18:32:27', '2'),
('1370003', 'name0003', 'add0003', 'p1,p2', 'sex0003', '2020-12-11 18:32:27', '3'),
('1370004', 'name0004', 'add0004', 'p1,p2', 'sex0004', '2020-12-11 18:32:27', '4'),
('1370005', 'name0005', 'add0005', 'p1,p2', 'sex0005', '2020-12-11 18:32:27', '5'),
('1370006', 'name0006', 'add0006', 'p1,p2', 'sex0006', '2020-12-11 18:32:27', '6'),
('1370007', 'name0007', 'add0007', 'p1,p2', 'sex0007', '2020-12-11 18:32:27', '7'),
('1370008', 'name0008', 'add0008', 'p1,p2', 'sex0008', '2020-12-11 18:32:27', '8'),
('1370009', 'name0009', 'add0009', 'p1,p2', 'sex0009', '2020-12-11 18:32:27', '9'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '2020-12-11 23:28:28', '20201212072820'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '2020-12-11 23:45:05', '20201212074500'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '2020-12-11 23:53:06', '20201212075248'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '2020-12-12 00:03:18', '20201212080305'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '2020-12-12 00:06:32', '20201212080626'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '2020-12-12 00:19:56', '20201212081953'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '2020-12-12 00:29:38', '20201212082932'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '2020-12-12 00:30:27', '20201212083022'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '2020-12-12 00:33:41', '20201212083329');

-- --------------------------------------------------------

--
-- 表的结构 `qy_gx_big_cust`
--

CREATE TABLE `qy_gx_big_cust` (
  `phone` varchar(99) NOT NULL COMMENT 'phone',
  `name` varchar(999) DEFAULT NULL,
  `address` varchar(999) DEFAULT NULL,
  `products` varchar(999) DEFAULT NULL,
  `sex` varchar(99) DEFAULT NULL,
  `group_id` varchar(999) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='qy_gx_big_cust';

--
-- 转存表中的数据 `qy_gx_big_cust`
--

INSERT INTO `qy_gx_big_cust` (`phone`, `name`, `address`, `products`, `sex`, `group_id`, `createtime`) VALUES
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '20201212024918', '2020-12-11 18:49:19'),
('', '', '', '', '', '20201212031841', '2020-12-11 19:18:42'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '20201212031841', '2020-12-11 19:18:42'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '20201212032946', '2020-12-11 19:29:47'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '20201212053112', '2020-12-11 21:31:12'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '20201212070952', '2020-12-11 23:09:53'),
('123456789', 'Dear Papa', 'Chicago', '$31 marketing product', 'male', '20201212072537', '2020-12-11 23:25:37');

-- --------------------------------------------------------

--
-- 表的结构 `qy_gx_group`
--

CREATE TABLE `qy_gx_group` (
  `group_id` bigint(20) UNSIGNED NOT NULL,
  `group_name` varchar(999) DEFAULT NULL COMMENT 'group_name',
  `group_desc` varchar(999) DEFAULT NULL COMMENT 'group_desc',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='qy_gx_group';

--
-- 转存表中的数据 `qy_gx_group`
--

INSERT INTO `qy_gx_group` (`group_id`, `group_name`, `group_desc`, `createtime`) VALUES
(1, 'groupname001', 'NEW CUST GROUP DESC', '2020-12-11 18:41:09'),
(3, 'groupname003', 'groupdesc003', '2020-12-11 18:41:09'),
(4, 'groupname004', 'groupdesc004', '2020-12-11 18:41:09'),
(5, 'groupname005', 'groupdesc005', '2020-12-11 18:41:09'),
(6, 'groupname006', 'groupdesc006', '2020-12-11 18:41:09'),
(7, 'groupname007', 'groupdesc007', '2020-12-11 18:41:09'),
(8, 'groupname008', 'groupdesc008', '2020-12-11 18:41:09'),
(9, 'groupname009', 'groupdesc009', '2020-12-11 18:41:09'),
(20201212072820, '1', '1', '2020-12-11 23:28:26'),
(20201212074500, '1', '1', '2020-12-11 23:45:05'),
(20201212075248, '2', '2', '2020-12-11 23:53:06'),
(20201212080305, '3', '3', '2020-12-12 00:03:17'),
(20201212080626, '5', '5', '2020-12-12 00:06:30'),
(20201212081953, '1', '1', '2020-12-12 00:19:56'),
(20201212082932, '11', '111', '2020-12-12 00:29:37'),
(20201212083022, '1', '1', '2020-12-12 00:30:26'),
(20201212083329, '111', '111', '2020-12-12 00:33:40');

-- --------------------------------------------------------

--
-- 表的结构 `qy_gx_market_event`
--

CREATE TABLE `qy_gx_market_event` (
  `event_id` bigint(20) UNSIGNED NOT NULL,
  `event_name` varchar(999) DEFAULT NULL COMMENT 'event_name',
  `product_id` varchar(999) DEFAULT NULL COMMENT 'product_id',
  `product_name` varchar(999) DEFAULT NULL COMMENT 'product_name',
  `group_id` varchar(999) DEFAULT NULL COMMENT 'group_id',
  `group_name` varchar(999) DEFAULT NULL COMMENT 'group_name',
  `salesmanship` varchar(999) DEFAULT NULL COMMENT 'salesmanship',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='qy_gx_market_event';

--
-- 转存表中的数据 `qy_gx_market_event`
--

INSERT INTO `qy_gx_market_event` (`event_id`, `event_name`, `product_id`, `product_name`, `group_id`, `group_name`, `salesmanship`, `createtime`) VALUES
(20201212051851, 'ttt222', '1', 'productname001', '1', 'groupname001', ' NEW SALESMANSHIP', '2020-12-11 21:18:51'),
(20201212055823, 'fdafad', '2', 'productname002', '1', 'groupname001', 'fdafda', '2020-12-11 21:58:24'),
(20201212103323, '饿饿饿', '20201212062118', '新类别', '1', 'groupname001', '呃呃呃呃', '2020-12-12 02:33:23');

-- --------------------------------------------------------

--
-- 表的结构 `qy_gx_product`
--

CREATE TABLE `qy_gx_product` (
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `product_name` varchar(999) DEFAULT NULL COMMENT 'product_name',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='qy_gx_product';

--
-- 转存表中的数据 `qy_gx_product`
--

INSERT INTO `qy_gx_product` (`product_id`, `product_name`, `createtime`) VALUES
(20201212062118, '新类别', '2020-12-11 22:21:19'),
(20201212075059, 'dfafdafdafda', '2020-12-11 23:50:59'),
(20201212075103, '新类别fdafda', '2020-12-11 23:51:03'),
(20201212075107, '新类别111', '2020-12-11 23:51:08'),
(20201212075112, '新类别gggg', '2020-12-11 23:51:12'),
(20201212082215, 'New product', '2020-12-12 00:22:15'),
(20201212082537, 'New product', '2020-12-12 00:25:37');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `qy_gx_admin`
--
ALTER TABLE `qy_gx_admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uk_account` (`account`) USING BTREE,
  ADD KEY `id` (`id`);

--
-- Indexes for table `qy_gx_all_cust`
--
ALTER TABLE `qy_gx_all_cust`
  ADD KEY `phone` (`phone`);

--
-- Indexes for table `qy_gx_big_cust`
--
ALTER TABLE `qy_gx_big_cust` 
  ADD KEY `group_id` (`group_id`);

--
-- Indexes for table `qy_gx_group`
--
ALTER TABLE `qy_gx_group`
  ADD PRIMARY KEY (`group_id`),
  ADD KEY `group_id` (`group_id`);

--
-- Indexes for table `qy_gx_market_event`
--
ALTER TABLE `qy_gx_market_event`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `event_id` (`event_id`);

--
-- Indexes for table `qy_gx_product`
--
ALTER TABLE `qy_gx_product`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `product_id` (`product_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `qy_gx_admin`
--
ALTER TABLE `qy_gx_admin`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- 使用表AUTO_INCREMENT `qy_gx_group`
--
ALTER TABLE `qy_gx_group`
  MODIFY `group_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2147483647;
--
-- 使用表AUTO_INCREMENT `qy_gx_market_event`
--
ALTER TABLE `qy_gx_market_event`
  MODIFY `event_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2147483647;
--
-- 使用表AUTO_INCREMENT `qy_gx_product`
--
ALTER TABLE `qy_gx_product`
  MODIFY `product_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2147483647;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
