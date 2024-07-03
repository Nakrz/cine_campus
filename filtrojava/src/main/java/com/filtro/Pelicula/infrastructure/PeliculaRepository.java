package com.filtro.Pelicula.infrastructure;

import java.util.List;
import java.util.Optional;

import com.filtro.Pelicula.domain.Pelicula;

public interface PeliculaRepository {

    void save(Pelicula pelicula);
    void update(Pelicula pelicula);
    Optional<Pelicula> findById(int id);
    void delete(int id);
    List<Pelicula> findAll();
}
