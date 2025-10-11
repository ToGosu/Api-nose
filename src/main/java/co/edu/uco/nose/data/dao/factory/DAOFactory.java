package co.edu.uco.nose.data.dao.factory;

import java.sql.Connection;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.data.dao.entity.*;
import co.edu.uco.nose.data.dao.factory.sqlserver.PostgresqlDAOFactory;


public abstract class DAOFactory {
	
	protected Connection connection;
	
	protected static FactoryEnum factory = FactoryEnum.POSTGRESQL;
	
	public static DAOFactory getFactory() {
		if (FactoryEnum.POSTGRESQL.equals(factory)) {
			return new PostgresqlDAOFactory();
		}else {
			var userMessage= "Factoria no iniciada";
			var technicalMessage= "Factoria no valida";
			throw NoseException.create(userMessage, technicalMessage);
		}
		
		}
	
	
	public abstract CityDAO getCityDAO();
	
	public abstract CountryDAO getCountryDAO();
	
	public abstract IdTypeDAO getIdTypeDAO();
	
	public abstract StateDAO getStateDAO();
	
	public abstract UserDAO getUserDAO();
	
	protected abstract void openConnection();
	
	protected final void initTransaction(){
		SqlConnectionHelper.ensureTransactionIsNotStarted(connection);
		try {
			connection.setAutoCommit(false);
		} catch (final Exception exception) {
			var userMessage= "";
			var technicalMessage= "";
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Throwable exception) {
			var userMessage= "";
			var technicalMessage= "";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}
	
	protected final void commitTransaction(){
		SqlConnectionHelper.ensureTransactionIsNotStarted(connection);
		try {
			connection.commit();
		} catch (final Exception exception) {
			var userMessage= "";
			var technicalMessage= "";
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Throwable exception) {
			var userMessage= "";
			var technicalMessage= "";
			throw NoseException.create(exception, userMessage, technicalMessage);

		}
	}
	
	protected final void rollbackTransaction(){
		SqlConnectionHelper.ensureTransactionIsNotStarted(connection);
		try {
			connection.rollback();
		} catch (final Exception exception) {
			var userMessage= "";
			var technicalMessage= "";
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Throwable exception) {
			var userMessage= "";
			var technicalMessage= "";
			throw NoseException.create(exception, userMessage, technicalMessage);

		}
	}
	
	protected final void closeConnection(){
		SqlConnectionHelper.ensureTransactionIsNotStarted(connection);
		try {
			connection.close();
		} catch (final Exception exception) {
			var userMessage= "";
			var technicalMessage= "";
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Throwable exception) {
			var userMessage= "";
			var technicalMessage= "";
			throw NoseException.create(exception, userMessage, technicalMessage);

		}
	}
	
	
	
	
}
