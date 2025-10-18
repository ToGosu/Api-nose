package co.edu.uco.nose.buisness.buisness.impl;

import java.util.List;
import java.util.UUID;
import co.edu.uco.nose.crosscuting.helper.*;

import co.edu.uco.nose.buisness.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.buisness.buisness.UserBuisness;
import co.edu.uco.nose.buisness.domain.UserDomain;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

public final class UserBuisnessImpl implements UserBuisness {

	private DAOFactory daoFactory;
	
	public UserBuisnessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	//1. validar que la informacion sea consistente de tipo de dato, longitud, obligatoriedad, formato, rango, reglas propias del objeto
	//2. validar que no exista previamente otro usuario con el mismo tipo y numero de identificacion
	//3. validar que no exista previamente otro usuario con el mismo corre electronico
	//4. validar que no exista previamente otro usuario con el mismo numero de telefono celular
	//5. generar un identificador para el nuevo usuario asegurando que no exista
	//6. registrar la informacion del nuevo usuario
	
	@Override
	public void registerNewUserInformation(UserDomain userDomain) {
		var id = UUIDHelper.getUUIDHelper().generateNewUUID();
		var userEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userDomain);
		
		userEntity.setId(id);
		
		daoFactory.getUserDAO().create(userEntity);

	}

	@Override
	public void dropUserInformation(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uptadeUserInformation(UUID id, UserDomain userDomain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserDomain> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDomain> findUsersByFilter(UserDomain userFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDomain findUserById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void confirmMobileNumber(UUID id, int confirmationCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmEmail(UUID id, int confirmationCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMobileNumberConfirmation(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailConfirmation(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
