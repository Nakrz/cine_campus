INSERT INTO tipo_actor (descripcion) VALUES('Principal'),('Secundario');
INSERT INTO genero (descripcion) VALUES('Acción'),('Comedia');
INSERT INTO pais (descripcion) VALUES('Estados Unidos'),('Argentina');
INSERT INTO formato (descripcion) VALUES('DVD'),('Blu-ray');
INSERT INTO pelicula (codinterno, nombre, duracion, sinopsis) VALUES
('P001', 'Pulp Fiction', '154 minutos', 'Una serie de historias entrelazadas en el mundo del crimen de Los Ángeles.'),
('P002', 'El Padrino', '175 minutos', 'La historia de la familia mafiosa Corleone en Nueva York.');
INSERT INTO pelicula_formato (idpelicula, idformato, cantidad) VALUES(1, 1, 2), (2, 2, 1);
INSERT INTO actor (nombre, idnacionalidad, edad, idgenero) VALUES('John Travolta', 1, 68, 1), ('Al Pacino', 2, 81, 2); 
INSERT INTO pelicula_protagonista (idpelicula, idprotagonista, idtipoactor) VALUES(1, 1, 1), (2, 2, 1); 





