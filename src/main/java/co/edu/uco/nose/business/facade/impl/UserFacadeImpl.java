package co.edu.uco.nose.business.facade.impl;

import co.edu.uco.nose.business.assembler.dto.impl.UserDTOAssembler;
import co.edu.uco.nose.business.business.impl.UserBusinessImpl;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.business.facade.UserFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.facade.MessagesEnumUserFacade;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public final class UserFacadeImpl implements UserFacade {

    @Override
    public void registerNewUserInformation(final UserDTO userDTO) {

        var daoFactory = DAOFactory.getFactory();
        var business = new UserBusinessImpl(daoFactory);

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

            var userMessage = MessagesEnumUserFacade.REGISTER_USER_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumUserFacade.REGISTER_USER_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void dropUserInformation(UUID id) {

        var daoFactory = DAOFactory.getFactory();
        var business = new UserBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            business.dropUserInformation(id);

            daoFactory.commitTransaction();

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumUserFacade.DROP_USER_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumUserFacade.DROP_USER_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void updateUserInformation(UUID id, UserDTO userDomain) {

        var daoFactory = DAOFactory.getFactory();
        var business = new UserBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            var domain = UserDTOAssembler.getUserDTOAssembler().toDomain(userDomain);
            business.updateUserInformation(id, domain);

            daoFactory.commitTransaction();

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumUserFacade.UPDATE_USER_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumUserFacade.UPDATE_USER_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<UserDTO> findAllUsers() {

        var daoFactory = DAOFactory.getFactory();
        var business = new UserBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            List<UserDomain> domainList = business.findAllUsers();

            return UserDTOAssembler.getUserDTOAssembler().toDTO(domainList);

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumUserFacade.FIND_ALL_USERS_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumUserFacade.FIND_ALL_USERS_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<UserDTO> findUsersByFilter(UserDTO userDTO) {

        var daoFactory = DAOFactory.getFactory();
        var business = new UserBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            var domainFilter = UserDTOAssembler.getUserDTOAssembler().toDomain(userDTO);

            List<UserDomain> domainList = business.findUsersByFilter(domainFilter);

            return UserDTOAssembler.getUserDTOAssembler().toDTO(domainList);

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumUserFacade.FIND_USERS_BY_FILTER_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumUserFacade.FIND_USERS_BY_FILTER_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public UserDTO findSpecificUser(UUID id) {

        var daoFactory = DAOFactory.getFactory();
        var business = new UserBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            var domain = business.findSpecificUser(id);

            return UserDTOAssembler.getUserDTOAssembler().toDTO(domain);

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumUserFacade.FIND_SPECIFIC_USER_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumUserFacade.FIND_SPECIFIC_USER_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void confirmMobileNumber(UUID id, int confirmationCode) {

    }

    @Override
    public void confirmEmail(UUID id) {

    }

    @Override
    public void sendMobileNumberConfirmation(UUID id) {

    }

    @Override
    public void sendEmailConfirmation(UUID id) {

    }
}