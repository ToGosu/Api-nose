package co.edu.uco.nose.data.dao.entity;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;

public abstract class SqlConnection {
	
	private Connection connection;
	
	protected SqlConnection(Connection connection2) {
		setConnection(connection);
	}

	public Connection getConnection() {
		return connection;
	}

	private void setConnection(Connection connection) {
		
		if (ObjectHelper.isNull(connection)) {
			var userMessage= MessagesEnum.USER_ERROR_SQL_CONNECTION_IS_EMPTY.getContent();
			var technicalMessage=MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_IS_EMPTY.getContent();
			throw NoseException.create(userMessage, technicalMessage);	
		}
		
		
		try {
			if(connection.isClosed()) {
				var userMessage= MessagesEnum.USER_ERROR_SQL_CONNECTION_IS_CLOSED.getContent();
				var technicalMessage=MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED.getContent();
				throw NoseException.create(userMessage, technicalMessage);	
			}
			
			if (connection.getAutoCommit()) {
				var userMessage = MessagesEnum.USER_ERROR_TRANSACTION_IS_NOT_STARTED.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_TRANSACTION_IS_NOT_STARTED.getContent();
				throw NoseException.create(userMessage, technicalMessage);
			}
			
		} catch (SQLException exception) {
			var userMessage= MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED.getContent();
			var technicalMessage=MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_STARTED.getContent();
			throw NoseException.create(userMessage, technicalMessage);	
		
		}
		
		
		
		
		
		this.connection = connection;
	}
	
	
}
