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
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.*;
import co.edu.uco.nose.entity.CountryEntity;

public final class CountryPostgresqlDAO extends SqlConnection implements CountryDAO{

    public CountryPostgresqlDAO(Connection connection) {
        super(connection);
    }


    @Override
    public List<CountryEntity> findAll() {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final String sql = "SELECT id, name FROM country";
        final List<CountryEntity> countries = new ArrayList<>();

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
             final ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                countries.add(new CountryEntity(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("name")
                ));
            }

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.COUNTRY_ERROR_FIND_ALL_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_ALL_SQL_COUNTRY.getContent(),
                    exception
            );

        } catch (final Exception exception) {
            throw new NoseException(
                    MessagesEnum.COUNTRY_ERROR_FIND_ALL_UNEXPECTED.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_ALL_UNEXPECTED_COUNTRY.getContent(),
                    (SQLException) exception
            );

        } catch (final Throwable exception) {
            throw new NoseException(
                    MessagesEnum.COUNTRY_ERROR_FIND_ALL_CRITICAL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_ALL_CRITICAL_COUNTRY.getContent(),
                    (SQLException) exception
            );
        }

        return countries;
    }

    @Override
    public List<CountryEntity> findByFilter(CountryEntity filterEntity) {
        if (filterEntity == null) {
            return findAll();
        }

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("SELECT id, name FROM country");

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

        final List<CountryEntity> countries = new ArrayList<>();

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
                    countries.add(new CountryEntity(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("name")
                    ));
                }
                return countries;
            }

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.COUNTRY_ERROR_FIND_BY_FILTER_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_SQL_COUNTRY.getContent(),
                    exception
            );

        } catch (final Exception exception) {
            throw new NoseException(
                    MessagesEnum.COUNTRY_ERROR_FIND_BY_FILTER_UNEXPECTED.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_UNEXPECTED_COUNTRY.getContent(),
                    (SQLException) exception
            );

        } catch (final Throwable exception) {
            throw new NoseException(
                    MessagesEnum.COUNTRY_ERROR_FIND_BY_FILTER_CRITICAL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_CRITICAL_COUNTRY.getContent(),
                    (SQLException) exception
            );
        }
    }



    @Override
    public CountryEntity findById(final UUID id) {

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final String sql = "SELECT id, name FROM country WHERE id = ?";

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

            preparedStatement.setObject(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new CountryEntity(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("name")
                );
            }

            return null;

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.COUNTRY_ERROR_FIND_BY_ID_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_SQL_COUNTRY.getContent(),
                    (SQLException) exception
            );

        } catch (final Exception exception) {
            throw new NoseException(
                    MessagesEnum.COUNTRY_ERROR_FIND_BY_ID_UNEXPECTED.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_UNEXPECTED_COUNTRY.getContent(),
                    (SQLException) exception
            );

        } catch (final Throwable exception) {
            throw new NoseException(
                    MessagesEnum.COUNTRY_ERROR_FIND_BY_ID_CRITICAL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_CRITICAL_COUNTRY.getContent(),
                    (SQLException) exception
            );
        }
    }


}


