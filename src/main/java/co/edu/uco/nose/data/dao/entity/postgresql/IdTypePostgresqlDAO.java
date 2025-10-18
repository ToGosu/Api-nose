package co.edu.uco.nose.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.IdTypeEntity;

public final class IdTypePostgresqlDAO extends SqlConnection implements IdTypeDAO {

    public IdTypePostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<IdTypeEntity> findAll() {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final String sql = "SELECT id, name, abbreviation FROM idtype";

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            final List<IdTypeEntity> idTypes = new ArrayList<>();

            while (resultSet.next()) {
                final IdTypeEntity entity = new IdTypeEntity(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("name"));
                idTypes.add(entity);
            }

            return idTypes;

        } catch (final SQLException exception) {
            throw NoseException.create(
                    exception,
                    MessagesEnum.ID_TYPE_ERROR_FIND_BY_ID_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_SQL_ID_TYPE.getContent()
            );

        } catch (final Exception exception) {
            throw NoseException.create(
                    exception,
                    MessagesEnum.ID_TYPE_ERROR_FIND_BY_ID_UNEXPECTED.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_UNEXPECTED_ID_TYPE.getContent()
            );

        } catch (final Throwable exception) {
            throw NoseException.create(
                    exception,
                    MessagesEnum.ID_TYPE_ERROR_FIND_BY_ID_CRITICAL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_CRITICAL_ID_TYPE.getContent()
            );
        }
    }

    @Override
    public List<IdTypeEntity> findByFilter(IdTypeEntity filterEntity) {
        if (filterEntity == null) {
            return findAll();
        }

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("SELECT id, name, abbreviation FROM idtype");

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

        final List<IdTypeEntity> idTypes = new ArrayList<>();

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {

            int index = 1;
            if (filterEntity.getId() != null) {
                preparedStatement.setObject(index++, filterEntity.getId());
            }
            if (filterEntity.getName() != null && !filterEntity.getName().trim().isEmpty()) {
                preparedStatement.setString(index++, "%" + filterEntity.getName().trim() + "%");
            }

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    final IdTypeEntity entity = new IdTypeEntity(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("name"));
                    idTypes.add(entity);
                }
                return idTypes;
            }

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.ID_TYPE_ERROR_FIND_BY_FILTER_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_SQL_ID_TYPE.getContent(),
                    exception
            );

        } catch (final Exception exception) {
            throw new NoseException(
                    MessagesEnum.ID_TYPE_ERROR_FIND_BY_FILTER_UNEXPECTED.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_UNEXPECTED_ID_TYPE.getContent(),
                    (SQLException) exception
            );

        } catch (final Throwable exception) {
            throw new NoseException(
                    MessagesEnum.ID_TYPE_ERROR_FIND_BY_FILTER_CRITICAL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_CRITICAL_ID_TYPE.getContent(),
                    (SQLException) exception
            );
        }
    }


    @Override
    public IdTypeEntity findById(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final String sql = "SELECT id, name, abbreviation FROM idtype WHERE id = ?";

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new IdTypeEntity(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("name"));
            }

            return null;

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.ID_TYPE_ERROR_FIND_BY_FILTER_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_SQL_ID_TYPE.getContent(),
                    (SQLException) exception
            );

        } catch (final Exception exception) {
            throw new NoseException(
                    MessagesEnum.ID_TYPE_ERROR_FIND_BY_FILTER_UNEXPECTED.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_UNEXPECTED_ID_TYPE.getContent(),
                    (SQLException) exception
            );

        } catch (final Throwable exception) {
            throw new NoseException(
                    MessagesEnum.ID_TYPE_ERROR_FIND_BY_FILTER_CRITICAL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_CRITICAL_ID_TYPE.getContent(),
                    (SQLException) exception
            );
        }
    }
}
