package co.edu.uco.nose.business.assembler.entity.impl;

import java.util.List;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.CountryDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.entity.CountryEntity;

public class CountryEntityAssembler implements EntityAssembler<CountryEntity, CountryDomain> {
/**
	@Override
	public CountryEntity toEntity(CountryDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountryDomain toDomain(CountryEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CountryEntity> toDTO(List<CountryDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}**/
	
    private static final EntityAssembler<CountryEntity,CountryDomain> instance =
            new CountryEntityAssembler();

    private CountryEntityAssembler() {

    }
    public static EntityAssembler<CountryEntity,CountryDomain> getCountryEntityAssembler() {
        return instance;
    }

    @Override
    public CountryEntity toEntity(final CountryDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new CountryDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new CountryEntity(domainTmp.getId(), domainTmp.getName());
    }

    @Override
    public CountryDomain toDomain(final CountryEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new CountryEntity());
        return new CountryDomain(entityTmp.getId(), entityTmp.getName());
    }

    @Override
    public List<CountryEntity> toDTO(List<CountryDomain> domainList) {
        return List.of();
    }

}
