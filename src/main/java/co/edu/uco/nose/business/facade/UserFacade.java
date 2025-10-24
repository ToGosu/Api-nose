package co.edu.uco.nose.business.facade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.dto.UserDTO;

public interface UserFacade {
	
	void registerNewUserInformation(UserDTO userDto);
	
	void dropUserInformation(UUID id);
	
	void uptadeUserInformation(UUID id, UserDTO userDto);
	
	List<UserDTO> findAllUsers();
	
	List<UserDTO> findUsersByFilter(UserDTO userFilters);
	
	UserDTO findUserById(UUID id);
	
	void confirmMobileNumber(UUID id, int confirmationCode);
	
	void confirmEmail (UUID id, int confirmationCode);
	
	void sendMobileNumberConfirmation(UUID id);
	
	void sendEmailConfirmation(UUID id);
	

}
