package co.edu.uco.nose.data.dao.factory.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.UserEntity;

public final class UserPostgresqlDAO implements UserDAO {

    private final Connection connection;

    public UserPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    private Connection getConnection() {
        return connection;
    }

    @Override
    public void create(final UserEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(connection);

        final var sql = new StringBuilder();
        sql.append("INSERT INTO users (id, id_type, phone_number, first_name, second_name, first_last_name, second_last_name, residence_city, email, email_confirmed, mobile_number_confirmed) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        try (var preparedStatement = connection.prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getIdType().getId());
            preparedStatement.setString(3, entity.getPhoneNumber());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getSecondName());
            preparedStatement.setString(6, entity.getFirstLastName());
            preparedStatement.setString(7, entity.getSecondLastName());
            preparedStatement.setObject(8, entity.getResidenceCity().getId());
            preparedStatement.setString(9, entity.getEmail());
            preparedStatement.setBoolean(10, entity.isEmailConfirmed());
            preparedStatement.setBoolean(11, entity.isMobileNumberConfirmed());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw NoseException.create(exception,
                    "No se pudo crear el usuario.",
                    "Error SQL al intentar insertar un usuario en la base de datos.");
        } catch (Exception exception) {
            throw NoseException.create(exception,
                    "No se pudo crear el usuario.",
                    "Error inesperado al intentar crear el usuario.");
        }
    }

    @Override
    public UserEntity findById(final UUID id) {
        final String sql = "SELECT id, id_type, phone_number, first_name, second_name, first_last_name, second_last_name, residence_city, email, email_confirmed, mobile_number_confirmed FROM users WHERE id = ?";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                
            	if (resultSet.next()) {
            		CityEntity residenceCity = new CityEntity((UUID) resultSet.getObject("residence_city"));
            		IdTypeEntity idType = new IdTypeEntity((UUID) resultSet.getObject("id_type"));
                    return new UserEntity(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("second_name"),
                            resultSet.getString("first_last_name"),
                            resultSet.getString("second_last_name"),
                            resultSet.getString("identificaction"),
                            residenceCity,
                            idType,
                            resultSet.getString("email"),
                            resultSet.getString("phone_number"),
                            resultSet.getBoolean("email_confirmed"),
                            resultSet.getBoolean("mobile_number_confirmed")
                    );
                }
                return null;
            }
        } catch (SQLException exception) {
            throw NoseException.create(exception,
                    "Error al buscar el usuario.",
                    "Error SQL en findById.");
        }
    }

    @Override
    public void update(final UserEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(connection);
        final var sql = new StringBuilder();
        sql.append("UPDATE users SET id_type = ?, phone_number = ?, first_name = ?, second_name = ?, first_last_name = ?, second_last_name = ?, residence_city = ?, email = ?, email_confirmed = ?, mobile_number_confirmed = ? WHERE id = ?");

        try (var preparedStatement = connection.prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, entity.getIdType().getId());
            preparedStatement.setString(2, entity.getPhoneNumber());
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getSecondName());
            preparedStatement.setString(5, entity.getFirstLastName());
            preparedStatement.setString(6, entity.getSecondLastName());
            preparedStatement.setObject(7, entity.getResidenceCity().getId());
            preparedStatement.setString(8, entity.getEmail());
            preparedStatement.setBoolean(9, entity.isEmailConfirmed());
            preparedStatement.setBoolean(10, entity.isMobileNumberConfirmed());
            preparedStatement.setObject(11, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw NoseException.create(exception,
                    "No se pudo actualizar el usuario.",
                    "Error SQL al intentar actualizar el usuario.");
        }
    }

    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(connection);
        final String sql = "DELETE FROM users WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw NoseException.create(exception,
                    "No se pudo eliminar el usuario.",
                    "Error SQL al intentar eliminar el usuario.");
        }
    }

    @Override
    public List<UserEntity> findAll() {
        // Implementación pendiente
        return List.of();
    }

    @Override
    public List<UserEntity> findByFilter(final UserEntity filterEntity) {
        // Implementación pendiente
        return List.of();
    }
}
