package co.edu.uco.nose.business.business.impl;

import co.edu.uco.nose.business.assembler.entity.impl.IdTypeEntityAssembler;
import co.edu.uco.nose.business.business.IdTypeBusiness;
import co.edu.uco.nose.business.domain.IdTypeDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.IdTypeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class IdTypeBusinessImpl implements IdTypeBusiness {

    private final DAOFactory daoFactory;

    public IdTypeBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<IdTypeDomain> findAllIdTypes() {
        try {

            List<IdTypeEntity> entityList = daoFactory.getIdTypeDAO().findAll();

            List<IdTypeDomain> domainList = new ArrayList<>();

            for (IdTypeEntity entity : entityList) {
                domainList.add(IdTypeEntityAssembler.getIdTypeEntityAssembler().toDomain(entity));
            }

            return domainList;

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            // 4. Manejo de excepciones
            throw NoseException.create(exception, "Error inesperado al consultar todos los tipos de identificación.",
                    "Capa de Negocio");
        }
    }

    @Override
    public List<IdTypeDomain> findIdTypesByFilter(final IdTypeDomain idTypeFilters) {
        try {

            IdTypeEntity filterEntity = IdTypeEntityAssembler.getIdTypeEntityAssembler().toEntity(idTypeFilters);

            List<IdTypeEntity> entityList = daoFactory.getIdTypeDAO().findByFilter(filterEntity);

            List<IdTypeDomain> domainList = new ArrayList<>();

            for (IdTypeEntity entity : entityList) {
                domainList.add(IdTypeEntityAssembler.getIdTypeEntityAssembler().toDomain(entity));
            }

            return domainList;

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {

            throw NoseException.create(exception, "Error inesperado al consultar los tipos de identificación por filtro.",
                    "Capa de Negocio");
        }
    }

    @Override
    public IdTypeDomain findSpecificIdType(final UUID id) {
        try {

            IdTypeEntity entity = daoFactory.getIdTypeDAO().findById(id);

            if (entity == null) {
                throw NoseException.create("El tipo de identificación con el ID " + id + " no fue encontrado.", "Capa de Negocio");
            }

            return IdTypeEntityAssembler.getIdTypeEntityAssembler().toDomain(entity);

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {

            throw NoseException.create(exception, "Error inesperado al buscar un tipo de identificación específico.", "Capa de Negocio");
        }

    }
}