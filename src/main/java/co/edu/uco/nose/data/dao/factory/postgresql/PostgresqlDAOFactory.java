package co.edu.uco.nose.data.dao.factory.postgresql;

import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.CityPostgresqlDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.CountryPostgresqlDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.IdTypePostgresqlDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.StatePostgresqlDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.UserPostgresqlDAO;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

public final class PostgresqlDAOFactory extends DAOFactory {
	
	public PostgresqlDAOFactory() {
		openConnection();
	}

	@Override
	protected void openConnection() {
		String url = "jdbc:postgresql://localhost:5432/apiNose";
		String user = "postgres";
		String password = "C2wvVCP18#6@";

		try {
			Class.forName("org.postgresql.Driver");
			this.connection = DriverManager.getConnection(url, user, password);
			try (var statement = this.connection.createStatement()) {
		            statement.execute("SET search_path TO apinosedb;");
		     }
			
		} catch (SQLException exception) {
			var userMassage = MessagesEnum.USER_ERROR_SQL_CANNOT_OPEN_CONNECTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CANNOT_OPEN_CONNECTION.getContent();
			throw NoseException.create(exception, userMassage, technicalMessage);
		} catch (Exception exception) {
			var userMassage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_OPENING_CONNECTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_OPENING_CONNECTION.getContent();
			throw NoseException.create(exception, userMassage, technicalMessage);
		}
	}

	@Override
	public CityDAO getCityDAO() { return new CityPostgresqlDAO(connection); }

	@Override
	public CountryDAO getCountryDAO() { return new CountryPostgresqlDAO(connection); }

	@Override
	public IdTypeDAO getIdTypeDAO() { return new IdTypePostgresqlDAO(connection); }

	@Override
	public StateDAO getStateDAO() { return new StatePostgresqlDAO(connection); }

	@Override
	public UserDAO getUserDAO() { return new UserPostgresqlDAO(connection); }
}
