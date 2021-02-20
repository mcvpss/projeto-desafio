CREATE TABLE `cidade` (
  `id_cid` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `cliente` (
  `id_cli` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `sexo` char(1) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `idade` smallint(3) DEFAULT NULL,
  `id_cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_cli`),
  KEY `fk_cidade_idx` (`id_cid`),
  CONSTRAINT `fk_cidade` FOREIGN KEY (`id_cid`) REFERENCES `cidade` (`id_cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
