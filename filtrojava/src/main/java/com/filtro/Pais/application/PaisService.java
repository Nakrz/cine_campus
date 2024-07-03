package com.filtro.Pais.application;

import java.util.List;
import java.util.Optional;
import com.filtro.Pais.domain.Pais;
import com.filtro.Pais.infrastructure.PaisRepository;


public class PaisService {

    private final PaisRepository paisRepository;

    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    public void createPais(Pais pais){
        paisRepository.save(pais);
    }

    public void updatePais(Pais pais){
        paisRepository.update(pais);
    }

    public Optional<Pais> getPaisById(int id){
        return paisRepository.findById(id);
    }

    public void deletePais(int id){
        paisRepository.delete(id);
    }

    public List<Pais> getAllPaises(){
        return paisRepository.findAll();
    }
}
