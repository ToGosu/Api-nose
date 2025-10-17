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
import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.IdTypeEntity;

public final class IdTypePostgresqlDAO extends SqlConnection implements IdTypeDAO {

    public IdTypePostgresqlDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List findAll() {
        final var sql = new StringBuilder();
        sql.append("SELECT id, nombre, descripcion ");
        sql.append("FROM TipoIdentificacion");

        final List<IdTypeEntity> idTypes = new ArrayList<>();

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString());
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                var idType = new IdTypeEntity();
                idType.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
                idType.setName(resultSet.getString("nombre"));
                idType.setDescription(resultSet.getString("descripcion"));
                idTypes.add(idType);
            }

        } catch (final SQLException exception) {
            var userMessage = "Error al intentar consultar los tipos de identificación.";
            var technicalMessage = "Error al ejecutar la sentencia SQL en findAll() de IdTypePostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un error inesperado al consultar los tipos de identificación.";
            var technicalMessage = "Excepción inesperada en findAll() de IdTypePostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return idTypes;
    }

    @Override
    public List<IdTypeEntity> findByFilter(final IdTypeEntity filterEntity) {
        final var sql = new StringBuilder();
        sql.append("SELECT id, nombre, descripcion ");
        sql.append("FROM TipoIdentificacion ");
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
        if (filterEntity.getDescription() != null && !filterEntity.getDescription().isBlank()) {
            sql.append("AND LOWER(descripcion) LIKE LOWER(?) ");
            parameters.add("%" + filterEntity.getDescription() + "%");
        }

        final List<IdTypeEntity> idTypes = new ArrayList<>();

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var idType = new IdTypeEntity();
                    idType.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
                    idType.setName(resultSet.getString("nombre"));
                    idType.setDescription(resultSet.getString("descripcion"));
                    idTypes.add(idType);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Error al intentar consultar tipos de identificación con filtro.";
            var technicalMessage = "Error al ejecutar la sentencia SQL en findByFilter() de IdTypePostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un error inesperado al consultar tipos de identificación con filtro.";
            var technicalMessage = "Excepción inesperada en findByFilter() de IdTypePostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return idTypes;
    }

    @Override
    public IdTypeEntity findById(final UUID id) {
        final var sql = new StringBuilder();
        sql.append("SELECT id, nombre, descripcion ");
        sql.append("FROM TipoIdentificacion ");
        sql.append("WHERE id = ?");

        IdTypeEntity idType = null;

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    idType = new IdTypeEntity();
                    idType.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
                    idType.setName(resultSet.getString("nombre"));
                    idType.setDescription(resultSet.getString("descripcion"));
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Error al intentar consultar el tipo de identificación.";
            var technicalMessage = "Error al ejecutar la sentencia SQL en findById() de IdTypePostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un error inesperado al consultar el tipo de identificación.";
            var technicalMessage = "Excepción inesperada en findById() de IdTypePostgresqlDAO.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return idType;
    }
}
