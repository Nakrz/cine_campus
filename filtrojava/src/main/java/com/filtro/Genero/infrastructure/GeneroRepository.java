package com.filtro.Genero.infrastructure;

import java.util.Optional;
import java.util.List;
import com.filtro.Genero.domain.Genero;

public interface GeneroRepository {
    void save(Genero genero);
    void update(Genero genero);
    Optional<Genero> findById(int id);
    void delete(int id);
    List<Genero> findAll();
}
