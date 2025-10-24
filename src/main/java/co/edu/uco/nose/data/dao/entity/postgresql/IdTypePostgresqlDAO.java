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
import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.entity.IdTypeEntity;

public final class IdTypePostgresqlDAO extends SqlConnection implements IdTypeDAO {

	public IdTypePostgresqlDAO(final Connection connection) {
		super(connection);
	}

	private void mapResultSetToIdType(final java.sql.ResultSet resultSet, final IdTypeEntity entity) {
		try {
			entity.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
			entity.setName(resultSet.getString("nombre"));

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_MAPPING_IDTYPE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_IDTYPE.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public List<IdTypeEntity> findAll() {

		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

		final List<IdTypeEntity> idTypes = new ArrayList<>();
		final String sql = "SELECT id, nombre FROM TipoIdentificacion";

		try (var preparedStatement = getConnection().prepareStatement(sql);
			 var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				var idType = new IdTypeEntity();
				mapResultSetToIdType(resultSet, idType);
				idTypes.add(idType);
			}

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_ALL_IDTYPE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_ALL_IDTYPE.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_IDTYPE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_IDTYPE.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return idTypes;
	}

	@Override
	public List<IdTypeEntity> findByFilter(final IdTypeEntity filterEntity) {

		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

		final List<IdTypeEntity> idTypes = new ArrayList<>();
		final var sql = new StringBuilder("SELECT id, nombre FROM TipoIdentificacion WHERE 1=1 ");
		final var parameters = new ArrayList<Object>();

		if (filterEntity.getId() != null && !UUIDHelper.getUUIDHelper().getDefault().equals(filterEntity.getId())) {
			sql.append("AND id = ? ");
			parameters.add(filterEntity.getId());
		}
		if (filterEntity.getName() != null && !filterEntity.getName().trim().isEmpty()) {
			sql.append("AND nombre LIKE ? ");
			parameters.add("%" + filterEntity.getName().trim() + "%");
		}

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			for (int i = 0; i < parameters.size(); i++) {
				preparedStatement.setObject(i + 1, parameters.get(i));
			}
			try (var resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					var idType = new IdTypeEntity();
					mapResultSetToIdType(resultSet, idType);
					idTypes.add(idType);
				}
			}
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_IDTYPE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_IDTYPE.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final NoseException exception) {
			throw exception;
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_IDTYPE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_IDTYPE.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return idTypes;
	}

	@Override
	public IdTypeEntity findById(final UUID id) {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		IdTypeEntity idType = null;
		final String sql = "SELECT id, nombre FROM TipoIdentificacion WHERE id = ?";

		try (var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setObject(1, id);
			try (var resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					idType = new IdTypeEntity();
					mapResultSetToIdType(resultSet, idType);
				}
			}
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_BY_ID_IDTYPE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_ID_IDTYPE.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_IDTYPE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_IDTYPE.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return idType;
	}
}
