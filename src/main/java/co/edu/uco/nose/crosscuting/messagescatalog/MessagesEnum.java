package co.edu.uco.nose.crosscuting.messagescatalog;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnum {

	//SqlConnectionHelper
	
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
	
	USER_ERROR_TRANSACTION_IS_NOT_STARTED("Transacción no iniciada",
			"La operación no puede completarse porque la transacción requerida no ha sido iniciada. "
			+ "Por favor inicie la transacción e intente nuevamente. Si el problema persiste, contacte al administrador de la aplicación."),
	
	TECHNICAL_ERROR_TRANSACTION_IS_NOT_STARTED("Transacción no iniciada en la base de datos",
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

	USER_ERROR_TRANSACTION_IS_STARTED("Transacción ya iniciada",
			"La operación no puede completarse porque la transacción ya se encuentra iniciada. "
					+ "Por favor verifique el estado de la transacción e intente nuevamente. Si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_TRANSACTION_IS_STARTED("Transacción ya iniciada en la base de datos",
			"La operación no puede completarse porque la transacción ya se encontraba iniciada en la base de datos. "
					+ "Por favor revise la lógica de control de transacciones y si el problema persiste, contacte al administrador de la aplicación."),

	USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_NOT_STARTED("Error inesperado al validar que la transacción no esté iniciada",
			"Se presentó un problema inesperado al validar que la transacción no se encontrara iniciada. "
					+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_NOT_STARTED("Error SQL al validar que la transacción no esté iniciada",
			"Se produjo una excepción SQL al intentar validar que la transacción no se encontrara iniciada. "
					+ "Por favor revise la conexión con la base de datos y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_NOT_STARTED("Error técnico inesperado al validar que la transacción no esté iniciada",
			"Se presentó un error técnico inesperado al intentar validar que la transacción no estuviera iniciada. "
					+ "Por favor revise los registros del sistema y si el problema persiste, contacte al administrador de la aplicación."),




	//SqlServerDAOFactory

	USER_ERROR_SQL_CANNOT_OPEN_CONNECTION("No fue posible establecer conexión con la base de datos",
			"Se presentó un error al intentar conectarse con la base de datos PostgreSQL. " +
					"Por favor verifique los datos de conexión (URL, usuario y contraseña) e intente nuevamente. " +
			    	"Si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_CANNOT_OPEN_CONNECTION("Error SQL al intentar abrir la conexión con PostgreSQL",
			"Ocurrió una excepción SQL al ejecutar DriverManager.getConnection(). "
				   + "Verifique que el servicio de base de datos esté disponible, las credenciales sean correctas y la URL sea válida. "
				   + "Si el problema persiste, contacte al administrador de la aplicación."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_OPENING_CONNECTION("Error inesperado al intentar abrir la conexión",
		   "Se presentó un problema inesperado al intentar establecer la conexión con la base de datos. "
				   + "Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_OPENING_CONNECTION("Error técnico inesperado al intentar abrir la conexión",
			"Se presentó un error técnico no controlado al intentar establecer la conexión con la base de datos. "
					+ "Por favor revise los registros del sistema y si el problema persiste, contacte al administrador de la aplicación."),




	//DAOFatory

	USER_ERROR_SQL_CANNOT_INIT_TRANSACTION(
			"No fue posible iniciar la transacción con la base de datos",
			"Se presentó un error al intentar iniciar una transacción en la base de datos PostgreSQL. " +
					"Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_CANNOT_INIT_TRANSACTION(
			"Error SQL al intentar iniciar la transacción con PostgreSQL",
			"Ocurrió una excepción SQL al intentar ejecutar la instrucción de inicio de transacción (BEGIN/COMMIT/ROLLBACK). " +
					"Verifique que la conexión esté activa y que la base de datos permita transacciones en este contexto. " +
					"Si el problema persiste, contacte al administrador de la aplicación."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_INIT_TRANSACTION(
			"Error inesperado al intentar iniciar la transacción",
			"Se presentó un problema inesperado al intentar comenzar la transacción con la base de datos. " +
					"Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INIT_TRANSACTION(
			"Error técnico inesperado al intentar iniciar la transacción",
			"Se presentó un error técnico no controlado al intentar iniciar una transacción en la base de datos. " +
					"Por favor revise los registros del sistema y si el problema persiste, contacte al administrador de la aplicación."),

	USER_ERROR_SQL_DATASOURCE_NOT_AVAILABLE(
			"La fuente de información no está disponible en el sistema",
			"La fuente de información sobre la cual se va a realizar la transacción seleccionada no está disponible dentro del sistema. "
					+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_DATASOURCE_NOT_AVAILABLE(
			"Error técnico al intentar acceder a la fuente de información",
			"La factoría correspondiente a la fuente de información solicitada no existe o no se ha implementado correctamente. "
					+ "Verifique que la clase de factoría haya sido creada y registrada en el sistema. "
					+ "Si el problema persiste, contacte al administrador de la aplicación."),

	USER_ERROR_SQL_CANNOT_COMMIT_TRANSACTION(
			"No fue posible confirmar la transacción en la base de datos",
			"Se presentó un error al intentar confirmar (commit) la transacción en la base de datos PostgreSQL. " +
					"Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_CANNOT_COMMIT_TRANSACTION(
			"Error SQL al intentar confirmar la transacción en PostgreSQL",
			"Ocurrió una excepción SQL al ejecutar la instrucción COMMIT. " +
					"Verifique que la conexión esté activa, que la transacción haya sido iniciada correctamente y que no existan bloqueos en la base de datos. " +
					"Si el problema persiste, contacte al administrador de la aplicación."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_COMMIT_TRANSACTION(
			"Error inesperado al intentar confirmar la transacción",
			"Se presentó un problema inesperado al intentar confirmar la transacción con la base de datos. " +
					"Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_COMMIT_TRANSACTION(
			"Error técnico inesperado al intentar confirmar la transacción",
			"Se presentó un error técnico no controlado durante la confirmación (commit) de la transacción. " +
					"Por favor revise los registros del sistema y si el problema persiste, contacte al administrador de la aplicación."),

	USER_ERROR_SQL_CANNOT_ROLLBACK_TRANSACTION(
			"No fue posible revertir la transacción en la base de datos",
			"Se presentó un error al intentar deshacer (rollback) la transacción en la base de datos PostgreSQL. " +
					"Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_CANNOT_ROLLBACK_TRANSACTION(
			"Error SQL al intentar revertir la transacción en PostgreSQL",
			"Ocurrió una excepción SQL al ejecutar la instrucción ROLLBACK. " +
					"Verifique que la conexión esté activa y que exista una transacción iniciada antes de realizar el rollback. " +
					"Si el problema persiste, contacte al administrador de la aplicación."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_ROLLBACK_TRANSACTION(
			"Error inesperado al intentar revertir la transacción",
			"Se presentó un problema inesperado al intentar revertir la transacción con la base de datos. " +
					"Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_ROLLBACK_TRANSACTION(
			"Error técnico inesperado al intentar revertir la transacción",
			"Se presentó un error técnico no controlado durante la reversión (rollback) de la transacción. " +
					"Por favor revise los registros del sistema y si el problema persiste, contacte al administrador de la aplicación."),

	USER_ERROR_SQL_CANNOT_CLOSE_CONNECTION(
			"No fue posible cerrar la conexión con la base de datos",
			"Se presentó un error al intentar cerrar la conexión con la base de datos PostgreSQL. " +
					"Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_CANNOT_CLOSE_CONNECTION(
			"Error SQL al intentar cerrar la conexión con PostgreSQL",
			"Ocurrió una excepción SQL al ejecutar el método Connection.close(). " +
					"Verifique que la conexión esté activa y no haya sido cerrada previamente. " +
					"Si el problema persiste, contacte al administrador de la aplicación."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_CLOSING_CONNECTION(
			"Error inesperado al intentar cerrar la conexión",
			"Se presentó un problema inesperado al intentar cerrar la conexión con la base de datos. " +
					"Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_CLOSING_CONNECTION(
			"Error técnico inesperado al intentar cerrar la conexión",
			"Se presentó un error técnico no controlado durante el cierre de la conexión con la base de datos. " +
					"Por favor revise los registros del sistema y si el problema persiste, contacte al administrador de la aplicación."),




	//UserSqlServerDAO

	//Insertar
	USER_ERROR_SQL_INSERT_USER("Error al registrar la información del nuevo usuario",
			"Se ha presentado un problema tratando de registrar la información del nuevo usuario. "
					+ "Por favor intente de nuevo y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_INSERT_USER("Error técnico al registrar la información del nuevo usuario",
			"Se ha presentado un problema al tratar de ejecutar el proceso de creación de un nuevo usuario en la base de datos. "
					+ "Por favor valide que la base de datos esté funcionando correctamente. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_INSERT_USER("Error inesperado al registrar la información del nuevo usuario",
			"Se ha presentado un problema inesperado tratando de registrar la información del nuevo usuario. "
					+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_USER("Error técnico inesperado al registrar la información del nuevo usuario",
			"Se ha presentado un problema técnico inesperado al tratar de ejecutar el proceso de creación de un nuevo usuario. "
					+ "Por favor valide que la base de datos esté funcionando correctamente y revise los registros del sistema. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	//Mapper
	USER_ERROR_SQL_MAPPING_USER("Error mapeando la información del usuario",
			"Se ha presentado un problema tratando de interpretar la información del usuario consultada desde la base de datos. "
					+ "Por favor, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_MAPPING_USER("Error técnico mapeando el ResultSet a UserEntity",
			"Se ha presentado un problema técnico al mapear un registro del ResultSet a la entidad UserEntity. "
					+ "Verifique que los nombres de las columnas en la consulta SQL coincidan exactamente con los esperados por el método de mapeo. "
					+ "Revise la traza de la SQLException para identificar la columna o el tipo de dato incorrecto."),

	USER_ERROR_SQL_UNEXPECTED_MAPPING_USER("Error inesperado mapeando la información del usuario",
			"Se ha presentado un problema inesperado tratando de interpretar la información del usuario. "
					+ "Por favor, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_USER("Error técnico inesperado en el mapeo de UserEntity",
			"Se ha presentado un error inesperado (no-SQL) durante el proceso de mapeo del ResultSet a la entidad UserEntity. "
					+ "Revise la traza completa de la excepción para determinar la causa raíz."),

	//Consultar todos

	USER_ERROR_SQL_EXECUTING_FIND_ALL_USER("Error al consultar todos los usuarios",
			"Se ha presentado un problema tratando de consultar la información de todos los usuarios. "
					+ "Por favor intente de nuevo y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_ALL_USER("Error técnico al consultar todos los usuarios",
			"Se ha presentado un problema técnico al tratar de ejecutar la consulta SQL para obtener la información de todos los usuarios en la base de datos. "
					+ "Por favor valide que la conexión a la base de datos sea correcta. "
					+ "Si el problema persiste, revise la traza de la excepción y contacte al administrador."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_USER("Error inesperado al consultar todos los usuarios",
			"Se ha presentado un problema inesperado tratando de consultar la información de todos los usuarios. "
					+ "Por favor intente de nuevo y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_USER("Error técnico inesperado al consultar todos los usuarios",
			"Se ha presentado un problema técnico inesperado y fuera de control al tratar de consultar la información de todos los usuarios. "
					+ "Por favor revise la traza de la excepción para identificar la causa raíz del inconveniente. "
					+ "Si el problema persiste, contacte al administrador."),


	//Consultar por filtro

	USER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_USER("Error al filtrar los usuarios",
			"Se ha presentado un problema tratando de consultar los usuarios que cumplen con los criterios de búsqueda especificados. "
					+ "Por favor intente de nuevo y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_USER("Error técnico al filtrar los usuarios",
			"Se ha presentado un problema técnico al tratar de ejecutar la consulta SQL para filtrar la información de los usuarios en la base de datos. "
					+ "Por favor valide que la conexión a la base de datos sea correcta. "
					+ "Si el problema persiste, revise la traza de la excepción y contacte al administrador."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_USER("Error inesperado al filtrar los usuarios",
			"Se ha presentado un problema inesperado tratando de filtrar la información de los usuarios. "
					+ "Por favor intente de nuevo y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_USER("Error técnico inesperado al filtrar los usuarios",
			"Se ha presentado un problema técnico inesperado y fuera de control al tratar de filtrar la información de los usuarios. "
					+ "Por favor revise la traza de la excepción para identificar la causa raíz del inconveniente. "
					+ "Si el problema persiste, contacte al administrador."),

	//Consultar por Id

	USER_ERROR_SQL_PREPARING_FIND_BY_ID_USER("Error al preparar la sentencia para consultar el usuario por ID",
			"Se ha presentado un problema tratando de preparar la sentencia SQL para consultar la información del usuario deseado. "
					+ "Por favor intente de nuevo y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_PREPARING_FIND_BY_ID_USER("Error técnico al preparar la sentencia para consultar el usuario por ID",
			"Se ha presentado un problema técnico al tratar de preparar la sentencia SQL para consultar la información del usuario deseado en la base de datos. "
					+ "Por favor valide que la base de datos esté funcionando correctamente. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	USER_ERROR_SQL_EXECUTING_FIND_BY_ID_USER("Error al ejecutar la consulta del usuario por ID",
			"Se ha presentado un problema tratando de ejecutar la consulta SQL para obtener la información del usuario deseado. "
					+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_ID_USER("Error técnico al ejecutar la consulta del usuario por ID",
			"Se ha presentado un problema técnico al tratar de ejecutar la consulta SQL para obtener la información del usuario deseado. "
					+ "Por favor valide que la base de datos esté funcionando correctamente y revise los registros del sistema. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_USER("Error inesperado al consultar la información del usuario deseado",
			"Se ha presentado un problema inesperado tratando de consultar la información del usuario deseado. "
					+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_USER("Error técnico inesperado al consultar la información del usuario deseado",
			"Se ha presentado un problema técnico inesperado al tratar de ejecutar el proceso de consulta del usuario deseado. "
					+ "Por favor valide que la base de datos esté funcionando correctamente y revise los registros del sistema. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	//Actualizar

	USER_ERROR_SQL_UPDATE_USER("Error al modificar la información del usuario",
			"Se ha presentado un problema tratando de modificar la información del usuario. "
					+ "Por favor intente de nuevo y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UPDATE_USER("Error técnico al modificar la información del usuario",
			"Se ha presentado un problema al tratar de ejecutar el proceso de modificación del usuario en la base de datos. "
					+ "Por favor valide que la base de datos esté funcionando correctamente. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_USER("Error inesperado al modificar la información del usuario",
			"Se ha presentado un problema inesperado tratando de modificar la información del usuario. "
					+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_USER("Error técnico inesperado al modificar la información del usuario",
			"Se ha presentado un problema técnico inesperado al tratar de ejecutar el proceso de modificación del usuario. "
					+ "Por favor valide que la base de datos esté funcionando correctamente y revise los registros del sistema. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	//Eliminar

	USER_ERROR_SQL_DELETE_USER("Error al eliminar la información del usuario",
			"Se ha presentado un problema tratando de eliminar la información del usuario. "
					+ "Por favor intente de nuevo y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_DELETE_USER("Error técnico al eliminar la información del usuario",
			"Se ha presentado un problema al tratar de ejecutar el proceso de eliminación del usuario en la base de datos. "
					+ "Por favor valide que la base de datos esté funcionando correctamente. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_DELETE_USER("Error inesperado al eliminar la información del usuario",
			"Se ha presentado un problema inesperado tratando de eliminar la información del usuario. "
					+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_USER("Error técnico inesperado al eliminar la información del usuario",
			"Se ha presentado un problema técnico inesperado al tratar de ejecutar el proceso de eliminación del usuario. "
					+ "Por favor valide que la base de datos esté funcionando correctamente y revise los registros del sistema. "
					+ "Si el problema persiste, contacte al administrador del sistema."),




	//CountrSqlServerDAO

	// PAIS (COUNTRY)
	USER_ERROR_SQL_MAPPING_COUNTRY("Error interpretando la información del país",
			"Se ha presentado un problema tratando de interpretar la información de un país consultada desde la base de datos. " +
					"Por favor, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_MAPPING_COUNTRY("Error técnico mapeando el ResultSet a CountryEntity",
			"Se ha presentado un problema técnico al mapear un registro del ResultSet a la entidad CountryEntity. " +
					"Verifique que los nombres de las columnas en la consulta SQL coincidan con los esperados por el método de mapeo."),

	USER_ERROR_SQL_UNEXPECTED_EXECUTING_MAPPING_COUNTRY("Error al consultar los países",
			"Se ha presentado un problema tratando de consultar la información de los países. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_EXECUTING_MAPPING_COUNTRY("Error técnico al consultar los países",
			"Se ha presentado un problema técnico al tratar de ejecutar la consulta SQL para obtener la " +
					"información de los países en la base de datos."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_COUNTRY("Error inesperado al consultar los países",
			"Se ha presentado un problema inesperado tratando de consultar la información de todos los países. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_COUNTRY("Error técnico inesperado al consultar todos los países",
			"Se ha presentado un problema técnico inesperado al tratar de consultar la información de todos los " +
					"países en la base de datos. Por favor revise la traza de la excepción."),

	USER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_COUNTRY("Error al filtrar los países",
			"Se ha presentado un problema tratando de consultar los países que cumplen con los criterios de " +
					"búsqueda especificados. Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_COUNTRY("Error técnico al filtrar los países",
			"Se ha presentado un problema técnico al tratar de ejecutar la consulta SQL para filtrar la " +
					"información de los países en la base de datos."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_COUNTRY("Error inesperado al filtrar los países",
			"Se ha presentado un problema inesperado tratando de filtrar la información de los países. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_COUNTRY("Error técnico inesperado al filtrar los países",
			"Se ha presentado un problema técnico inesperado al tratar de filtrar la información de los países. " +
					"Por favor revise la traza de la excepción."),

	USER_ERROR_SQL_EXECUTING_FIND_BY_ID_COUNTRY("Error al consultar el país por ID",
			"Se ha presentado un problema tratando de consultar la información del país deseado. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_ID_COUNTRY("Error técnico al consultar el país por ID",
			"Se ha presentado un problema técnico al tratar de ejecutar la consulta SQL para la información del país deseado."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_COUNTRY("Error inesperado consultando el país por ID",
			"Se ha presentado un problema inesperado tratando de consultar la información del país deseado. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_COUNTRY("Error técnico inesperado consultando el país por ID",
			"Se ha presentado un problema técnico inesperado al tratar de consultar la información del país deseado. " +
					"Por favor revise la traza de la excepción."),



	//StateSqlServerDAO

	USER_ERROR_SQL_MAPPING_STATE("Error interpretando la información del departamento",
			"Se ha presentado un problema tratando de interpretar la información de un departamento desde la base de datos. " +
					"Por favor, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_MAPPING_STATE("Error técnico mapeando el ResultSet a StateEntity",
			"Se ha presentado un problema técnico al mapear un registro a la entidad StateEntity. " +
					"Verifique que los alias de las columnas en la consulta SQL ('idDepartamento', 'nombreDepartamento', 'idPais', 'nombrePais') coincidan con los esperados por el método de mapeo."),

	USER_ERROR_SQL_EXECUTING_FIND_ALL_STATE("Error al consultar los departamentos",
			"Se ha presentado un problema tratando de consultar la información de todos los departamentos. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_ALL_STATE("Error técnico al consultar todos los departamentos",
			"Se ha presentado un problema técnico al ejecutar la consulta SQL para obtener la información de todos los departamentos."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_STATE("Error inesperado al consultar los departamentos",
			"Se ha presentado un problema inesperado tratando de consultar la información de todos los departamentos. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_STATE("Error técnico inesperado al consultar todos los departamentos",
			"Se ha presentado un problema técnico inesperado al consultar todos los departamentos. " +
					"Por favor revise la traza de la excepción."),

	USER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_STATE("Error al filtrar los departamentos",
			"Se ha presentado un problema tratando de consultar los departamentos que cumplen con los criterios de búsqueda. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_STATE("Error técnico al filtrar los departamentos",
			"Se ha presentado un problema técnico al ejecutar la consulta SQL para filtrar la información de los departamentos."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_STATE("Error inesperado al filtrar los departamentos",
			"Se ha presentado un problema inesperado al filtrar los departamentos. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_STATE("Error técnico inesperado al filtrar los departamentos",
			"Se ha presentado un problema técnico inesperado al filtrar los departamentos. " +
					"Por favor revise la traza de la excepción."),

	USER_ERROR_SQL_EXECUTING_FIND_BY_ID_STATE("Error al consultar el departamento por ID",
			"Se ha presentado un problema al consultar la información del departamento deseado." +
					" Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_ID_STATE("Error técnico al consultar el departamento por ID",
			"Se ha presentado un problema técnico al ejecutar la consulta SQL para la información del departamento deseado."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_STATE("Error inesperado consultando el departamento por ID",
			"Se ha presentado un problema inesperado al consultar la información del departamento deseado. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_STATE("Error técnico inesperado consultando el departamento por ID",
			"Se ha presentado un problema técnico inesperado al consultar la información del departamento deseado. " +
					"Por favor revise la traza de la excepción."),




	// CitySqlServerDAO
	USER_ERROR_SQL_MAPPING_CITY("Error interpretando la información de la ciudad",
			"Se ha presentado un problema tratando de interpretar la información de una ciudad desde la base de datos. " +
					"Por favor, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_MAPPING_CITY("Error técnico mapeando el ResultSet a CityEntity",
			"Se ha presentado un problema técnico al mapear un registro a la entidad CityEntity. " +
					"Verifique que los alias de las columnas ('idCiudad', 'nombreCiudad', 'idDepartamento', etc.) coincidan con los esperados por el método de mapeo."),

	USER_ERROR_SQL_EXECUTING_FIND_ALL_CITY("Error al consultar las ciudades",
			"Se ha presentado un problema tratando de consultar la información de todas las ciudades. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_ALL_CITY("Error técnico al consultar todas las ciudades",
			"Se ha presentado un problema técnico al ejecutar la consulta SQL para obtener la información de todas las ciudades."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_CITY("Error inesperado al consultar las ciudades",
			"Se ha presentado un problema inesperado tratando de consultar la información de todas las ciudades. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_CITY("Error técnico inesperado al consultar todas las ciudades",
			"Se ha presentado un problema técnico inesperado al consultar todas las ciudades." +
					" Por favor revise la traza de la excepción."),

	USER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_CITY("Error al filtrar las ciudades",
			"Se ha presentado un problema tratando de consultar las ciudades que cumplen con los criterios de búsqueda." +
					" Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_CITY("Error técnico al filtrar las ciudades",
			"Se ha presentado un problema técnico al ejecutar la consulta SQL para filtrar la información de las ciudades."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_CITY("Error inesperado al filtrar las ciudades",
			"Se ha presentado un problema inesperado al filtrar las ciudades. Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_CITY("Error técnico inesperado al filtrar las ciudades",
			"Se ha presentado un problema técnico inesperado al filtrar las ciudades. " +
					"Por favor revise la traza de la excepción."),

	USER_ERROR_SQL_EXECUTING_FIND_BY_ID_CITY("Error al consultar la ciudad por ID",
			"Se ha presentado un problema al consultar la información de la ciudad deseada. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_ID_CITY("Error técnico al consultar la ciudad por ID",
			"Se ha presentado un problema técnico al ejecutar la consulta SQL para la información de la ciudad deseada."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_CITY("Error inesperado consultando la ciudad por ID",
			"Se ha presentado un problema inesperado al consultar la información de la ciudad deseada. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_CITY("Error técnico inesperado consultando la ciudad por ID",
			"Se ha presentado un problema técnico inesperado al consultar la información de la ciudad deseada. " +
					"Por favor revise la traza de la excepción."),




	//IdTypeSqlServerDAO

	// TIPO DE IDENTIFICACION (IDTYPE)
	USER_ERROR_SQL_MAPPING_IDTYPE("Error interpretando la información del tipo de identificación",
			"Se ha presentado un problema tratando de interpretar la información de un tipo de identificación desde la base de datos. " +
					"Por favor, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_MAPPING_IDTYPE("Error técnico mapeando el ResultSet a IdTypeEntity",
			"Se ha presentado un problema técnico al mapear un registro del ResultSet a la entidad IdTypeEntity. " +
					"Verifique que los nombres de las columnas en la consulta SQL coincidan con los esperados por el método de mapeo."),

	USER_ERROR_SQL_EXECUTING_FIND_ALL_IDTYPE("Error al consultar los tipos de identificación",
			"Se ha presentado un problema tratando de consultar la información de todos los tipos de identificación. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_ALL_IDTYPE("Error técnico al consultar todos los tipos de identificación",
			"Se ha presentado un problema técnico al ejecutar la consulta SQL para obtener la información de todos los tipos de identificación."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_IDTYPE("Error inesperado al consultar los tipos de identificación",
			"Se ha presentado un problema inesperado al consultar la información de todos los tipos de identificación. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_IDTYPE("Error técnico inesperado al consultar los tipos de identificación",
			"Se ha presentado un problema técnico inesperado al consultar todos los tipos de identificación. " +
					"Por favor revise la traza de la excepción."),

	USER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_IDTYPE("Error al filtrar los tipos de identificación",
			"Se ha presentado un problema al consultar los tipos de identificación que cumplen con los criterios de búsqueda. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_IDTYPE("Error técnico al filtrar los tipos de identificación",
			"Se ha presentado un problema técnico al ejecutar la consulta SQL para filtrar la información de los tipos de identificación."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_IDTYPE("Error inesperado al filtrar los tipos de identificación",
			"Se ha presentado un problema inesperado al filtrar la información de los tipos de identificación. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_IDTYPE("Error técnico inesperado al filtrar los tipos de identificación",
			"Se ha presentado un problema técnico inesperado al filtrar los tipos de identificación. " +
					"Por favor revise la traza de la excepción."),

	USER_ERROR_SQL_EXECUTING_FIND_BY_ID_IDTYPE("Error al consultar el tipo de identificación por ID",
			"Se ha presentado un problema al consultar la información del tipo de identificación deseado. " +
					"Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_ID_IDTYPE("Error técnico al consultar el tipo de identificación por ID",
			"Se ha presentado un problema técnico al ejecutar la consulta SQL para la información del tipo de identificación deseado."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_IDTYPE("Error inesperado consultando el tipo de identificación por ID",
			"Se ha presentado un problema inesperado al consultar la información del tipo de identificación deseado." +
					" Por favor intente de nuevo y si el problema persiste, contacte al administrador."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_IDTYPE("Error técnico inesperado consultando el tipo de identificación por ID",
			"Se ha presentado un problema técnico inesperado al consultar la información del tipo de identificación deseado. " +
					"Por favor revise la traza de la excepción.");


	private String title;
	private String content;
	
	private MessagesEnum(final String title, final String content) {
		setTitle(title);
		setContent(content);
	}

	public String getTitle() {
		return title;
	}

	private void setTitle(String title) {
		this.title = TextHelper.getDefaultWithTrim(title);
	}

	public String getContent() {
		return content;
	}

	private void setContent(String content) {
		this.content = TextHelper.getDefaultWithTrim(content);
	}
}