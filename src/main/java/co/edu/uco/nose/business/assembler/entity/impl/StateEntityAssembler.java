package co.edu.uco.nose.business.assembler.entity.impl;

import java.util.List;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.StateDomain;
import co.edu.uco.nose.entity.StateEntity;

public class StateEntityAssembler implements EntityAssembler<StateEntity, StateDomain> {
/**
	@Override
	public StateEntity toEntity(StateDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateDomain toDomain(StateEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StateEntity> toDTO(List<StateDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}
**/
	
    private static final EntityAssembler<StateEntity, StateDomain> instance =
            new StateEntityAssembler();

    private StateEntityAssembler() {

    }

    public static EntityAssembler<StateEntity, StateDomain> getStateEntityAssembler() {
        return instance;
    }
    @Override
    public StateEntity toEntity(final StateDomain domain) {
        return null;
    }

    @Override
    public StateDomain toDomain(final StateEntity entity) {
        return null;
    }

    @Override
    public List<StateEntity> toDTO(List<StateDomain> domainList) {
        return List.of();
    }
}
