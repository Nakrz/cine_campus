package com.filtro.Actor.infrastructure;

import java.util.List;
import java.util.Optional;

import com.filtro.Actor.domain.Actor;

public interface ActorRepository {

    void save(Actor actor);
    void update(Actor actor);
    Optional<Actor> findById(int id);
    void delete(int id);
    List<Actor> findAll();
    
}
