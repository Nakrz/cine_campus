package com.filtro.Pais.adapters.out;

import com.filtro.Pais.domain.Pais;
import com.filtro.Pais.infrastructure.PaisRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaisMySQLRepository implements PaisRepository {

    private final String url;
    private final String user;
    private final String password;

    public PaisMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Pais pais) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO pais (descripcion) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, pais.getDescripcion());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Pais pais) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE pais SET descripcion = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, pais.getDescripcion());
                statement.setInt(2, pais.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Pais> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM pais WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Pais pais = new Pais(
                                resultSet.getInt("id"),
                                resultSet.getString("descripcion")
                        );
                        return Optional.of(pais);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM pais WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pais> findAll() {
        List<Pais> paises = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, descripcion FROM pais";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Pais pais = new Pais(
                            resultSet.getInt("id"),
                            resultSet.getString("descripcion")
                    );
                    paises.add(pais);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paises;
    }
}
