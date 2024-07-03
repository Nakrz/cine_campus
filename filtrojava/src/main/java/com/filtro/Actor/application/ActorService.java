package com.filtro.Actor.application;

import java.util.List;
import java.util.Optional;

import com.filtro.Actor.infrastructure.ActorRepository;
import com.filtro.Actor.domain.Actor;

public class ActorService {

    private final ActorRepository actorRepository;


    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
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

}
