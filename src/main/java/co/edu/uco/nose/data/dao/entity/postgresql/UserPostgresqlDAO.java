package co.edu.uco.nose.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.StateEntity;
import co.edu.uco.nose.entity.UserEntity;

public final class UserPostgresqlDAO extends SqlConnection implements UserDAO {

	
	
	@Override
	public void create(final UserEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql=new StringBuilder();
		sql.append("INSERT INTO User(id, idType, phoneNumber, firstName, secondName, firstLastName, secondLastName, residenceCity, email, phoneNumber, emailConfirmed, mobileNumberConfirmed) ");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		try (var preparedStatement=this.getConnection().prepareStatement(sql.toString())){
			preparedStatement.setObject(1, entity.getId());
			preparedStatement.setObject(2, entity.getIdType().getId());
			preparedStatement.setString(3, entity.getPhoneNumber());
			preparedStatement.setString(4, entity.getFirstName());
			preparedStatement.setString(5, entity.getSecondName());
			preparedStatement.setString(6, entity.getFirstLastName());
			preparedStatement.setString(7, entity.getSecondLastName());
			preparedStatement.setObject(8, entity.getResidenceCity().getId());
			preparedStatement.setString(9, entity.getEmail());
			preparedStatement.setString(10, entity.getPhoneNumber());
			preparedStatement.setBoolean(11, entity.isEmailConfirmed());
			preparedStatement.setBoolean(12, entity.isMobileNumberConfirmed());
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			var userMessage= "No se pudo crear el usuario";
			var technicalMessage= "Se ha presentado un error al intentar crear el usuario en la base de datos. Por favor verifique la traza completa del error";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage= "No se pudo crear el usuario";
			var technicalMessage= "Se ha presentado un error inesperado al intentar crear el usuario en la base de datos. Por favor verifique la traza completa del error";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Throwable exception) {
			var userMessage= "No se pudo crear el usuario";
			var technicalMessage= "Se ha presentado un error critico al intentar crear el usuario en la base de datos. Por favor verifique la traza completa del error";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	public UserPostgresqlDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}


	@Override
	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserEntity> findByFilter(UserEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity findById(final UUID id) {
		
		var user = new UserEntity();
		
		final var sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  	u.id, ");
		sql.append("  	ti.id AS idTipoIdentificacion, ");
		sql.append("  	ti.nombre AS nombreTipoIdentificacion, ");
		sql.append("  	u.numeroIdentificacion, ");
		sql.append("  	u.primerNombre, ");
		sql.append("  	u.segundoNombre, ");
		sql.append("  	u.primerApellido, ");
		sql.append("  	u.segundoApellido, ");
		sql.append("  	c.id AS idCiudadResidencia, ");
		sql.append("  	c.nombre AS nombreCiudadResidencia, ");
		sql.append("  	d.id AS idDepartamentoCiudadResidencia, ");
		sql.append("  	d.nombre AS nombreDepartamentoCiudadResidencia, ");
		sql.append("  	p.id AS idPaisDepartamentoCiudadResidencia, ");
		sql.append("  	p.nombre AS nombrePaisDepartamentoCiudadResidencia, ");
		sql.append("  	u.correoElectronico, ");
		sql.append("  	u.numeroTelefonoMovil, ");
		sql.append("  	u.correoElectronicoConfirmado, ");
		sql.append("  	u.numeroTelefonoMovilConfirmado ");
		sql.append("FROM Usuario AS u ");
		sql.append("INNER JOIN TipoIdentificacion AS ti ");
		sql.append("ON 	u.tipoIdentificacion = ti.id ");
		sql.append("INNER JOIN Ciudad AS c ");
		sql.append("ON 	u.ciudadResidencia = c.id ");
		sql.append("INNER JOIN Departamento AS d ");
		sql.append("ON 	c.departamento = d.id ");
		sql.append("INNER JOIN Pais AS p ");
		sql.append("ON 	d.pais = p.id; ");
		sql.append("WHERE u.id = ?; ");
		
        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
        	
        	preparedStatement.setObject(1,id);
        	
        	try (var resultSet= preparedStatement.executeQuery()){
        		if (resultSet.next()) {
        			var idType = new IdTypeEntity();
        			idType.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTipoIdentificacion")));
        			idType.setName(resultSet.getString("nombreTipoIdentificacion"));
        			
        			
        			var country = new CountryEntity();
        			country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPaisDepartamentoCiudadResidencia")));
        			country.setName(resultSet.getString("nombrePaisDepartamentoCiudadResidencia"));
        			
        			
        			var state = new StateEntity();
        			state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamentoCiudadResidencia")));
        			state.setName(resultSet.getString("nombreDepartamentoCiudadResidencia"));
        			
        			
        			var city = new CityEntity();
        			city.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudadResidencia")));
        			city.setName(resultSet.getString("nombreCiudadResidencia"));
        			
        			user.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
        			user.setIdType(idType);
        			user.setFirstName(resultSet.getString("primerNombre"));
        			user.setSecondName(resultSet.getString("segundoNombre"));
        			user.setFirstLastName(resultSet.getString("primerApellido"));
        			user.setSecondLastName(resultSet.getString("segundoApellido"));
        			user.setResidenceCity(city);
        			user.setEmail(resultSet.getString("correoElectronico"));
        			user.setPhoneNumber(resultSet.getString("numeroTelefonoMovil"));
        			user.setEmailConfirmed(resultSet.getBoolean("correoElectronicoConfirmado"));
        			user.setMobileNumberConfirmed(resultSet.getBoolean("numeroTelefonoMovilConfirmado"));
        		} 
        		
        		
        	}catch (final SQLException exception) {
                var userMessage = "Se ha presentado un error al tratar de consultar el usuario";
                var technicalMessage = "se ha presentado un error al intentar consultar el usuario en la base de datos. Por favor verifique la traza completa del error";
                throw NoseException.create(exception, userMessage, technicalMessage);
            } catch (final Exception exception) {
                var userMessage = "No se pudo actualizar el usuario";
                var technicalMessage = "Se ha presentado un error inesperado al intentar actualizar el usuario en la base de datos. Por favor verifique la traza completa del error";
                throw NoseException.create(exception, userMessage, technicalMessage);
            } catch (final Throwable exception) {
                var userMessage = "No se pudo actualizar el usuario";
                var technicalMessage = "Se ha presentado un error critico al intentar actualizar el usuario en la base de datos. Por favor verifique la traza completa del error";
                throw NoseException.create(exception, userMessage, technicalMessage);
            }
        	return user;
        } catch (final SQLException exception) {
			var userMessage = "Se ha presentado un error al tratar de consultar el usuario";
			var technicalMessage = "se ha presentado un error al intentar consultar el usuario en la base de datos. Por favor verifique la traza completa del error";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "No se pudo consultar el usuario";
			var technicalMessage = "Se ha presentado un error inesperado al intentar actualizar el usuario en la base de datos. Por favor verifique la traza completa del error";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Throwable exception) {
			var userMessage = "No se pudo actualizar el usuario";
			var technicalMessage = "Se ha presentado un error critico al intentar actualizar el usuario en la base de datos. Por favor verifique la traza completa del error";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
        
	}

	@Override
	public void update(UserEntity entity) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
        final var sql = new StringBuilder();
        sql.append("UPDATE User SET idType = ?, phoneNumber = ?, firstName = ?, secondName = ?, firstLastName = ?, secondLastName = ?, residenceCity = ?, email = ?, phoneNumber = ?, emailConfirmed = ?, mobileNumberConfirmed = ? WHERE id = ?");
        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, entity.getIdType().getId());
            preparedStatement.setString(2, entity.getPhoneNumber());
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getSecondName());
            preparedStatement.setString(5, entity.getFirstLastName());
            preparedStatement.setString(6, entity.getSecondLastName());
            preparedStatement.setObject(7, entity.getResidenceCity().getId());
            preparedStatement.setString(8, entity.getEmail());
            preparedStatement.setString(9, entity.getPhoneNumber());
            preparedStatement.setBoolean(10, entity.isEmailConfirmed());
            preparedStatement.setBoolean(11, entity.isMobileNumberConfirmed());
            preparedStatement.setObject(12, entity.getId());
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = "No se pudo actualizar el usuario";
            var technicalMessage = "Se ha presentado un error al intentar actualizar el usuario en la base de datos. Por favor verifique la traza completa del error";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "No se pudo actualizar el usuario";
            var technicalMessage = "Se ha presentado un error inesperado al intentar actualizar el usuario en la base de datos. Por favor verifique la traza completa del error";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Throwable exception) {
            var userMessage = "No se pudo actualizar el usuario";
            var technicalMessage = "Se ha presentado un error critico al intentar actualizar el usuario en la base de datos. Por favor verifique la traza completa del error";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
	}

	@Override
	public void delete(UUID id) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
        final var sql = new StringBuilder();
        sql.append("DELETE FROM User WHERE id = ?");
        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = "No se pudo eliminar el usuario";
            var technicalMessage = "Se ha presentado un error al intentar eliminar el usuario en la base de datos. Por favor verifique la traza completa del error";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "No se pudo eliminar el usuario";
            var technicalMessage = "Se ha presentado un error inesperado al intentar eliminar el usuario en la base de datos. Por favor verifique la traza completa del error";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Throwable exception) {
            var userMessage = "No se pudo eliminar el usuario";
            var technicalMessage = "Se ha presentado un error critico al intentar eliminar el usuario en la base de datos. Por favor verifique la traza completa del error";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }
	
}