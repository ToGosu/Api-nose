package co.edu.uco.nose.business.assembler.entity.impl;

import java.util.List;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.IdTypeDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.entity.IdTypeEntity;

public class IdTypeEntityAssembler implements EntityAssembler<IdTypeEntity, IdTypeDomain> {
/**
	@Override
	public IdTypeEntity toEntity(IdTypeDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdTypeDomain toDomain(IdTypeEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IdTypeEntity> toDTO(List<IdTypeDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}**/

    private static final EntityAssembler<IdTypeEntity, IdTypeDomain> instance = new IdTypeEntityAssembler();

    private IdTypeEntityAssembler() {
        super();
    }

    public static EntityAssembler<IdTypeEntity, IdTypeDomain> getIdTypeEntityAssembler() {
        return instance;
    }

    @Override
    public IdTypeEntity toEntity(final IdTypeDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new IdTypeDomain());
        return new IdTypeEntity(domainTmp.getId(), domainTmp.getName());
    }

    @Override
    public IdTypeDomain toDomain(final IdTypeEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new IdTypeEntity());
        return new IdTypeDomain(entityTmp.getId(), entityTmp.getName());
    }

    @Override
    public List<IdTypeEntity> toDTO(List<IdTypeDomain> domainList) {
        return List.of();
    }
}
