package co.edu.uco.nose.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.*;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.StateEntity;

public final class StatePostgresqlDAO extends SqlConnection implements StateDAO {

    public StatePostgresqlDAO(Connection connection2) {
        super(connection2);
    }

    @Override
    public List<StateEntity> findAll() {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, name FROM state");

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString());
             final ResultSet resultSet = preparedStatement.executeQuery()) {

            final List<StateEntity> results = new ArrayList<>();

            while (resultSet.next()) {
                var state = new StateEntity(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("name"),
                        new CountryEntity()
                );
                results.add(state);
            }

            return results;

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.STATE_ERROR_FIND_ALL_SQL.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_FIND_ALL_SQL_STATE.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } catch (final Exception exception) {
            var userMessage = MessagesEnum.STATE_ERROR_FIND_ALL_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_FIND_ALL_UNEXPECTED_STATE.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } catch (final Throwable exception) {
            var userMessage = MessagesEnum.STATE_ERROR_FIND_ALL_CRITICAL.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_FIND_ALL_CRITICAL_STATE.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<StateEntity> findByFilter(StateEntity filterEntity) {
        if (filterEntity == null) {
            return findAll();
        }

        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, name FROM state");

        final List<String> whereClauses = new ArrayList<>();
        if (filterEntity.getId() != null) {
            whereClauses.add("id = ?");
        }
        if (filterEntity.getName() != null && !filterEntity.getName().trim().isEmpty()) {
            whereClauses.add("name ILIKE ?");
        }

        if (!whereClauses.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", whereClauses));
        }

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {

            int index = 1;
            if (filterEntity.getId() != null) {
                preparedStatement.setObject(index++, filterEntity.getId());
            }
            if (filterEntity.getName() != null && !filterEntity.getName().trim().isEmpty()) {
                preparedStatement.setString(index++, "%" + filterEntity.getName().trim() + "%");
            }

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                final List<StateEntity> results = new ArrayList<>();
                while (resultSet.next()) {
                    results.add(new StateEntity(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("name"),
                            new CountryEntity()
                    ));
                }
                return results;
            }

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.STATE_ERROR_FIND_BY_FILTER_SQL.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_SQL_STATE.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } catch (final Exception exception) {
            var userMessage = MessagesEnum.STATE_ERROR_FIND_BY_FILTER_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_UNEXPECTED_STATE.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } catch (final Throwable exception) {
            var userMessage = MessagesEnum.STATE_ERROR_FIND_BY_FILTER_CRITICAL.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_CRITICAL_STATE.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }



    @Override
	public StateEntity findById(UUID id) {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, name FROM state WHERE id = ?");

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);

            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new StateEntity(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("name"),
                        new CountryEntity()
                		);
            }

            return null;

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.STATE_ERROR_FIND_BY_ID_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_SQL_STATE.getContent(),
                    exception
            );


        } catch (final Exception exception) {
            throw new NoseException(
                    MessagesEnum.STATE_ERROR_FIND_BY_ID_UNEXPECTED.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_UNEXPECTED_STATE.getContent(),
                    (SQLException) exception

            );


        } catch (final Throwable exception) {
            throw new NoseException(
                    MessagesEnum.STATE_ERROR_FIND_BY_ID_CRITICAL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_CRITICAL_STATE.getContent(),
                    (SQLException) exception
        );
        }
    }
}

