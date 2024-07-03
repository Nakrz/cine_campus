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
    descripcion VARCHAR(50) NOT NULL,
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


CREATE TABLE actor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    idnacionalidad INT,
    edad INT,
    idgenero INT,
    FOREIGN KEY (idgenero) REFERENCES genero(id) ON DELETE CASCADE,
    FOREIGN KEY (idnacionalidad) REFERENCES pais(id) ON DELETE CASCADE
);



CREATE TABLE pelicula_protagonista (
    idpelicula INT,
    idprotagonista INT,
    idtipoactor INT,
    PRIMARY KEY (idpelicula, idprotagonista),
    FOREIGN KEY (idpelicula) REFERENCES pelicula(id) ON DELETE CASCADE,
    FOREIGN KEY (idprotagonista) REFERENCES actor(id) ON DELETE CASCADE,
    FOREIGN KEY (idtipoactor) REFERENCES tipo_actor(id)
);
