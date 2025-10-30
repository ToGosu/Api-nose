package co.edu.uco.nose.business.business.impl;

import co.edu.uco.nose.business.assembler.entity.impl.CountryEntityAssembler;
import co.edu.uco.nose.business.business.CountryBusiness;
import co.edu.uco.nose.business.domain.CountryDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.CountryEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class CountryBusinessImpl implements CountryBusiness {

    private final DAOFactory daoFactory;

    public CountryBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<CountryDomain> findAllCountries() {
        try {

            List<CountryEntity> entityList = daoFactory.getCountryDAO().findAll();

            List<CountryDomain> domainList = new ArrayList<>();

            for (CountryEntity entity : entityList) {
                domainList.add(CountryEntityAssembler.getCountryEntityAssembler().toDomain(entity));
            }

            return domainList;

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {

            throw NoseException.create(exception, "Error inesperado al consultar todos los países.",
                    "Capa de Negocio");
        }
    }

    @Override
    public List<CountryDomain> findCountriesByFilter(final CountryDomain countryFilters) {
        try {

            CountryEntity filterEntity = CountryEntityAssembler.getCountryEntityAssembler().toEntity(countryFilters);

            List<CountryEntity> entityList = daoFactory.getCountryDAO().findByFilter(filterEntity);

            List<CountryDomain> domainList = new ArrayList<>();

            for (CountryEntity entity : entityList) {
                domainList.add(CountryEntityAssembler.getCountryEntityAssembler().toDomain(entity));
            }
            return domainList;

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {

            throw NoseException.create(exception, "Error inesperado al consultar los países por filtro.",
                    "Capa de Negocio");
        }
    }

    @Override
    public CountryDomain findSpecificCountry(final UUID id) {
        try {

            CountryEntity entity = daoFactory.getCountryDAO().findById(id);

            if (entity == null) {
                throw NoseException.create("El país con el ID " + id + " no fue encontrado.", "Capa de Negocio");
            }

            return CountryEntityAssembler.getCountryEntityAssembler().toDomain(entity);

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {

            throw NoseException.create(exception, "Error inesperado al buscar un país específico.", "Capa de Negocio");
        }
    }
}