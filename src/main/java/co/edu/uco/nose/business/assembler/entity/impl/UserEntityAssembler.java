package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.entity.UserEntity;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;

import java.util.List;

import static co.edu.uco.nose.business.assembler.entity.impl.IdTypeEntityAssembler.getIdTypeEntityAssembler;
import static co.edu.uco.nose.business.assembler.entity.impl.CityEntityAssembler.getCityEntityAssembler;


public final class UserEntityAssembler implements EntityAssembler<UserEntity, UserDomain> {

    private static final EntityAssembler<UserEntity, UserDomain> instance = new UserEntityAssembler();

    private UserEntityAssembler() {
        super();
    }

    public static EntityAssembler<UserEntity, UserDomain> getUserEntityAssembler() {
        return instance;
    }

    @Override
    public UserEntity toEntity(final UserDomain domain) {

        var domainTmp = ObjectHelper.getDefault(domain, new UserDomain());

        var idTypeEntity = getIdTypeEntityAssembler().toEntity(domainTmp.getIdType());
        var cityEntity = getCityEntityAssembler().toEntity(domainTmp.getHomeCity());

        var entity = new UserEntity();
        entity.setId(domainTmp.getId());
        entity.setIdType(idTypeEntity);
        entity.setIdNumber(domainTmp.getIdNumber());
        entity.setFirstName(domainTmp.getFirstName());
        entity.setSecondName(domainTmp.getSecondName());
        entity.setFirstSurname(domainTmp.getFirstSurname());
        entity.setSecondSurname(domainTmp.getSecondSurname());
        entity.setHomeCity(cityEntity);
        entity.setEmail(domainTmp.getEmail());
        entity.setMobileNumber(domainTmp.getMobileNumber());

        entity.setEmailConfirmed(domainTmp.isEmailConfirmed());
        entity.setMobileNumberConfirmed(domainTmp.isMobileNumberConfirmed());

        return entity;
    }

    @Override
    public UserDomain toDomain(final UserEntity entity) {

        var entityTmp = ObjectHelper.getDefault(entity, new UserEntity());

        var idTypeDomain = getIdTypeEntityAssembler().toDomain(entityTmp.getIdType());
        var cityDomain = getCityEntityAssembler().toDomain(entityTmp.getHomeCity());

        return new UserDomain(
                entityTmp.getId(),
                idTypeDomain,
                entityTmp.getIdNumber(),
                entityTmp.getFirstName(),
                entityTmp.getSecondName(),
                entityTmp.getFirstSurname(),
                entityTmp.getSecondSurname(),
                cityDomain,
                entityTmp.getEmail(),
                entityTmp.getMobileNumber(),
                entityTmp.isEmailConfirmed(),
                entityTmp.isMobileNumberConfirmed()
        );
    }

    @Override
    public List<UserEntity> toDTO(List<UserDomain> domainList) {
        return List.of();
    }
}