package co.edu.uco.nose.business.facade.impl;

import co.edu.uco.nose.business.assembler.dto.impl.CountryDTOAssembler;
import co.edu.uco.nose.business.business.impl.CountryBusinessImpl;
import co.edu.uco.nose.business.domain.CountryDomain;
import co.edu.uco.nose.business.facade.CountryFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.facade.MessagesEnumCountryFacade;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.CountryDTO;

import java.util.List;
import java.util.UUID;

public final class CountryFacadeImpl implements CountryFacade {

    @Override
    public List<CountryDTO> findAllCountries() {

        var daoFactory = DAOFactory.getFactory();
        var business = new CountryBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            List<CountryDomain> domainList = business.findAllCountries();

            return CountryDTOAssembler.getCountryDTOAssembler().toDTO(domainList);

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumCountryFacade.FIND_ALL_COUNTRIES_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumCountryFacade.FIND_ALL_COUNTRIES_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<CountryDTO> findCountriesByFilter(CountryDTO countryFilters) {

        var daoFactory = DAOFactory.getFactory();
        var business = new CountryBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            var filterDomain = CountryDTOAssembler.getCountryDTOAssembler().toDomain(countryFilters);

            List<CountryDomain> domainList = business.findCountriesByFilter(filterDomain);

            return CountryDTOAssembler.getCountryDTOAssembler().toDTO(domainList);

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumCountryFacade.FIND_COUNTRIES_BY_FILTER_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumCountryFacade.FIND_COUNTRIES_BY_FILTER_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public CountryDTO findSpecificCountry(UUID id) {

        var daoFactory = DAOFactory.getFactory();
        var business = new CountryBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            CountryDomain domain = business.findSpecificCountry(id);

            return CountryDTOAssembler.getCountryDTOAssembler().toDTO(domain);

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnumCountryFacade.FIND_SPECIFIC_COUNTRY_UNEXPECTED_ERROR.getTitle();
            var technicalMessage = MessagesEnumCountryFacade.FIND_SPECIFIC_COUNTRY_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }
}