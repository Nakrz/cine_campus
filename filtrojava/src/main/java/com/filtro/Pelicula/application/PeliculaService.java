package com.filtro.Pelicula.application;

import java.util.List;
import java.util.Optional;

import com.filtro.Pelicula.domain.Pelicula;
import com.filtro.Pelicula.infrastructure.PeliculaRepository;

public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public void createPelicula(Pelicula pelicula){
        peliculaRepository.save(pelicula);
    }

    public void updatePelicula(Pelicula pelicula){
        peliculaRepository.update(pelicula);
    }

    public Optional<Pelicula> findPeliculaById(int id){
        return peliculaRepository.findById(id);
    }

    public void deletePelicula(int id){
        peliculaRepository.delete(id);
    }

    public List<Pelicula> findAllPeliculas(){
        return peliculaRepository.findAll();
    }

}
