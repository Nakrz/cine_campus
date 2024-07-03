CREATE DATABASE cinecampus;
USE cinecampus;

CREATE TABLE tipo_actor(
    id INT AUTO_INCREMENT,
    descripcion VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE genero(
    id INT AUTO_INCREMENT,
    descripcion VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE pais(
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE formato(
    id INT AUTO_INCREMENT,
    descripcion VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE pelicula(
    id INT AUTO_INCREMENT,
    codinterno VARCHAR(5) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    duracion VARCHAR(20) NOT NULL,
    sinopsis TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE pelicula_formato (
    idpelicula INT,
    idformato INT,
    cantidad INT,
    PRIMARY KEY (idpelicula, idformato),
    FOREIGN KEY (idpelicula) REFERENCES pelicula(id),
    FOREIGN KEY (idformato) REFERENCES formato(id)
);


CREATE TABLE actor(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    idnacionalidad int(30),
    edad INT,
    idgenero INT,
    FOREIGN KEY (idgenero) REFERENCES genero(id),
    FOREIGN key (idnacionalidad)REFERENCES pais(id)
);

CREATE TABLE pelicula_protagonista (
    idpelicula INT,
    idprotagonista INT,
    idtipoactor INT,
    PRIMARY KEY (idpelicula, idprotagonista),
    FOREIGN KEY (idpelicula) REFERENCES pelicula(id),
    FOREIGN KEY (idprotagonista) REFERENCES actor(id),
    FOREIGN KEY (idtipoactor) REFERENCES tipo_actor(id)
);

INSERT INTO tipo_actor VALUES (1,"decripcion1"),(2,"decripcion2");
INSERT INTO genero VALUES (1,"Hombre"), (2,"Mujer");
INSERT INTO pais VALUES (1,"Argentina"), (2,"EspaÃ±a");
INSERT INTO formato VALUES (1,"DVD"), (2,"CD");
INSERT INTO pelicula VALUES (1, "COD01", "AVENGERS", "2HORAS", "TEXTO DE DESCRIPCION"), (2, "COD02", "CUALQUIERA", "2HORAS", "TEXTO DE DESCRIPCION2");
INSERT INTO pelicula_formato VALUES (1,1,5),(2,2,20);
INSERT INTO actor VALUES (1, "nombre1", 1, 30, 2);
INSERT INTO pelicula_protagonista VALUES ( 1, 1, 2);