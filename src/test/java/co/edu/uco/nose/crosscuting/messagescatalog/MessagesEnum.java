package co.edu.uco.nose.crosscuting.messagescatalog;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnum {
	
	USER_ERROR_SQL_CONNCETION_IS_EMPTY("Conexion contra la fuente de informacion deseada vacia","La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada está vacia. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
	TECHNICAL_ERROR_SQL_CONNCETION_IS_EMPTY("Conexion contra la fuente de informacion deseada nula","La conexion requerida para llevar a cabo la operacion contra la base de datos llegó nula."),
	
	USER_ERROR_SQL_CONNCETION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada","La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada está cerrada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
	TECHNICAL_ERROR_SQL_CONNCETION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada","La conexion requerida para llevar a cabo la operacion contra la base de datos llegó cerrada."),
	
	USER_ERROR_SQL_CONNCETION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Problema inesperado contra la fuente de informacion deseada","La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada ha dado con un error inesperado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
	TECHNICAL_ERROR_SQL_CONNCETION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Problema inesperado contra la fuente de informacion deseada","Se produjo un problema inesperado al validar el estado de la conexion contra la fuente de informacion deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
	
	USER_ERROR_SQL_CONNECTION_TRANSACTION_NOT_STARTED("Error al iniciar","No se ha podido iniciar la transaccion contra la fuente de informacion deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
	TECHNICAL_ERROR_SQL_CONNECTION_TRANSACTION_NOT_STARTED("Error al iniciar","No se ha podido iniciar la transaccion contra la fuente de informacion deseada"),;
	
	
	private String title;
	private String content;
	
	private MessagesEnum(final String title, final String content) {
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	private void setTitle(final String title) {
		this.title = TextHelper.getDefaultWithTrim(title);
	}

	public String getContent() {
		return content;
	}

	private void setContent(final String content) {
		this.content = TextHelper.getDefaultWithTrim(content);
	}
	
	
}
