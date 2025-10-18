package co.edu.uco.nose.data.dao.factory;

import java.sql.Connection;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.data.dao.entity.*;
import co.edu.uco.nose.data.dao.factory.postgresql.PostgresqlDAOFactory;

public abstract class DAOFactory {

    protected Connection connection;
    protected static final FactoryEnum FACTORY = FactoryEnum.POSTGRESQL;

    public static DAOFactory getFactory() {
        if (FactoryEnum.POSTGRESQL.equals(FACTORY)) {
            return new PostgresqlDAOFactory();
        } else {
            throw NoseException.create(
                    "No se pudo iniciar la fábrica de DAOs.",
                    "Tipo de fábrica no soportado: " + FACTORY);
        }
    }

    public abstract CityDAO getCityDAO();

    public abstract CountryDAO getCountryDAO();

    public abstract IdTypeDAO getIdTypeDAO();

    public abstract StateDAO getStateDAO();

    public abstract UserDAO getUserDAO();

    protected abstract void openConnection();

    public void initTransaction() {
        SqlConnectionHelper.ensureTransactionIsNotStarted(connection);
        try {
            connection.setAutoCommit(false);
        } catch (Exception exception) {
            throw NoseException.create(exception,
                    "No se pudo iniciar la transacción.",
                    "Error al desactivar el autocommit de la conexión.");
        }
    }

    public void commitTransaction() {
        SqlConnectionHelper.ensureTransactionIsStarted(connection);
        try {
            connection.commit();
        } catch (Exception exception) {
            throw NoseException.create(exception,
                    "No se pudo confirmar la transacción.",
                    "Error al hacer commit de la conexión.");
        }
    }

    public void rollbackTransaction() {
        SqlConnectionHelper.ensureTransactionIsStarted(connection);
        try {
            connection.rollback();
        } catch (Exception exception) {
            throw NoseException.create(exception,
                    "No se pudo revertir la transacción.",
                    "Error al hacer rollback de la conexión.");
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception exception) {
            throw NoseException.create(exception,
                    "No se pudo cerrar la conexión.",
                    "Error al intentar cerrar la conexión a la base de datos.");
        }
    }
}
