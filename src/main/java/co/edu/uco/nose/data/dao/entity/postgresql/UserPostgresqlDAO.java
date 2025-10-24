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
		sql.append(" INSERT INTO Usuario (id, tipoIdentificacion, numeroIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido,"
				+ " ciudadResidencia, correoElectronico, numeroTelefonoMovil, correoElectronicoConfirmado, numeroTelefonoMovilConfirmado) ");

		sql.append("SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ");

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
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_USER
					.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {

			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_INSERT_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_USER
					.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public void update(final UserEntity entity) {

		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

		final var sql = new StringBuilder();

		sql.append("UPDATE Usuario ");
		sql.append("SET tipoIdentificacion = ?, ");
		sql.append("    numeroIdentificacion = ?, ");
		sql.append("    primerNombre = ?, ");
		sql.append("    segundoNombre = ?, ");
		sql.append("    primerApellido = ?, ");
		sql.append("    segundoApellido = ?, ");
		sql.append("    ciudadResidencia = ?, ");
		sql.append("    correoElectronico = ?, ");
		sql.append("    numeroTelefonoMovil = ?, ");
		sql.append("    correoElectronicoConfirmado = ?, ");
		sql.append("    numeroTelefonoMovilConfirmado = ? ");
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
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_USER
					.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_USER
					.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public void delete(final UUID id) {

		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

		final var sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM   Usuario ");
		sql.append("WHERE  id = ?");

		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, id);
			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_DELETE_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_USER
					.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_DELETE_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_USER
					.getContent() + exception.getMessage();
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
		} catch (final NoseException exception) {
			throw exception;

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_USER
					.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}


	@Override
	public UserEntity findById(final UUID id) {
		return findByFilter(new UserEntity(id))
				.stream()
				.findFirst()
				.orElse(new UserEntity());
	}

	@Override
	public List<UserEntity> findAll() {
		return findByFilter(new UserEntity());
	}

	private String createSentenceFindByFilter(final UserEntity filterEntity, final List<Object> parameterList) {
		final var sql = new StringBuilder();

		sql.append("SELECT     u.id, ");
		sql.append("           ti.id AS idTipoIdentificacion, ");
		sql.append("           ti.nombre AS nombreTipoIdentificacion, ");
		sql.append("           u.numeroIdentificacion, ");
		sql.append("           u.primerNombre, ");
		sql.append("           u.segundoNombre, ");
		sql.append("           u.primerApellido, ");
		sql.append("           u.segundoApellido, ");
		sql.append("           c.id AS idCiudadResidencia, ");
		sql.append("           c.nombre AS nombreCiudadResidencia, ");
		sql.append("           d.id AS idDepartamentoCiudadResidencia, ");
		sql.append("           d.nombre AS nombreDepartamentoCiudadResidencia, ");
		sql.append("           p.id AS idPaisDepartamentoCiudadResidencia, ");
		sql.append("           p.nombre AS nombrePaisDepartamentoCiudadResidencia, ");
		sql.append("           u.correoElectronico, ");
		sql.append("           u.numeroTelefonoMovil, ");
		sql.append("           u.correoElectronicoConfirmado, ");
		sql.append("           u.numeroTelefonoMovilConfirmado ");
		sql.append("FROM       Usuario AS u ");
		sql.append("INNER JOIN TipoIdentificacion AS ti ");
		sql.append("ON         u.tipoIdentificacion = ti.id ");
		sql.append("INNER JOIN Ciudad AS c ");
		sql.append("ON         u.ciudadResidencia = c.id ");
		sql.append("INNER JOIN Departamento AS d ");
		sql.append("ON         c.departamento = d.id ");
		sql.append("INNER JOIN Pais AS p ");
		sql.append("ON         d.pais = p.id ");

		createWhereClauseFindByFilter(sql, parameterList, filterEntity);

		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameterList,
											   final UserEntity filterEntity) {
		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new UserEntity());
		final var conditions = new ArrayList<String>();

		addCondition(conditions, parameterList,
				!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getId()),
				"u.id = ?", filterEntityValidated.getId());

		addCondition(conditions, parameterList,
				!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getIdType().getId()),
				" u.tipoIdentificacion = ?", filterEntityValidated.getIdType().getId());

		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getIdNumber()),
				"u.numeroIdentificacion = ?", filterEntityValidated.getIdNumber());

		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getFirstName()),
				"u.primerNombre = ?", filterEntityValidated.getFirstName());

		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getSecondName()),
				"u.segundoNombre = ?", filterEntityValidated.getSecondName());

		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getFirstSurname()),
				"u.primerApellido = ?", filterEntityValidated.getFirstSurname());

		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getSecondSurname()),
				"u.segundoApellido = ?", filterEntityValidated.getSecondSurname());

		addCondition(conditions, parameterList,
				!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getHomeCity().getId()),
				"u.ciudadResidencia = ?", filterEntityValidated.getHomeCity().getId());

		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getEmail()),
				"u.correoElectronico = ?", filterEntityValidated.getEmail());

		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getMobileNumber()),
				"u.numeroTelefonoMovil = ?", filterEntityValidated.getMobileNumber());

		addCondition(conditions, parameterList, !filterEntityValidated.isEmailConfirmedIsDefaultValue(),
				"u.correoElectronicoConfirmado = ?", filterEntityValidated.isEmailConfirmed());

		addCondition(conditions, parameterList, !filterEntityValidated.isMobileNumberConfirmedIsDefualtValue(),
				"u.numeroTelefonoMovilConfirmado = ?", filterEntityValidated.isMobileNumberConfirmed());

		if (!conditions.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(String.join(" AND ", conditions));
		}
	}

	private void addCondition(final List<String> conditions, final List<Object> parameterList, final boolean codition,
							  final String clause, final Object value) {
		if (codition) {
			conditions.add(clause);
			parameterList.add(value);
		}
	}

	private List<UserEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
		var listUser = new ArrayList<UserEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {

				var idType = new IdTypeEntity();
				idType.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTipoIdentificacion")));
				idType.setName(resultSet.getString("nombreTipoIdentificacion"));

				var country = new CountryEntity();
				country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPaisDepartamentoCiudadResidencia")));
				country.setName(resultSet.getString("nombrePaisDepartamentoCiudadResidencia"));

				var state = new StateEntity();
				state.setCountry(country);
				state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamentoCiudadResidencia")));
				state.setName(resultSet.getString("nombreDepartamentoCiudadResidencia"));

				var city = new CityEntity();
				city.setState(state);
				city.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudadResidencia")));
				city.setName(resultSet.getString("nombreCiudadResidencia"));

				var user = new UserEntity();

				user.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
				user.setIdType(idType);
				user.setIdNumber(resultSet.getString("numeroIdentificacion"));
				user.setFirstName(resultSet.getString("primerNombre"));
				user.setSecondName(resultSet.getString("segundoNombre"));
				user.setFirstSurname(resultSet.getString("primerApellido"));
				user.setSecondSurname(resultSet.getString("segundoApellido"));
				user.setHomeCity(city);
				user.setEmail(resultSet.getString("correoElectronico"));
				user.setMobileNumber(resultSet.getString("numeroTelefonoMovil"));
				user.setEmailConfirmed(resultSet.getBoolean("correoElectronicoConfirmado"));
				user.setMobileNumberConfirmed(resultSet.getBoolean("numeroTelefonoMovilConfirmado"));

				listUser.add(user);
			}

		} catch (final SQLException exception) {

			var userMessage = MessagesEnum.USER_ERROR_SQL_MAPPING_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_USER
					.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {

			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_MAPPING_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_USER
					.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}

		return listUser;

	}

}