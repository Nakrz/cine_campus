package com.filtro.Genero.application;

import com.filtro.Genero.infrastructure.GeneroRepository;
import com.filtro.Genero.domain.Genero;
import java.util.List;
import java.util.Optional;


public class GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public void createGenero(Genero genero){
        generoRepository.save(genero);
    }

    public void updateGenero(Genero genero){
        generoRepository.update(genero);
    }

    public Optional<Genero> findGeneroById(int id){
        return generoRepository.findById(id);
    }

    public void deleteGenero(int id){
        generoRepository.delete(id);
    }

    public List<Genero> findAllGeneros(){
        return generoRepository.findAll();
    }
}
