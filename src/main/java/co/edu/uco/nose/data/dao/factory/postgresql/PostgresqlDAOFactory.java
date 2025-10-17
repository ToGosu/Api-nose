package co.edu.uco.nose.data.dao.factory.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.data.dao.entity.*;
import co.edu.uco.nose.data.dao.entity.postgresql.*;

import co.edu.uco.nose.data.dao.factory.DAOFactory;


public final class PostgresqlDAOFactory extends DAOFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/noseDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "C2wvVCP18#6@";

    public PostgresqlDAOFactory() {
        openConnection();
    }

    @Override
    protected void openConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException exception) {
            throw NoseException.create(exception,
                    "No se pudo abrir la conexión con la base de datos.",
                    "Error SQL al intentar conectar con PostgreSQL en la URL: " + URL);
        } catch (Exception exception) {
            throw NoseException.create(exception,
                    "No se pudo establecer la conexión con la base de datos.",
                    "Error inesperado al abrir la conexión con PostgreSQL.");
        }
    }



    @Override
    public CityDAO getCityDAO() {
        return new CityPostgresqlDAO(connection);
    }

    @Override
    public CountryDAO getCountryDAO() {
        return new CountryPostgresqlDAO(connection);
    }

    @Override
    public IdTypeDAO getIdTypeDAO() {
        return new IdTypePostgresqlDAO(connection);
    }

    @Override
    public StateDAO getStateDAO() {
        return new StatePostgresqlDAO(connection);
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserPostgresqlDAO(connection);
    }



    @Override
    protected final void initTransaction() {
        SqlConnectionHelper.ensureTransactionIsNotStarted(connection);
        try {
            connection.setAutoCommit(false);
        } catch (SQLException exception) {
            throw NoseException.create(exception,
                    "No se pudo iniciar la transacción.",
                    "Error SQL al desactivar el autocommit en PostgreSQL.");
        }
    }

    @Override
    protected final void commitTransaction() {
        SqlConnectionHelper.ensureTransactionIsStarted(connection);
        try {
            connection.commit();
        } catch (SQLException exception) {
            throw NoseException.create(exception,
                    "No se pudo confirmar la transacción.",
                    "Error SQL al hacer commit de la transacción en PostgreSQL.");
        }
    }

    @Override
    protected final void rollbackTransaction() {
        SqlConnectionHelper.ensureTransactionIsStarted(connection);
        try {
            connection.rollback();
        } catch (SQLException exception) {
            throw NoseException.create(exception,
                    "No se pudo revertir la transacción.",
                    "Error SQL al hacer rollback de la transacción en PostgreSQL.");
        }
    }

    @Override
    protected final void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException exception) {
            throw NoseException.create(exception,
                    "No se pudo cerrar la conexión.",
                    "Error SQL al cerrar la conexión a PostgreSQL.");
        }
    }
}
