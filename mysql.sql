drop database IF EXISTS dbalunospromedio;
CREATE DATABASE IF NOT EXISTS dbalunospromedio;
USE dbalunospromedio; 

CREATE TABLE alumno (
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  apellido VARCHAR(50) NOT NULL,
  nota1 DOUBLE NOT NULL,
  nota2 DOUBLE NOT NULL,
  nota3 DOUBLE NOT NULL,
  promedio DOUBLE NOT NULL,
  PRIMARY KEY (id)
);