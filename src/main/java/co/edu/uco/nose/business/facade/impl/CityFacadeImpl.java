package co.edu.uco.nose.business.facade.impl;

import co.edu.uco.nose.business.assembler.dto.impl.CityDTOAssembler;
import co.edu.uco.nose.business.business.impl.CityBusinessImpl;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.business.facade.CityFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.facade.MessagesEnumCityFacade;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.CityDTO;

import java.util.List;
import java.util.UUID;

public final class CityFacadeImpl implements CityFacade {

    @Override
    public List<CityDTO> findAllCities() {
        var daoFactory = DAOFactory.getFactory();
        var business = new CityBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();
            List<CityDomain> domainList = business.findAllCities();
            return CityDTOAssembler.getCityDTOAssembler().toDTO(domainList);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumCityFacade.FIND_ALL_CITIES_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumCityFacade.FIND_ALL_CITIES_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();
            throw  NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<CityDTO> findCitiesByFilter(CityDTO cityFilters) {
        var daoFactory = DAOFactory.getFactory();
        var business = new CityBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();
            var filterDomain = CityDTOAssembler.getCityDTOAssembler().toDomain(cityFilters);
            List<CityDomain> domainList = business.findCitiesByFilter(filterDomain);
            return CityDTOAssembler.getCityDTOAssembler().toDTO(domainList);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumCityFacade.FIND_CITIES_BY_FILTER_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumCityFacade.FIND_CITIES_BY_FILTER_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public CityDTO findSpecificCity(UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new CityBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();
            CityDomain domain = business.findSpecificCity(id);
            return CityDTOAssembler.getCityDTOAssembler().toDTO(domain);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumCityFacade.FIND_SPECIFIC_CITY_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumCityFacade.FIND_SPECIFIC_CITY_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }
}