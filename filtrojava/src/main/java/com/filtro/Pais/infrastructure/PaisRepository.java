package com.filtro.Pais.infrastructure;
import java.util.Optional;
import java.util.List;
import com.filtro.Pais.domain.Pais;


public interface PaisRepository {
    void save(Pais pais);
    void update(Pais pais);
    Optional<Pais> findById(int id);
    void delete(int id);
    List<Pais> findAll();
}
