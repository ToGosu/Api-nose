package co.edu.uco.nose.business.business.impl;

import co.edu.uco.nose.business.assembler.entity.impl.CityEntityAssembler;
import co.edu.uco.nose.business.business.CityBusiness;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.CityEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class CityBusinessImpl implements CityBusiness {

    private final DAOFactory daoFactory;

    public CityBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<CityDomain> findAllCities() {
        try {

            List<CityEntity> entityList = daoFactory.getCityDAO().findAll();

            List<CityDomain> domainList = new ArrayList<>();

            for (CityEntity entity : entityList) {
                domainList.add(CityEntityAssembler.getCityEntityAssembler().toDomain(entity));
            }
            return domainList;

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {

            throw NoseException.create(exception, "Error inesperado al consultar todas las ciudades.",
                    "Capa de Negocio");
        }
    }

    @Override
    public List<CityDomain> findCitiesByFilter(final CityDomain cityFilters) {
        try {

            CityEntity filterEntity = CityEntityAssembler.getCityEntityAssembler().toEntity(cityFilters);

            List<CityEntity> entityList = daoFactory.getCityDAO().findByFilter(filterEntity);

            List<CityDomain> domainList = new ArrayList<>();

            for (CityEntity entity : entityList) {
                domainList.add(CityEntityAssembler.getCityEntityAssembler().toDomain(entity));
            }
            return domainList;

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {

            throw NoseException.create(exception, "Error inesperado al consultar las ciudades por filtro.",
                    "Capa de Negocio");
        }
    }

    @Override
    public CityDomain findSpecificCity(final UUID id) {
        try {

            CityEntity entity = daoFactory.getCityDAO().findById(id);

            if (entity == null) {
                throw NoseException.create("La ciudad con el ID " + id + " no fue encontrada.", "Capa de Negocio");
            }
            return CityEntityAssembler.getCityEntityAssembler().toDomain(entity);

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {

            throw NoseException.create(exception, "Error inesperado al buscar una ciudad específica.", "Capa de Negocio");
        }
    }
}