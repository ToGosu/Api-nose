package co.edu.uco.nose.business.assembler.dto.impl;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.dto.UserDTO;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;

import static co.edu.uco.nose.business.assembler.dto.impl.IdTypeDTOAssembler.getIdTypeDTOAssembler;
import static co.edu.uco.nose.business.assembler.dto.impl.CityDTOAssembler.getCityDTOAssembler;

import java.util.ArrayList;
import java.util.List;

public class UserDTOAssembler implements DTOAssembler<UserDTO, UserDomain> {

    private static final DTOAssembler<UserDTO, UserDomain> instance =
            new UserDTOAssembler();

    private UserDTOAssembler() {
        super();
    }

    public static DTOAssembler<UserDTO, UserDomain> getUserDTOAssembler() {
        return instance;
    }

    @Override
    public UserDTO toDTO(final UserDomain domain) {

        var domainTmp = ObjectHelper.getDefault(domain, new UserDomain());

        var idTypeDtoTmp = getIdTypeDTOAssembler().toDTO(domainTmp.getIdType());
        var cityDtoTmp = getCityDTOAssembler().toDTO(domainTmp.getHomeCity());

        return new UserDTO(
                domainTmp.getId(),
                idTypeDtoTmp,
                domainTmp.getIdNumber(),
                domainTmp.getFirstName(),
                domainTmp.getSecondName(),
                domainTmp.getFirstSurname(),
                domainTmp.getSecondSurname(),
                cityDtoTmp,
                domainTmp.getEmail(),
                domainTmp.getMobileNumber(),
                domainTmp.isEmailConfirmed(),
                domainTmp.isMobileNumberConfirmed()
        );
    }

    @Override
    public UserDomain toDomain(final UserDTO dto) {

        var dtoTmp = ObjectHelper.getDefault(dto, new UserDTO());

        var idTypeDomainTmp = getIdTypeDTOAssembler().toDomain(dtoTmp.getIdType());
        var cityDomainTmp = getCityDTOAssembler().toDomain(dtoTmp.getHomeCity());

        return new UserDomain(
                dtoTmp.getId(),
                idTypeDomainTmp,
                dtoTmp.getIdNumber(),
                dtoTmp.getFirstName(),
                dtoTmp.getSecondName(),
                dtoTmp.getFirstSurname(),
                dtoTmp.getSecondSurname(),
                cityDomainTmp,
                dtoTmp.getEmail(),
                dtoTmp.getMobileNumber(),
                dtoTmp.isEmailConfirmed(),
                dtoTmp.isMobileNumberConfirmed()
        );
    }

    @Override
    public List<UserDTO> toDTO(final List<UserDomain> domainList) {

        var domainListTmp = ObjectHelper.getDefault(domainList, new ArrayList<UserDomain>());
        var dtoList = new ArrayList<UserDTO>();

        for (var userDomain : domainListTmp) {

            dtoList.add(toDTO(userDomain));
        }

        return dtoList;
    }
}