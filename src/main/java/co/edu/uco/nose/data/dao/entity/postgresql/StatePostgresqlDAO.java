package co.edu.uco.nose.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.StateEntity;

public final class StatePostgresqlDAO extends SqlConnection implements StateDAO {
    
    public StatePostgresqlDAO(final Connection connection) {
        super(connection);
    }


    @Override
    public List<StateEntity> findAll() {

        final var states = new ArrayList<StateEntity>();
        final var sql = new StringBuilder();

        sql.append("SELECT d.id AS idDepartamento, d.nombre AS nombreDepartamento, ");
        sql.append("p.id AS idPais, p.nombre AS nombrePais ");
        sql.append("FROM Departamento AS d ");
        sql.append("INNER JOIN Pais AS p ON d.pais = p.id");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString());
             var resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                var country = new CountryEntity();
                country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPais")));
                country.setName(resultSet.getString("nombrePais"));

                var state = new StateEntity();
                state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamento")));
                state.setName(resultSet.getString("nombreDepartamento"));
                state.setCountry(country);

                states.add(state);
            }

        } catch (final SQLException exception) {
            var userMessage = "Error al consultar los departamentos.";
            var technicalMessage = "Error SQL al intentar listar los departamentos.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "No se pudo listar los departamentos.";
            var technicalMessage = "Error inesperado al procesar la consulta de departamentos.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return states;
    }

    @Override
    public List<StateEntity> findByFilter(final StateEntity filterEntity) {

        final var states = new ArrayList<StateEntity>();
        final var sql = new StringBuilder();

        sql.append("SELECT d.id AS idDepartamento, d.nombre AS nombreDepartamento, ");
        sql.append("p.id AS idPais, p.nombre AS nombrePais ");
        sql.append("FROM Departamento AS d ");
        sql.append("INNER JOIN Pais AS p ON d.pais = p.id ");
        sql.append("WHERE 1=1 ");

        final var parameters = new ArrayList<Object>();

        if (filterEntity != null) {
            if (filterEntity.getId() != null) {
                sql.append("AND d.id = ? ");
                parameters.add(filterEntity.getId());
            }
            if (filterEntity.getName() != null && !filterEntity.getName().isBlank()) {
                sql.append("AND LOWER(d.nombre) LIKE LOWER(?) ");
                parameters.add("%" + filterEntity.getName() + "%");
            }
            if (filterEntity.getCountry() != null && filterEntity.getCountry().getId() != null) {
                sql.append("AND p.id = ? ");
                parameters.add(filterEntity.getCountry().getId());
            }
        }

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var country = new CountryEntity();
                    country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPais")));
                    country.setName(resultSet.getString("nombrePais"));

                    var state = new StateEntity();
                    state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamento")));
                    state.setName(resultSet.getString("nombreDepartamento"));
                    state.setCountry(country);

                    states.add(state);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Error al consultar los departamentos filtrados.";
            var technicalMessage = "Error SQL al ejecutar la consulta dinámica de departamentos.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "No se pudo ejecutar el filtro de departamentos.";
            var technicalMessage = "Error inesperado al procesar los parámetros de búsqueda de departamentos.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return states;
    }

    @Override
    public StateEntity findById(final UUID id) {

        var state = new StateEntity();
        final var sql = new StringBuilder();

        sql.append("SELECT d.id AS idDepartamento, d.nombre AS nombreDepartamento, ");
        sql.append("p.id AS idPais, p.nombre AS nombrePais ");
        sql.append("FROM Departamento AS d ");
        sql.append("INNER JOIN Pais AS p ON d.pais = p.id ");
        sql.append("WHERE d.id = ?");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    var country = new CountryEntity();
                    country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPais")));
                    country.setName(resultSet.getString("nombrePais"));

                    state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamento")));
                    state.setName(resultSet.getString("nombreDepartamento"));
                    state.setCountry(country);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Error al consultar el departamento.";
            var technicalMessage = "Error SQL al buscar un departamento por ID.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "No se pudo consultar el departamento.";
            var technicalMessage = "Error inesperado al intentar consultar el departamento.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return state;
    }


}
