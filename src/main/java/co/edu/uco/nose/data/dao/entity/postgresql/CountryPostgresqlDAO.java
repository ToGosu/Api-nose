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
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.entity.CountryEntity;

public final class CountryPostgresqlDAO extends SqlConnection implements CountryDAO{

	public CountryPostgresqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public CountryEntity findById(final UUID id) {
		return findByFilter(new CountryEntity(id))
				.stream()
				.findFirst()
				.orElse(new CountryEntity());
	}

	@Override
	public List<CountryEntity> findAll() {
		return findByFilter(new CountryEntity());
	}

	@Override
	public List<CountryEntity> findByFilter(final CountryEntity filterEntity) {

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
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_COUNTRY.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_COUNTRY
					.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}

	}

	private String createSentenceFindByFilter (final CountryEntity filterEntity, final List<Object> parameterList) {

		final var sql = new StringBuilder(" SELECT c.id, c.nombre FROM apinosedb.Pais c ");

		createWhereClauseFindByFilter(sql, parameterList, filterEntity);

		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameterList,
											  final CountryEntity filterEntity) {
		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new CountryEntity());
		final var conditions = new ArrayList<String>();

		addCondition(conditions, parameterList,
				!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getId()),
				"c.id = ?",filterEntityValidated.getId());

		addCondition(conditions, parameterList,
				!TextHelper.isEmptyWithTrim(filterEntityValidated.getName()),
				"c.nombre = ?",filterEntityValidated.getName());

		if (!conditions.isEmpty()){
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

	private List<CountryEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
		var listCountry = new ArrayList<CountryEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				var country = new CountryEntity();

				country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
				country.setName(resultSet.getString("nombre"));

				listCountry.add(country);
			}

		}  catch (SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_MAPPING_COUNTRY.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_COUNTRY
					.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (Exception exception){
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_EXECUTING_MAPPING_COUNTRY.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_EXECUTING_MAPPING_COUNTRY
					.getContent() + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}

		return listCountry;
    }

}


