-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Hostiteľ: 127.0.0.1
-- Čas generovania: Po 09.Máj 2016, 04:56
-- Verzia serveru: 5.7.9
-- Verzia PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáza: `sovybanka`
--

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `accounts`
--

DROP TABLE IF EXISTS `accounts`;
CREATE TABLE IF NOT EXISTS `accounts` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Balance` double DEFAULT NULL,
  `clientid` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `clientid` (`clientid`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `accounts`
--

INSERT INTO `accounts` (`ID`, `Balance`, `clientid`) VALUES
(1, 1500, 2),
(2, 450, 2),
(3, 500, 1),
(5, 950, 5),
(7, 800, 4),
(9, 500, 3);

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `cards`
--

DROP TABLE IF EXISTS `cards`;
CREATE TABLE IF NOT EXISTS `cards` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Active` char(1) DEFAULT NULL,
  `accountid` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `accountid` (`accountid`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `cards`
--

INSERT INTO `cards` (`ID`, `Active`, `accountid`) VALUES
(1, 'Y', 1),
(2, 'Y', 2),
(3, 'Y', 2),
(4, 'N', 3),
(5, 'Y', 5),
(6, 'Y', 7),
(7, 'Y', 1),
(8, 'N', 8);

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `dateofbirth` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `client`
--

INSERT INTO `client` (`ID`, `firstname`, `lastname`, `dateofbirth`) VALUES
(1, 'Marek', 'Velky', '1996-10-22'),
(2, 'Tomas', 'Kuzma', '1974-05-11'),
(3, 'Stefan', 'Molnar', '1977-01-04'),
(4, 'Miro', 'Petrik', '1977-01-04'),
(5, 'Oliver', 'Bugis', '1977-01-04'),
(10, 'Dominik', 'Kolesar', '1995-05-14'),
(9, 'Patrik', 'Paty', '1992-05-11');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `clientdetails`
--

DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE IF NOT EXISTS `clientdetails` (
  `ID` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  KEY `ID` (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `clientdetails`
--

INSERT INTO `clientdetails` (`ID`, `email`, `mobile`) VALUES
(1, 'marek.velky55@gmail.com', '0902845788'),
(2, 'tomas.kuzma@gmail.com', '0905699512'),
(3, 'stevkosomhej@gmail.com', '0904223002'),
(4, 'miro.petrik@gmail.com', '0906804294'),
(5, 'olivkobugisko@gmail.com', '0903623002'),
(8, 'sfsf', 'sdfs'),
(9, 'patrik.paty@email.sk', '0917111777'),
(10, 'dominik.kolesar@email.sk', '0917222333');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `client_login`
--

DROP TABLE IF EXISTS `client_login`;
CREATE TABLE IF NOT EXISTS `client_login` (
  `username` varchar(30) DEFAULT NULL,
  `ClientID` int(11) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `Active` char(1) DEFAULT NULL,
  KEY `ClientID` (`ClientID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `client_login`
--

INSERT INTO `client_login` (`username`, `ClientID`, `password`, `Active`) VALUES
('marekvel', 1, 'jasommarek', 'Y'),
('tomikuzma', 2, '12345678', 'Y'),
('stevo668', 3, 'kosice88', 'Y'),
('petrik1998', 4, 'batman55', 'Y'),
('olivertwist', 5, 'shakespeare', 'Y');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PositionID` int(11) DEFAULT NULL,
  `firstname` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `PositionID` (`PositionID`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `employee`
--

INSERT INTO `employee` (`ID`, `PositionID`, `firstname`, `lastname`) VALUES
(1, 1, 'Tomas', 'Barath'),
(2, 2, 'Marek', 'Zoldos'),
(3, 2, 'Osvald', 'Bara'),
(4, 4, 'Benjamin', 'Timko'),
(5, 3, 'Gregor', 'Lejko');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `employee_login`
--

DROP TABLE IF EXISTS `employee_login`;
CREATE TABLE IF NOT EXISTS `employee_login` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeID` int(11) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `employee_login`
--

INSERT INTO `employee_login` (`ID`, `EmployeeID`, `username`, `password`) VALUES
(1, 1, 'tomas123', '5845845'),
(2, 2, 'zolmar96', 'caesar'),
(3, 3, 'osytt569', 'broskyna'),
(4, 4, 'benfranklin', 'mojeheslo'),
(5, 5, 'grelejko', '19901111'),
(6, NULL, 'admin', 'admin1');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `history_login`
--

DROP TABLE IF EXISTS `history_login`;
CREATE TABLE IF NOT EXISTS `history_login` (
  `ClientID` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  KEY `ClientID` (`ClientID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `history_login`
--

INSERT INTO `history_login` (`ClientID`, `date`) VALUES
(1, '2016-02-22'),
(2, '2016-01-02'),
(2, '2016-03-15'),
(3, '2016-04-04'),
(3, '2016-04-05');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `invalidaccess`
--

DROP TABLE IF EXISTS `invalidaccess`;
CREATE TABLE IF NOT EXISTS `invalidaccess` (
  `username` varchar(30) DEFAULT NULL,
  `date` date DEFAULT NULL,
  KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `invalidaccess`
--

INSERT INTO `invalidaccess` (`username`, `date`) VALUES
('petrik1998', '2016-01-20');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `loans`
--

DROP TABLE IF EXISTS `loans`;
CREATE TABLE IF NOT EXISTS `loans` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClientID` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `paidup` double DEFAULT NULL,
  `interest` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ClientID` (`ClientID`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `loans`
--

INSERT INTO `loans` (`ID`, `ClientID`, `amount`, `paidup`, `interest`) VALUES
(1, 3, 5000, 2000, 10),
(2, 4, 10000, 1000, 15),
(3, 4, 5500, 1580, 180),
(4, 1, 456, 50, 15),
(5, 1, 50, 20, 5);

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `poswork`
--

DROP TABLE IF EXISTS `poswork`;
CREATE TABLE IF NOT EXISTS `poswork` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `poswork`
--

INSERT INTO `poswork` (`ID`, `name`) VALUES
(1, 'manager'),
(2, 'assistant'),
(3, 'engineer'),
(4, 'basic');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `transactions`
--

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE IF NOT EXISTS `transactions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `clientid` int(11) DEFAULT NULL,
  `employeeid` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `accountID` int(11) DEFAULT NULL,
  `value` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `accountID` (`accountID`),
  KEY `clientid` (`clientid`),
  KEY `employeeid` (`employeeid`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `transactions`
--

INSERT INTO `transactions` (`ID`, `clientid`, `employeeid`, `date`, `accountID`, `value`) VALUES
(1, 0, 2, '2016-02-02', 1, 500),
(2, 2, 0, '2015-10-02', 1, 100);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
