package co.edu.uco.nose.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.StateEntity;

public final class CityPostgresqlDAO extends SqlConnection implements CityDAO {

    public CityPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<CityEntity> findAll() {
        final var sql = new StringBuilder();
        sql.append("""
            SELECT 
                c.id AS idCiudad,
                c.nombre AS nombreCiudad,
                d.id AS idDepartamento,
                d.nombre AS nombreDepartamento,
                p.id AS idPais,
                p.nombre AS nombrePais
            FROM Ciudad c
            INNER JOIN Departamento d ON c.departamento = d.id
            INNER JOIN Pais p ON d.pais = p.id
        """);

        final List<CityEntity> cities = new ArrayList<>();

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString());
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                var country = new CountryEntity(
                    UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPais")),
                    resultSet.getString("nombrePais")
                );

                var state = new StateEntity(
                    UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamento")),
                    resultSet.getString("nombreDepartamento"),
                    country
                );

                var city = new CityEntity(
                    UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudad")),
                    resultSet.getString("nombreCiudad"),
                    state
                );

                cities.add(city);
            }

        } catch (final SQLException exception) {
            var userMessage = "Error al consultar todas las ciudades.";
            var technicalMessage = "Error SQL en findAll() de CityPostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Error inesperado al consultar las ciudades.";
            var technicalMessage = "Excepción inesperada en findAll() de CityPostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return cities;
    }

    @Override
    public List<CityEntity> findByFilter(final CityEntity filterEntity) {
        final var sql = new StringBuilder();
        sql.append("""
            SELECT 
                c.id AS idCiudad,
                c.nombre AS nombreCiudad,
                d.id AS idDepartamento,
                d.nombre AS nombreDepartamento,
                p.id AS idPais,
                p.nombre AS nombrePais
            FROM Ciudad c
            INNER JOIN Departamento d ON c.departamento = d.id
            INNER JOIN Pais p ON d.pais = p.id
            WHERE 1=1
        """);

        final List<Object> parameters = new ArrayList<>();

        if (filterEntity != null) {
            if (filterEntity.getId() != null) {
                sql.append(" AND c.id = ? ");
                parameters.add(filterEntity.getId());
            }

            if (filterEntity.getName() != null && !filterEntity.getName().isBlank()) {
                sql.append(" AND LOWER(c.nombre) LIKE LOWER(?) ");
                parameters.add("%" + filterEntity.getName() + "%");
            }

            if (filterEntity.getState() != null && filterEntity.getState().getId() != null) {
                sql.append(" AND d.id = ? ");
                parameters.add(filterEntity.getState().getId());
            }
        }

        final List<CityEntity> cities = new ArrayList<>();

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var country = new CountryEntity(
                        UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPais")),
                        resultSet.getString("nombrePais")
                    );

                    var state = new StateEntity(
                        UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamento")),
                        resultSet.getString("nombreDepartamento"),
                        country
                    );

                    var city = new CityEntity(
                        UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudad")),
                        resultSet.getString("nombreCiudad"),
                        state
                    );

                    cities.add(city);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Error al consultar las ciudades con filtro.";
            var technicalMessage = "Error SQL en findByFilter() de CityPostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Error inesperado al consultar las ciudades con filtro.";
            var technicalMessage = "Excepción inesperada en findByFilter() de CityPostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return cities;
    }

    @Override
    public CityEntity findById(final UUID id) {
        final var sql = new StringBuilder();
        sql.append("""
            SELECT 
                c.id AS idCiudad,
                c.nombre AS nombreCiudad,
                d.id AS idDepartamento,
                d.nombre AS nombreDepartamento,
                p.id AS idPais,
                p.nombre AS nombrePais
            FROM Ciudad c
            INNER JOIN Departamento d ON c.departamento = d.id
            INNER JOIN Pais p ON d.pais = p.id
            WHERE c.id = ?
        """);

        CityEntity city = null;

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    var country = new CountryEntity(
                        UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPais")),
                        resultSet.getString("nombrePais")
                    );

                    var state = new StateEntity(
                        UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamento")),
                        resultSet.getString("nombreDepartamento"),
                        country
                    );

                    city = new CityEntity(
                        UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudad")),
                        resultSet.getString("nombreCiudad"),
                        state
                    );
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Error al consultar la ciudad por ID.";
            var technicalMessage = "Error SQL en findById() de CityPostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Error inesperado al consultar la ciudad.";
            var technicalMessage = "Excepción inesperada en findById() de CityPostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return city;
    }
}
