CREATE DATABASE  IF NOT EXISTS `anslutningslaget`;
USE `anslutningslaget`;

CREATE TABLE `kallsystem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `kategori` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `organisatoriskenhet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tjanstekontrakt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ursprungligkonsument` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `vardenhet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `vardgivare` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `anslutning` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kallsystem` bigint(20) NOT NULL,
  `kategori` bigint(20) NOT NULL,
  `oldest` datetime DEFAULT NULL,
  `organisatoriskenhet` bigint(20) NOT NULL,
  `tjanstekontrakt` bigint(20) NOT NULL,
  `ursprungligkonsument` bigint(20) NOT NULL,
  `vardenhet` bigint(20) NOT NULL,
  `vardgivare` bigint(20) NOT NULL,
  `youngest` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_ANSLUTNING` (`vardgivare`,`vardenhet`,`organisatoriskenhet`,`tjanstekontrakt`,`kategori`,`kallsystem`,`ursprungligkonsument`),
  KEY `fk_kallsystem_idx` (`kallsystem`), CONSTRAINT `fk_kallsystem` FOREIGN KEY (`kallsystem`) REFERENCES `kallsystem` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `fk_kategori_idx` (`kategori`), CONSTRAINT `fk_kategori` FOREIGN KEY (`kategori`) REFERENCES `kategori` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `fk_organisatoriskenhet_idx` (`organisatoriskenhet`), CONSTRAINT `fk_organisatoriskenhet` FOREIGN KEY (`organisatoriskenhet`) REFERENCES `organisatoriskenhet` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `fk_tjanstekontrakt_idx` (`tjanstekontrakt`), CONSTRAINT `fk_tjanstekontrakt` FOREIGN KEY (`tjanstekontrakt`) REFERENCES `tjanstekontrakt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `fk_ursprungligkonsument_idx` (`ursprungligkonsument`), CONSTRAINT `fk_ursprungligkonsument` FOREIGN KEY (`ursprungligkonsument`) REFERENCES `ursprungligkonsument` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `fk_vardenhet_idx` (`vardenhet`), CONSTRAINT `fk_vardenhet` FOREIGN KEY (`vardenhet`) REFERENCES `vardenhet` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `fk_vardgivare_idx` (`vardgivare`), CONSTRAINT `fk_vardgivare` FOREIGN KEY (`vardgivare`) REFERENCES `vardgivare` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;