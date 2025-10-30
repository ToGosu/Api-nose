package co.edu.uco.nose.business.facade.impl;

import co.edu.uco.nose.business.assembler.dto.impl.IdTypeDTOAssembler;
import co.edu.uco.nose.business.business.impl.IdTypeBusinessImpl;
import co.edu.uco.nose.business.domain.IdTypeDomain;
import co.edu.uco.nose.business.facade.IdTypeFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.facade.MessagesEnumIdTypeFacade;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.IdTypeDTO;

import java.util.List;
import java.util.UUID;

public final class IdTypeFacadeImpl implements IdTypeFacade {

    @Override
    public List<IdTypeDTO> findAllIdTypes() {

        var daoFactory = DAOFactory.getFactory();
        var business = new IdTypeBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            List<IdTypeDomain> domainList = business.findAllIdTypes();

            return IdTypeDTOAssembler.getIdTypeDTOAssembler().toDTO(domainList);

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumIdTypeFacade.FIND_ALL_IDTYPES_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumIdTypeFacade.FIND_ALL_IDTYPES_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<IdTypeDTO> findIdTypesByFilter(IdTypeDTO idTypeFilters) {

        var daoFactory = DAOFactory.getFactory();
        var business = new IdTypeBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            var filterDomain = IdTypeDTOAssembler.getIdTypeDTOAssembler().toDomain(idTypeFilters);

            List<IdTypeDomain> domainList = business.findIdTypesByFilter(filterDomain);

            return IdTypeDTOAssembler.getIdTypeDTOAssembler().toDTO(domainList);

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumIdTypeFacade.FIND_IDTYPES_BY_FILTER_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumIdTypeFacade.FIND_IDTYPES_BY_FILTER_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public IdTypeDTO findSpecificIdType(UUID id) {

        var daoFactory = DAOFactory.getFactory();
        var business = new IdTypeBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            IdTypeDomain domain = business.findSpecificIdType(id);

            return IdTypeDTOAssembler.getIdTypeDTOAssembler().toDTO(domain);

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumIdTypeFacade.FIND_SPECIFIC_IDTYPE_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumIdTypeFacade.FIND_SPECIFIC_IDTYPE_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }
}