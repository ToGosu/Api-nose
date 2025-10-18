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
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.StateEntity;

public final class CityPostgresqlDAO extends SqlConnection implements CityDAO {

    public CityPostgresqlDAO(final Connection connection) {
        super(connection);
    }



    @Override
    public List<CityEntity> findAll() {

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("SELECT id, name, state_id FROM city");

        final List<CityEntity> cities = new ArrayList<>();

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                var city = new CityEntity(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("name"),
                        new StateEntity((UUID) resultSet.getObject("state_id"), null, null)
                );
                cities.add(city);
            }

            return cities;

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.CITY_ERROR_FIND_ALL_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_ALL_SQL_CITY.getContent(),
                    (SQLException) exception
            );
        } catch (final Exception exception) {
            throw new NoseException(
                    MessagesEnum.CITY_ERROR_FIND_ALL_UNEXPECTED.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_ALL_UNEXPECTED_CITY.getContent(),
                    (SQLException) exception
            );
        } catch (final Throwable exception) {
            throw new NoseException(
                    MessagesEnum.CITY_ERROR_FIND_ALL_CRITICAL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_ALL_CRITICAL_CITY.getContent(),
                    (SQLException) exception
            );
        }
    }

    // java
    @Override
    public List<CityEntity> findByFilter(CityEntity filterEntity) {
        if (filterEntity == null) {
            return findAll();
        }

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("SELECT id, name, state_id FROM city");

        final List<String> whereClauses = new ArrayList<>();

        if (filterEntity.getId() != null) {
            whereClauses.add("id = ?");
        }
        if (filterEntity.getName() != null && !filterEntity.getName().trim().isEmpty()) {
            whereClauses.add("name ILIKE ?");
        }
        // Validar la entidad State y su id antes de usarlo
        if (filterEntity.getState() != null && filterEntity.getState().getId() != null) {
            whereClauses.add("state_id = ?");
        }

        if (!whereClauses.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", whereClauses));
        }

        final List<CityEntity> cities = new ArrayList<>();

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {

            int index = 1;
            if (filterEntity.getId() != null) {
                preparedStatement.setObject(index++, filterEntity.getId());
            }
            if (filterEntity.getName() != null && !filterEntity.getName().trim().isEmpty()) {
                preparedStatement.setString(index++, "%" + filterEntity.getName().trim() + "%");
            }
            if (filterEntity.getState() != null && filterEntity.getState().getId() != null) {
                preparedStatement.setObject(index++, filterEntity.getState().getId());
            }

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var city = new CityEntity(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("name"),
                            new StateEntity((UUID) resultSet.getObject("state_id"), null, null)
                    );
                    cities.add(city);
                }
                return cities;
            }

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.CITY_ERROR_FIND_BY_FILTER_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_SQL_CITY.getContent(),
                    exception
            );
        } catch (final Exception exception) {
            throw new NoseException(
                    MessagesEnum.CITY_ERROR_FIND_BY_FILTER_UNEXPECTED.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_UNEXPECTED_CITY.getContent(),
                    (SQLException) exception
            );
        } catch (final Throwable exception) {
            throw new NoseException(
                    MessagesEnum.CITY_ERROR_FIND_BY_FILTER_CRITICAL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_CRITICAL_CITY.getContent(),
                    (SQLException) exception
            );
        }
    }


    @Override
    public CityEntity findById(final UUID id) {

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("SELECT id, name, state_id FROM city WHERE id = ?");

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {

            preparedStatement.setObject(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new CityEntity(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("name"),
                        new StateEntity((UUID) resultSet.getObject("state_id"), null, null)
                );
            }
            return null;

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.CITY_ERROR_FIND_BY_ID_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_SQL_CITY.getContent(),
                    (SQLException) exception
            );
        } catch (final Exception exception) {
            throw new NoseException(
                    MessagesEnum.CITY_ERROR_FIND_BY_ID_UNEXPECTED.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_UNEXPECTED_CITY.getContent(),
                    (SQLException) exception
            );
        } catch (final Throwable exception) {
            throw new NoseException(
                    MessagesEnum.CITY_ERROR_FIND_BY_ID_CRITICAL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_CRITICAL_CITY.getContent(),
                    (SQLException) exception
            );
        }
    }
}


