package co.edu.uco.nose.crosscuting.messagescatalog;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnum {
    USER_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada vacia",
            "La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada está vacia. "
                    + "Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),

    TECHNICAL_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada nula",
            "La conexion requerida para llevar a cabo la operacion contra la base de datos llegó nula."
                    + "Por favor intenta de nuevo y si el problema persiste, contacte al administrador de la aplicación"),

    USER_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada",
            "La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada está cerrada. "
                    + "Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),

    TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada",
            "La conexion requerida para llevar a cabo la operacion contra la base de datos llegó cerrada."
                    + "Por favor intenta de nuevo y si el problema persiste, contacte al administrador de la aplicación"),

    USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Problema inesperado contra la fuente de informacion deseada vacia",
            "La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada está vacia. "
                    + "Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),

    TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_CONNECTION_STATUS("Problema inesperado contra la fuente de informacion deseada vacia" ,
            "La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada está vacia."
                    + "Por favor intenta de nuevo y si el problema persiste, contacte al administrador de la aplicación"),

    TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Error técnico inesperado al validar el estado de la conexión",
            "Se presentó un error técnico inesperado al intentar validar el estado de la conexión contra la base de datos. "
                    + "Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación"),

    USER_ERROR_TRANSACTION_IS_STARTED("Transacción no iniciada",
            "La operación no puede completarse porque la transacción requerida no ha sido iniciada. "
                    + "Por favor inicie la transacción e intente nuevamente. Si el problema persiste, contacte al administrador de la aplicación."),

    TECHNICAL_ERROR_TRANSACTION_IS_STARTED("Transacción no iniciada en la base de datos",
            "La operación no puede completarse porque la transacción requerida no fue iniciada correctamente en la base de datos. "
                    + "Por favor revise la lógica de inicio de transacciones y si el problema persiste, contacte al administrador de la aplicación."),

    USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED("Error inesperado al validar el inicio de la transacción",
            "Se presentó un problema inesperado al validar el estado de la transacción. "
                    + "Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

    TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_STARTED("Error SQL al validar el inicio de la transacción",
            "Se produjo una excepción SQL al intentar validar el estado de la transacción. "
                    + "Por favor revise la conexión con la base de datos y si el problema persiste, contacte al administrador de la aplicación."),

    TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED("Error técnico inesperado al validar el inicio de la transacción",
            "Se presentó un error técnico inesperado al intentar validar el estado de la transacción. "
                    + "Por favor revise los registros del sistema y si el problema persiste, contacte al administrador de la aplicación."),

    TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_NOT_STARTED(
            "Error SQL al validar que la transacción no ha sido iniciada",
            "Se produjo una excepción SQL al intentar validar que la transacción no fue iniciada. "
                    + "Por favor revise la conexión con la base de datos y si el problema persiste, contacte al administrador de la aplicación."
    ),

    USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_NOT_STARTED(
            "Error inesperado al validar que la transacción no ha sido iniciada",
            "Se presentó un problema inesperado al validar que la transacción no ha sido iniciada. "
                    + "Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."
    ),
    
    USER_ERROR_TRANSACTION_IS_NOT_STARTED("Transacción ya iniciada",
			"La operación no puede completarse porque la transacción requerida ya ha sido iniciada. "
					+ "Por favor asegúrese de que la transacción no esté iniciada e intente nuevamente. Si el problema persiste, contacte al administrador de la aplicación."),
    TECHNICAL_ERROR_TRANSACTION_IS_NOT_STARTED("Transacción ya iniciada en la base de datos","fLa operación no puede completarse porque la transacción requerida ya fue iniciada en la base de datos. "),
    
    

    // 🔹 NUEVOS MENSAJES PARA FIND BY ID 🔹
    USER_ERROR_FIND_BY_ID("No fue posible consultar la información solicitada",
            "No se encontró el registro solicitado o hubo un problema al intentar obtener la información. "
                    + "Por favor verifique los datos e intente nuevamente."),

    TECHNICAL_ERROR_FIND_BY_ID("Error técnico al consultar por ID",
            "Se presentó un problema técnico al intentar consultar la información por ID en la base de datos. "
                    + "Por favor revise los logs y contacte al administrador si el problema persiste.");

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