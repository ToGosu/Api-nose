package co.edu.uco.nose.business.assembler.entity.impl;

import java.util.List;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.entity.CityEntity;

public class CityEntityAssembler implements EntityAssembler<CityEntity, CityDomain> {
/**
	@Override
	public CityEntity toEntity(CityDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityDomain toDomain(CityEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CityEntity> toDTO(List<CityDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}**/
	
    private static final EntityAssembler<CityEntity, CityDomain> instance = new CityEntityAssembler();

    private CityEntityAssembler() {
        super();
    }

    public static EntityAssembler<CityEntity, CityDomain> getCityEntityAssembler() {
        return instance;
    }

    @Override
    public CityEntity toEntity(final CityDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new CityDomain());
        var departmentEntity = StateEntityAssembler.getStateEntityAssembler().toEntity(domainTmp.getState());
        return new CityEntity(domainTmp.getId(), departmentEntity, domainTmp.getName());
    }

    @Override
    public CityDomain toDomain(final CityEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new CityEntity());
        var departmentDomain = StateEntityAssembler.getStateEntityAssembler().toDomain(entityTmp.getState());
        return new CityDomain(entityTmp.getId(), departmentDomain, entityTmp.getName());
    }

    @Override
    public List<CityEntity> toDTO(List<CityDomain> domainList) {
        return List.of();
    }

}
