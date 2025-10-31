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
			country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPais")));
			country.setName(resultSet.getString("nombrePais"));

			var state = new StateEntity();
			state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamento")));
			state.setName(resultSet.getString("nombreDepartamento"));
			state.setCountry(country);

			entity.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudad")));
			entity.setName(resultSet.getString("nombreCiudad"));
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
		sql.append("SELECT c.id AS idCiudad, c.nombre AS nombreCiudad, ");
		sql.append("d.id AS idDepartamento, d.nombre AS nombreDepartamento, ");
		sql.append("p.id AS idPais, p.nombre AS nombrePais ");
		sql.append("FROM apinosedb.Ciudad c ");
		sql.append("INNER JOIN apinosedb.Departamento d ON c.departamento = d.id ");
		sql.append("INNER JOIN apinosedb.Pais p ON d.pais = p.id ");

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

		sql.append("SELECT c.id AS idCiudad, c.nombre AS nombreCiudad, ");
		sql.append("d.id AS idDepartamento, d.nombre AS nombreDepartamento, ");
		sql.append("p.id AS idPais, p.nombre AS nombrePais ");
		sql.append("FROM apinosedb.Ciudad c ");
		sql.append("INNER JOIN apinosedb.Departamento d ON c.departamento = d.id ");
		sql.append("INNER JOIN apinosedb.Pais p ON d.pais = p.id ");
		sql.append("WHERE 1=1 ");

		final UUID defaultUuid = UUIDHelper.getUUIDHelper().getDefault();
		if (filterEntity.getId() != null && !defaultUuid.equals(filterEntity.getId())) {
			sql.append("AND c.id = ? ");
			parameters.add(filterEntity.getId());
		}
		if (filterEntity.getName() != null && !filterEntity.getName().trim().isEmpty()) {
			sql.append("AND c.nombre LIKE ? ");
			parameters.add("%" + filterEntity.getName().trim() + "%");
		}
		if (filterEntity.getState() != null) {
			if (filterEntity.getState().getId() != null && !defaultUuid.equals(filterEntity.getState().getId())) {
				sql.append("AND d.id = ? ");
				parameters.add(filterEntity.getState().getId());
			}
			if (filterEntity.getState().getCountry() != null && filterEntity.getState().getCountry().getId() != null && !defaultUuid.equals(filterEntity.getState().getCountry().getId())) {
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
		sql.append("SELECT c.id AS idCiudad, c.nombre AS nombreCiudad, ");
		sql.append("d.id AS idDepartamento, d.nombre AS nombreDepartamento, ");
		sql.append("p.id AS idPais, p.nombre AS nombrePais ");
		sql.append("FROM apinosedb.Ciudad c ");
		sql.append("INNER JOIN apinosedb.Departamento d ON c.departamento = d.id ");
		sql.append("INNER JOIN apinosedb.Pais p ON d.pais = p.id ");
		sql.append("WHERE c.id = ? ");

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
		} catch (final NoseException exception) {
			throw exception;
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_CITY.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_CITY.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return city;
	}
}


