CREATE TABLE `assignment3`.`member` (
  `membId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `membType` VARCHAR(10) NOT NULL,
  `membDate` DATE NOT NULL,
  `expiryDate` DATE NOT NULL,
  PRIMARY KEY (`membId`));

  
CREATE TABLE `assignment3`.`publisher` (
  `pubId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`pubId`));

  
CREATE TABLE `books` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `price` decimal(7,2) NOT NULL DEFAULT '0.00',
  `available` char(1) NOT NULL DEFAULT 'N',
  `pubId` int(11) NOT NULL,
  `membId` int(11) DEFAULT NULL,
  `issueDate` date DEFAULT NULL,
  `dueDate` date DEFAULT NULL,
  `returnDate` date DEFAULT NULL,
  PRIMARY KEY (`bookId`),
  KEY `fk_pubId_idx` (`pubId`),
  KEY `fk_membId_idx` (`membId`),
  CONSTRAINT `fk_membId` FOREIGN KEY (`membId`) REFERENCES `member` (`membId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pubId` FOREIGN KEY (`pubId`) REFERENCES `publisher` (`pubId`) ON DELETE NO ACTION ON UPDATE NO ACTION
);



