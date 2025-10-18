package co.edu.uco.nose.buisness.buisness;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.buisness.domain.UserDomain;

public interface UserBuisness {

	void registerNewUserInformation(UserDomain userDomain);
	
	void dropUserInformation(UUID id);
	
	void uptadeUserInformation(UUID id, UserDomain userDomain);
	
	List<UserDomain> findAllUsers();
	
	List<UserDomain> findUsersByFilter(UserDomain userFilters);
	
	UserDomain findUserById(UUID id);
	
	void confirmMobileNumber(UUID id, int confirmationCode);
	
	void confirmEmail (UUID id, int confirmationCode);
	
	void sendMobileNumberConfirmation(UUID id);
	
	void sendEmailConfirmation(UUID id);
	
	
}

