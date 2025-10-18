package co.edu.uco.nose.buisness.facade.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.buisness.assembler.dto.impl.UserDTOAssembler;
import co.edu.uco.nose.buisness.buisness.impl.UserBuisnessImpl;
import co.edu.uco.nose.buisness.domain.UserDomain;
import co.edu.uco.nose.buisness.facade.UserFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.UserDTO;

public class UserFacadeImpl implements UserFacade {

	
	
	@Override
	public void registerNewUserInformation(final UserDTO userDto) {
		var daoFactory= DAOFactory.getFactory();
		var business = new UserBuisnessImpl(daoFactory);
		
		try {
			
			var domain = UserDTOAssembler.getUserDTOAssembler().toDomain(userDto);
			daoFactory.initTransaction();
			
			daoFactory.commitTransaction();
			
		} catch (final NoseException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var userMessage= "";
			var technicalMessage="";
			throw NoseException.create(exception, userMessage, technicalMessage);
			
		} finally {
			daoFactory.closeConnection();
		}
		

		
	}

	@Override
	public void dropUserInformation(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uptadeUserInformation(UUID id, UserDTO userDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserDTO> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> findUsersByFilter(UserDTO userFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO findUserById(UUID id) {
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
