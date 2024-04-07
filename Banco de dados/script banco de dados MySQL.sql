Drop DATABASE  IF EXISTS sistema_de_cadastro_de_filmes;

CREATE DATABASE IF NOT EXISTS sistema_de_cadastro_de_filmes;
USE sistema_de_cadastro_de_filmes;

DROP TABLE IF EXISTS filme;
CREATE TABLE filme (
  id int NOT NULL AUTO_INCREMENT,
  nome_filme varchar(50) NOT NULL,
  dat_cad datetime NOT NULL default CURRENT_TIMESTAMP,
  situacao varchar(50) default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Cadastro dos setores do produtos';

INSERT INTO filme (nome_filme,dat_cad,situacao) VALUES 
 ('A Era do Gelo',NOW(),NULL),
 ('O Senhor Dos Aneis',NOW(),NULL),
 ('Rio',NOW(),NULL),
 ('Madagascar',NOW(),NULL);

DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
  id int NOT NULL auto_increment,
  nome varchar(45) NOT NULL,
  cpf varchar(15) NOT NULL,
  nivel int(11) default '0',
  senha varchar(20) NOT NULL default 'user123',
  dat_cad datetime NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (id),
  UNIQUE KEY cpf_UNIQUE (cpf)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO usuario (nome,cpf,nivel,senha,dat_cad) VALUES 
 ('Victor','111.111.111-11',1,'123',NOW());
