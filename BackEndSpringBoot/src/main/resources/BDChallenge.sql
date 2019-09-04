CREATE SCHEMA IF NOT EXISTS `todo1bdtechnicaltest` DEFAULT CHARACTER SET utf8 ;
USE `todo1bdtechnicaltest` ;

CREATE TABLE category (
  `idcategory` int(11) NOT NULL AUTO_INCREMENT,
  `key` VARCHAR(30) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `value`  int(4) NOT NULL,
  PRIMARY KEY (`idcategory`))
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE person (
  `idperson` int(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NULL,
  `lastname` VARCHAR(250) NULL,
  `identificationnumber` VARCHAR(45) NULL, 
  `personcategory` INT NULL,
  `creationdate` date NOT NULL,
  `modificationdate` date DEFAULT NULL,
  PRIMARY KEY (`idperson`),
  UNIQUE INDEX `identificationnumber_UNIQUE` (`identificationnumber` ASC),
  INDEX `fk_person_idx` (`personcategory` ASC),
  CONSTRAINT `fk_person_idcategory_category`
    FOREIGN KEY (`personcategory`)
    REFERENCES `category` (`idcategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE product (
  `idproduct` int(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NULL,
  `description` VARCHAR(250) NULL,
  `price` decimal(10,2) NOT NULL,
  `stock` int(11) DEFAULT 0 NOT NULL,
  `productcategory` INT NULL,
  `creationdate` date NOT NULL,
  `modificationdate` date DEFAULT NULL,
  PRIMARY KEY (`idproduct`),
  INDEX `fk_product_idx` (`productcategory` ASC),
  CONSTRAINT `fk_product_idcategory_category`
    FOREIGN KEY (`productcategory`)
    REFERENCES `category` (`idcategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE kardexin (
  `idkardexin` int(11) NOT NULL AUTO_INCREMENT,
  `sequential` int(11) NOT NULL, 
  `description` VARCHAR(250) NULL,
  `idperson` int(11) NOT NULL,  
  `creationdate` date NOT NULL,
  PRIMARY KEY (`idkardexin`),
  INDEX `fk_kardexin_idx` (`idperson` ASC),
  CONSTRAINT `fk_kardexin_idperson_person`
    FOREIGN KEY (`idperson`)
    REFERENCES `person` (`idperson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE kardexindetail (
  `idkardexindetail` int(11) NOT NULL AUTO_INCREMENT,
  `idkardexin` int(11) NOT NULL,
  `idproduct` int(11) NOT NULL, 
  `quantity` int(11) NOT NULL, 
  PRIMARY KEY (`idkardexindetail`),
  INDEX `fk_idkardexindetail_idkardexin` (`idkardexin` ASC),
  CONSTRAINT `fk_kardexindetail_idkardexin_kardexin`
    FOREIGN KEY (`idkardexin`)
    REFERENCES `kardexin` (`idkardexin`),
  INDEX `fk_idkardexindetail_idproduct` (`idproduct` ASC),
  CONSTRAINT `fk_kardexindetail_idproduct_product`
    FOREIGN KEY (`idproduct`)
    REFERENCES `product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE kardexout (
  `idkardexout` int(11) NOT NULL AUTO_INCREMENT,
  `sequential` int(11) NOT NULL, 
  `description` VARCHAR(250) NULL,
  `idperson` int(11) NOT NULL,  
  `creationdate` date NOT NULL,
  PRIMARY KEY (`idkardexout`),
  INDEX `fk_kardexout_idx` (`idperson` ASC),
  CONSTRAINT `fk_kardexout_idperson_person`
    FOREIGN KEY (`idperson`)
    REFERENCES `person` (`idperson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE kardexoutdetail (
  `idkardexoutdetail` int(11) NOT NULL AUTO_INCREMENT,
  `idkardexout` int(11) NOT NULL,
  `idproduct` int(11) NOT NULL, 
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`idkardexoutdetail`),
  INDEX `fk_idkardexoutdetail_idkardexout` (`idkardexout` ASC),
  CONSTRAINT `fk_kardexoutdetail_idkardexout_kardexout`
    FOREIGN KEY (`idkardexout`)
    REFERENCES `kardexout` (`idkardexout`),
  INDEX `fk_idkardexoutdetail_idproduct` (`idproduct` ASC),
  CONSTRAINT `fk_kardexoutdetail_idproduct_product`
    FOREIGN KEY (`idproduct`)
    REFERENCES `product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `category` (`idcategory`, `key`, `name`, `value`) 
VALUES (1,'personcategory','Vendedor','1');
INSERT INTO `category` (`idcategory`, `key`, `name`, `value`) 
VALUES (2,'personcategory','Empleado','2');
INSERT INTO `category` (`idcategory`, `key`, `name`, `value`) 
VALUES (3,'productcategory','Camiseta','1');
INSERT INTO `category` (`idcategory`, `key`, `name`, `value`) 
VALUES (4,'productcategory','Vaso','2');
INSERT INTO `category` (`idcategory`, `key`, `name`, `value`) 
VALUES (5,'productcategory','Juguete','3');
INSERT INTO `category` (`idcategory`, `key`, `name`, `value`) 
VALUES (6,'productcategory','Accesorio','4');