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
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.CountryEntity;

public final class CountryPostgresqlDAO extends SqlConnection implements CountryDAO {

    public CountryPostgresqlDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<CountryEntity> findAll() {
        final var sql = new StringBuilder();
        sql.append("SELECT id, nombre ");
        sql.append("FROM Pais");

        final List<CountryEntity> countries = new ArrayList<>();

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString());
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                var country = new CountryEntity();
                country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
                country.setName(resultSet.getString("nombre"));
                countries.add(country);
            }

        } catch (final SQLException exception) {
            var userMessage = "Error al intentar consultar los países.";
            var technicalMessage = "Error al ejecutar la sentencia SQL en findAll() de CountryPostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un error inesperado al consultar los países.";
            var technicalMessage = "Excepción inesperada en findAll() de CountryPostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return countries;
    }

    @Override
    public List<CountryEntity> findByFilter(CountryEntity filterEntity) {
        final var sql = new StringBuilder();
        sql.append("SELECT id, nombre ");
        sql.append("FROM Pais ");
        sql.append("WHERE 1=1 ");

        final List<Object> parameters = new ArrayList<>();

        if (filterEntity.getId() != null) {
            sql.append("AND id = ? ");
            parameters.add(filterEntity.getId());
        }
        if (filterEntity.getName() != null && !filterEntity.getName().isBlank()) {
            sql.append("AND LOWER(nombre) LIKE LOWER(?) ");
            parameters.add("%" + filterEntity.getName() + "%");
        }

        final List<CountryEntity> countries = new ArrayList<>();

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var country = new CountryEntity();
                    country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
                    country.setName(resultSet.getString("nombre"));
                    countries.add(country);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Error al intentar consultar países con filtro.";
            var technicalMessage = "Error al ejecutar la sentencia SQL en findByFilter() de CountryPostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un error inesperado al consultar países con filtro.";
            var technicalMessage = "Excepción inesperada en findByFilter() de CountryPostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return countries;
    }

    @Override
    public CountryEntity findById(UUID id) {
        final var sql = new StringBuilder();
        sql.append("SELECT id, nombre ");
        sql.append("FROM Pais ");
        sql.append("WHERE id = ?");

        CountryEntity country = null;

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    country = new CountryEntity();
                    country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
                    country.setName(resultSet.getString("nombre"));
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Error al intentar consultar el país.";
            var technicalMessage = "Error al ejecutar la sentencia SQL en findById() de CountryPostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un error inesperado al consultar el país.";
            var technicalMessage = "Excepción inesperada en findById() de CountryPostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return country;
    }
}
