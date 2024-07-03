package com.filtro.Actor.application;

import java.util.List;
import java.util.Optional;

import com.filtro.Actor.infrastructure.ActorRepository;
import com.filtro.Genero.domain.Genero;
import com.filtro.Genero.infrastructure.GeneroRepository;
import com.filtro.Pais.domain.Pais;
import com.filtro.Pais.infrastructure.PaisRepository;
import com.filtro.Actor.domain.Actor;

public class ActorService {

    private final ActorRepository actorRepository;
    private final PaisRepository paisRepository; //inyecta pais
    private final GeneroRepository generoRepository; //inyecta genero


    public ActorService(ActorRepository actorRepository, PaisRepository paisRepository, GeneroRepository generoRepository){
        this.actorRepository = actorRepository;
        this.paisRepository = paisRepository;
        this.generoRepository = generoRepository;
    }

    public void createActor(Actor actor){
        actorRepository.save(actor);
    }

    public void updateActor(Actor actor){
        actorRepository.update(actor);
    }

    public Optional<Actor> findActorById(int id){
        return actorRepository.findById(id);
    }

    public void deleteActor(int id){
        actorRepository.delete(id);
    }

    public List<Actor> findAllActores(){
        return actorRepository.findAll();
    }

    //metodos de pais

    public void updatePais(Pais pais){
        paisRepository.update(pais);
    }

    public Optional<Pais> getPaisById(int id){
        return paisRepository.findById(id);
    }

    public List<Pais> getAllPaises(){
        return paisRepository.findAll();
    }

    //metodos de genero

    public void updateGenero(Genero genero){
        generoRepository.update(genero);
    }

    public Optional<Genero> findGeneroById(int id){
        return generoRepository.findById(id);
    }

    public List<Genero> findAllGeneros(){
        return generoRepository.findAll();
    }

}
