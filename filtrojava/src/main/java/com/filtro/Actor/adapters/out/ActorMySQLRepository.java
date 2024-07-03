package com.filtro.Actor.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import com.filtro.Actor.infrastructure.ActorRepository;
import com.filtro.Actor.domain.Actor;

public class ActorMySQLRepository implements ActorRepository{

    private final String url;
    private final String user;
    private final String password;


    public ActorMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Actor actor){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO actor (id, nombre, idnacionalidad, edad, idgenero) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, actor.getId());
                statement.setString(2, actor.getNombre());
                statement.setInt(3, actor.getIdNacionalidad());
                statement.setInt(4, actor.getEdad());
                statement.setInt(5, actor.getIdGenero());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
    
    @Override
    public void update(Actor actor){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE actor SET id = ?, nombre = ?, idnacionalidad = ?, edad = ?, idgenero   WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, actor.getId());
                statement.setString(2, actor.getNombre());
                statement.setInt(3, actor.getIdNacionalidad());
                statement.setInt(4, actor.getEdad());
                statement.setInt(5, actor.getIdGenero());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();

        }
    }


    @Override
    public Optional<Actor> findById(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM actor WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()) {
                        Actor actor = new Actor(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getInt("idacionalidad"),
                            resultSet.getInt("edad"),
                            resultSet.getInt("idgenero")
                        );
                        return Optional.of(actor);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "DELETE id FROM actor";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Actor> findAll() {
        List<Actor> actores = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, nombre, idnacionalidad, edad, idgenero FROM actor";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Actor actor = new Actor(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("idnacionalidad"),
                        resultSet.getInt("edad"),
                        resultSet.getInt("idgenero")
                    );
                    actores.add(actor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actores;
    }
}

