package co.edu.uco.nose.business.business;

import co.edu.uco.nose.business.domain.UserDomain;

import java.util.List;
import java.util.UUID;

public interface UserBusiness {

    void registerNewUserInformation (UserDomain userDomain);

    void dropUserInformation (UUID id);

    void updateUserInformation (UUID id, UserDomain userDomain);

    List<UserDomain> findAllUsers ();

    List <UserDomain> findUsersByFilter (UserDomain userFilters);

    UserDomain findSpecificUser(UUID id);

    void confirmMobileNumber(UUID id, int confirmationCode);

    void confirmEmail (UUID id);

    void sendMobileNumberConfirmation (UUID id);

    void sendEmailConfirmation (UUID id);
}