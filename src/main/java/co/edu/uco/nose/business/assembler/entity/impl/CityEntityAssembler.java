package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.entity.CityEntity;

import java.util.ArrayList;
import java.util.List;


public final class CityEntityAssembler implements EntityAssembler<CityEntity, CityDomain> {

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
    public List<CityEntity> toEntity(List<CityDomain> domainList) {

        var domainListTmp = ObjectHelper.getDefault(domainList, new ArrayList<CityDomain>());
        var entityList = new ArrayList<CityEntity>();

        for (var domain : domainListTmp) {
            entityList.add(toEntity(domain));
        }

        return entityList;
        }
    }