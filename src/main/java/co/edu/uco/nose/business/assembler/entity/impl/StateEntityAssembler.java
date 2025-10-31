package co.edu.uco.nose.business.assembler.entity.impl;

import static co.edu.uco.nose.business.assembler.entity.impl.CountryEntityAssembler.getCountryEntityAssembler;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.StateDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.entity.StateEntity;

import java.util.ArrayList;
import java.util.List;

public final class StateEntityAssembler implements EntityAssembler<StateEntity, StateDomain> {

    private static final EntityAssembler<StateEntity, StateDomain> instance =
            new StateEntityAssembler();

    private StateEntityAssembler() {

        super();
    }

    public static EntityAssembler<StateEntity, StateDomain> getStateEntityAssembler() {
        return instance;
    }

    @Override
    public StateEntity toEntity(final StateDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new StateDomain());
        var countryEntityTmp = getCountryEntityAssembler().toEntity(domainTmp.getCountry());
        return new StateEntity(domainTmp.getId(), countryEntityTmp, domainTmp.getName());
    }

    @Override
    public StateDomain toDomain(final StateEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new StateEntity());
        var countryDomainTmp = getCountryEntityAssembler().toDomain(entityTmp.getCountry());
        return new StateDomain(entityTmp.getId(), countryDomainTmp, entityTmp.getName());
    }

    @Override
    public List<StateEntity> toEntity(List<StateDomain> domainList) {
        var domainListTmp = ObjectHelper.getDefault(domainList, new ArrayList<StateDomain>());
        var entityList = new ArrayList<StateEntity>();

        for (var domain : domainListTmp) {
            entityList.add(toEntity(domain));
        }
        return entityList;
    }



}