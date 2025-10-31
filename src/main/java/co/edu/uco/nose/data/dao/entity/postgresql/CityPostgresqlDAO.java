package co.edu.uco.nose.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.StateEntity;

public final class CityPostgresqlDAO extends SqlConnection implements CityDAO {

    public CityPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    private void mapResultSetToCity(final java.sql.ResultSet resultSet, final CityEntity entity) {
        try {
            var country = new CountryEntity();
            country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id_pais")));
            country.setName(resultSet.getString("nombre_pais"));

            var state = new StateEntity();
            state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id_departamento")));
            state.setName(resultSet.getString("nombre_departamento"));
            state.setCountry(country);

            entity.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id_ciudad")));
            entity.setName(resultSet.getString("nombre_ciudad"));
            entity.setState(state);

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_MAPPING_CITY.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_CITY.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<CityEntity> findAll() {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        final List<CityEntity> cities = new ArrayList<>();
        final var sql = new StringBuilder();

        sql.append("SELECT c.id AS id_ciudad, c.nombre AS nombre_ciudad, ");
        sql.append("d.id AS id_departamento, d.nombre AS nombre_departamento, ");
        sql.append("p.id AS id_pais, p.nombre AS nombre_pais ");
        sql.append("FROM apinosedb.ciudad c ");
        sql.append("INNER JOIN apinosedb.departamento d ON c.departamento_id = d.id ");
        sql.append("INNER JOIN apinosedb.pais p ON d.pais_id = p.id;");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString());
             var resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                var city = new CityEntity();
                mapResultSetToCity(resultSet, city);
                cities.add(city);
            }

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_ALL_CITY.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_ALL_CITY.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_CITY.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_CITY.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
        return cities;
    }

    @Override
    public List<CityEntity> findByFilter(final CityEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        final List<CityEntity> cities = new ArrayList<>();
        final var sql = new StringBuilder();
        final var parameters = new ArrayList<Object>();

        sql.append("SELECT c.id AS id_ciudad, c.nombre AS nombre_ciudad, ");
        sql.append("d.id AS id_departamento, d.nombre AS nombre_departamento, ");
        sql.append("p.id AS id_pais, p.nombre AS nombre_pais ");
        sql.append("FROM apinosedb.ciudad c ");
        sql.append("INNER JOIN apinosedb.departamento d ON c.departamento_id = d.id ");
        sql.append("INNER JOIN apinosedb.pais p ON d.pais_id = p.id ");
        sql.append("WHERE 1=1 ");

        final UUID defaultUuid = UUIDHelper.getUUIDHelper().getDefault();

        if (filterEntity.getId() != null && !defaultUuid.equals(filterEntity.getId())) {
            sql.append("AND c.id = ? ");
            parameters.add(filterEntity.getId());
        }

        if (filterEntity.getName() != null && !filterEntity.getName().trim().isEmpty()) {
            sql.append("AND LOWER(c.nombre) LIKE LOWER(?) ");
            parameters.add("%" + filterEntity.getName().trim() + "%");
        }

        if (filterEntity.getState() != null) {
            if (filterEntity.getState().getId() != null && !defaultUuid.equals(filterEntity.getState().getId())) {
                sql.append("AND d.id = ? ");
                parameters.add(filterEntity.getState().getId());
            }

            if (filterEntity.getState().getCountry() != null && filterEntity.getState().getCountry().getId() != null
                    && !defaultUuid.equals(filterEntity.getState().getCountry().getId())) {
                sql.append("AND p.id = ? ");
                parameters.add(filterEntity.getState().getCountry().getId());
            }
        }

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var city = new CityEntity();
                    mapResultSetToCity(resultSet, city);
                    cities.add(city);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_CITY.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_CITY.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_CITY.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_CITY.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
        return cities;
    }

    @Override
    public CityEntity findById(final UUID id) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        CityEntity city = null;
        final var sql = new StringBuilder();

        sql.append("SELECT c.id AS id_ciudad, c.nombre AS nombre_ciudad, ");
        sql.append("d.id AS id_departamento, d.nombre AS nombre_departamento, ");
        sql.append("p.id AS id_pais, p.nombre AS nombre_pais ");
        sql.append("FROM apinosedb.ciudad c ");
        sql.append("INNER JOIN apinosedb.departamento d ON c.departamento_id = d.id ");
        sql.append("INNER JOIN apinosedb.pais p ON d.pais_id = p.id ");
        sql.append("WHERE c.id = ?;");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    city = new CityEntity();
                    mapResultSetToCity(resultSet, city);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_BY_ID_CITY.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_ID_CITY.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_CITY.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_CITY.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
        return city;
    }
}
