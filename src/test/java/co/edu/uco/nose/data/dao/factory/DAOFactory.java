package co.edu.uco.nose.data.dao.factory;

import java.sql.Connection;

import co.edu.uco.nose.data.dao.entity.*;


public abstract class DAOFactory {
	
	protected Connection connection;
	
	protected FactoryEnum factory = FactoryEnum.POSTGRESQL;
	
	public static DAOFactory getFactory() {
		return null;
	}
	
	public abstract CityDAO getCityDAO();
	
	public abstract CountryDAO getCountryDAO();
	
	public abstract IdTypeDAO getIdTypeDAO();
	
	public abstract StateDAO getStateDAO();
	
	public abstract UserDAO getUserDAO();
	
	protected abstract void openConnection();
	
	protected final void initTransaction(){}
	
	protected final void commitTransaction(){}
	
	protected final void rollbackTransaction(){}
	
	protected final void closeConnection(){}
	
	
	
}
