package co.edu.uco.nose.business.business.impl;

import co.edu.uco.nose.business.assembler.entity.impl.StateEntityAssembler;
import co.edu.uco.nose.business.business.StateBusiness;
import co.edu.uco.nose.business.domain.StateDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.StateEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class StateBusinessImpl implements StateBusiness {

    private final DAOFactory daoFactory;

    public StateBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<StateDomain> findAllStates() {

        try {
            List<StateEntity> entityList = daoFactory.getStateDAO().findAll();

            List<StateDomain> domainList = new ArrayList<>();

            for (StateEntity entity : entityList) {
                domainList.add(StateEntityAssembler.getStateEntityAssembler().toDomain(entity));
            }

            return domainList;

        } catch (final NoseException exception) {
            throw exception;

        } catch (final Exception exception) {

            throw NoseException.create(exception, "Error inesperado al consultar todos los departamento.",
                    "Capa de Negocio");
        }
    }

    @Override
    public List<StateDomain> findStatesByFilter(final StateDomain stateFilters) {
        try {

            StateEntity filterEntity = StateEntityAssembler.getStateEntityAssembler().toEntity(stateFilters);

            List<StateEntity> entityList = daoFactory.getStateDAO().findByFilter(filterEntity);

            List<StateDomain> domainList = new ArrayList<>();

            for (StateEntity entity : entityList) {
                domainList.add(StateEntityAssembler.getStateEntityAssembler().toDomain(entity));
            }

            return domainList;

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw NoseException.create(exception, "Error inesperado al consultar los departamento por filtro.",
                    "Capa de Negocio");
        }
    }

    @Override
    public StateDomain findSpecificState(final UUID id) {
        try {

            StateEntity entity = daoFactory.getStateDAO().findById(id);

            if (entity == null) {
                throw NoseException.create("El estado con el ID " + id + " no fue encontrado.", "Capa de Negocio");
            }

            return StateEntityAssembler.getStateEntityAssembler().toDomain(entity);

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw NoseException.create(exception, "Error inesperado al buscar un estado específico.", "Capa de Negocio");
        }
    }
}
