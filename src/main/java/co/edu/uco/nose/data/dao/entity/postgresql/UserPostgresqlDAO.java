package co.edu.uco.nose.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.StateEntity;
import co.edu.uco.nose.entity.UserEntity;

public class UserPostgresqlDAO extends SqlConnection implements UserDAO {

	public UserPostgresqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(final UserEntity entity) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

		final var sql = new StringBuilder();
		sql.append("INSERT INTO apinosedb.usuario (");
		sql.append("id, tipo_identificacion_id, numero_identificacion, primer_nombre, segundo_nombre, ");
		sql.append("primer_apellido, segundo_apellido, ciudad_id, correo_electronico, ");
		sql.append("numero_telefono_movil, correo_electronico_confirmado, numero_telefono_movil_confirmado) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, entity.getId());
			preparedStatement.setObject(2, entity.getIdType().getId());
			preparedStatement.setString(3, entity.getIdNumber());
			preparedStatement.setString(4, entity.getFirstName());
			preparedStatement.setString(5, entity.getSecondName());
			preparedStatement.setString(6, entity.getFirstSurname());
			preparedStatement.setString(7, entity.getSecondSurname());
			preparedStatement.setObject(8, entity.getHomeCity().getId());
			preparedStatement.setString(9, entity.getEmail());
			preparedStatement.setString(10, entity.getMobileNumber());
			preparedStatement.setBoolean(11, entity.isEmailConfirmed());
			preparedStatement.setBoolean(12, entity.isMobileNumberConfirmed());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_INSERT_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_USER.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public void update(final UserEntity entity) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

		final var sql = new StringBuilder();
		sql.append("UPDATE apinosedb.usuario SET ");
		sql.append("tipo_identificacion_id = ?, ");
		sql.append("numero_identificacion = ?, ");
		sql.append("primer_nombre = ?, ");
		sql.append("segundo_nombre = ?, ");
		sql.append("primer_apellido = ?, ");
		sql.append("segundo_apellido = ?, ");
		sql.append("ciudad_id = ?, ");
		sql.append("correo_electronico = ?, ");
		sql.append("numero_telefono_movil = ?, ");
		sql.append("correo_electronico_confirmado = ?, ");
		sql.append("numero_telefono_movil_confirmado = ? ");
		sql.append("WHERE id = ?");

		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, entity.getIdType().getId());
			preparedStatement.setString(2, entity.getIdNumber());
			preparedStatement.setString(3, entity.getFirstName());
			preparedStatement.setString(4, entity.getSecondName());
			preparedStatement.setString(5, entity.getFirstSurname());
			preparedStatement.setString(6, entity.getSecondSurname());
			preparedStatement.setObject(7, entity.getHomeCity().getId());
			preparedStatement.setString(8, entity.getEmail());
			preparedStatement.setString(9, entity.getMobileNumber());
			preparedStatement.setBoolean(10, entity.isEmailConfirmed());
			preparedStatement.setBoolean(11, entity.isMobileNumberConfirmed());
			preparedStatement.setObject(12, entity.getId());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UPDATE_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_USER.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public void delete(final UUID id) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

		final var sql = "DELETE FROM apinosedb.usuario WHERE id = ?";

		try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
			preparedStatement.setObject(1, id);
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_DELETE_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_USER.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public List<UserEntity> findByFilter(UserEntity filterEntity) {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

		var parameterList = new ArrayList<Object>();
		var sql = createSentenceFindByFilter(filterEntity, parameterList);

		try (var preparedStatement = this.getConnection().prepareStatement(sql)) {

			for (int i = 0; i < parameterList.size(); i++) {
				preparedStatement.setObject(i + 1, parameterList.get(i));
			}

			return executeSentenceFindByFilter(preparedStatement);
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_USER.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public UserEntity findById(final UUID id) {
		return findByFilter(new UserEntity(id)).stream().findFirst().orElse(new UserEntity());
	}

	@Override
	public List<UserEntity> findAll() {
		return findByFilter(new UserEntity());
	}

	private String createSentenceFindByFilter(final UserEntity filterEntity, final List<Object> parameterList) {
		final var sql = new StringBuilder();

		sql.append("SELECT u.id, ");
		sql.append("ti.id AS id_tipo_identificacion, ");
		sql.append("ti.nombre AS nombre_tipo_identificacion, ");
		sql.append("u.numero_identificacion, ");
		sql.append("u.primer_nombre, ");
		sql.append("u.segundo_nombre, ");
		sql.append("u.primer_apellido, ");
		sql.append("u.segundo_apellido, ");
		sql.append("c.id AS id_ciudad_residencia, ");
		sql.append("c.nombre AS nombre_ciudad_residencia, ");
		sql.append("d.id AS id_departamento_ciudad_residencia, ");
		sql.append("d.nombre AS nombre_departamento_ciudad_residencia, ");
		sql.append("p.id AS id_pais_departamento_ciudad_residencia, ");
		sql.append("p.nombre AS nombre_pais_departamento_ciudad_residencia, ");
		sql.append("u.correo_electronico, ");
		sql.append("u.numero_telefono_movil, ");
		sql.append("u.correo_electronico_confirmado, ");
		sql.append("u.numero_telefono_movil_confirmado ");
		sql.append("FROM apinosedb.usuario AS u ");
		sql.append("INNER JOIN apinosedb.tipo_identificacion AS ti ON u.tipo_identificacion_id = ti.id ");
		sql.append("INNER JOIN apinosedb.ciudad AS c ON u.ciudad_id = c.id ");
		sql.append("INNER JOIN apinosedb.departamento AS d ON c.departamento_id = d.id ");
		sql.append("INNER JOIN apinosedb.pais AS p ON d.pais_id = p.id ");

		createWhereClauseFindByFilter(sql, parameterList, filterEntity);
		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameterList, final UserEntity filterEntity) {
		var filter = ObjectHelper.getDefault(filterEntity, new UserEntity());
		var conditions = new ArrayList<String>();

		addCondition(conditions, parameterList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getId()), "u.id = ?", filter.getId());
		addCondition(conditions, parameterList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getIdType().getId()), "u.tipo_identificacion_id = ?", filter.getIdType().getId());
		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filter.getIdNumber()), "u.numero_identificacion = ?", filter.getIdNumber());
		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filter.getFirstName()), "u.primer_nombre = ?", filter.getFirstName());
		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filter.getSecondName()), "u.segundo_nombre = ?", filter.getSecondName());
		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filter.getFirstSurname()), "u.primer_apellido = ?", filter.getFirstSurname());
		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filter.getSecondSurname()), "u.segundo_apellido = ?", filter.getSecondSurname());
		addCondition(conditions, parameterList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getHomeCity().getId()), "u.ciudad_id = ?", filter.getHomeCity().getId());
		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filter.getEmail()), "u.correo_electronico = ?", filter.getEmail());
		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filter.getMobileNumber()), "u.numero_telefono_movil = ?", filter.getMobileNumber());
		addCondition(conditions, parameterList, !filter.isEmailConfirmedIsDefaultValue(), "u.correo_electronico_confirmado = ?", filter.isEmailConfirmed());
		addCondition(conditions, parameterList, !filter.isMobileNumberConfirmedIsDefualtValue(), "u.numero_telefono_movil_confirmado = ?", filter.isMobileNumberConfirmed());

		if (!conditions.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(String.join(" AND ", conditions));
		}
	}

	private void addCondition(final List<String> conditions, final List<Object> params, final boolean condition, final String clause, final Object value) {
		if (condition) {
			conditions.add(clause);
			params.add(value);
		}
	}

	private List<UserEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
		var listUser = new ArrayList<UserEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				var idType = new IdTypeEntity();
				idType.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id_tipo_identificacion")));
				idType.setName(resultSet.getString("nombre_tipo_identificacion"));

				var country = new CountryEntity();
				country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id_pais_departamento_ciudad_residencia")));
				country.setName(resultSet.getString("nombre_pais_departamento_ciudad_residencia"));

				var state = new StateEntity();
				state.setCountry(country);
				state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id_departamento_ciudad_residencia")));
				state.setName(resultSet.getString("nombre_departamento_ciudad_residencia"));

				var city = new CityEntity();
				city.setState(state);
				city.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id_ciudad_residencia")));
				city.setName(resultSet.getString("nombre_ciudad_residencia"));

				var user = new UserEntity();
				user.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
				user.setIdType(idType);
				user.setIdNumber(resultSet.getString("numero_identificacion"));
				user.setFirstName(resultSet.getString("primer_nombre"));
				user.setSecondName(resultSet.getString("segundo_nombre"));
				user.setFirstSurname(resultSet.getString("primer_apellido"));
				user.setSecondSurname(resultSet.getString("segundo_apellido"));
				user.setHomeCity(city);
				user.setEmail(resultSet.getString("correo_electronico"));
				user.setMobileNumber(resultSet.getString("numero_telefono_movil"));
				user.setEmailConfirmed(resultSet.getBoolean("correo_electronico_confirmado"));
				user.setMobileNumberConfirmed(resultSet.getBoolean("numero_telefono_movil_confirmado"));

				listUser.add(user);
			}
		} catch (SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_MAPPING_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_USER.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return listUser;
	}

}
