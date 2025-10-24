package co.edu.uco.nose.business.facade.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.assembler.dto.impl.UserDTOAssembler;
import co.edu.uco.nose.business.buisness.impl.UserBuisnessImpl;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.business.facade.UserFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.UserDTO;

public class UserFacadeImpl implements UserFacade {

	
	
    @Override
    public void registerNewUserInformation(final UserDTO userDTO) {

        var daoFactory = DAOFactory.getFactory();
        var business = new UserBuisnessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            var domain = UserDTOAssembler.getUserDTOAssembler().toDomain(userDTO);
            business.registerNewUserInformation(domain);

            daoFactory.commitTransaction();

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = "Error al registrar la información del nuevo usuario. Por favor contacte al administrador del sistema.";
            var technicalMessage = "Se ha presentado un error inesperado al registrar la información del nuevo usuario" +
                    ". Por favor revise la traza completa del error para mayor detalle: " + exception.getMessage();;

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }

    }

    @Override
    public void dropUserInformation(UUID id) {

    }

    @Override
    public void uptadeUserInformation(UUID id, UserDTO userDomain) {

    }

    @Override
    public List<UserDTO> findAllUsers() {
        return List.of();
    }

    @Override
    public List<UserDTO> findUsersByFilter(UserDTO userDTO) {
        return List.of();
    }

    @Override
    public UserDTO findUserById(UUID id) {
        return null;
    }

    @Override
    public void confirmMobileNumber(UUID id, int confirmationCode) {

    }

    @Override
    public void confirmEmail(UUID id, int confirmationCode) {

    }

    @Override
    public void sendMobileNumberConfirmation(UUID id) {

    }

    @Override
    public void sendEmailConfirmation(UUID id) {

    }

}
