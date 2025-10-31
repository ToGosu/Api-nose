package co.edu.uco.nose.business.assembler.dto.impl;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.IdTypeDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.dto.IdTypeDTO;

import java.util.ArrayList;
import java.util.List;

public final class IdTypeDTOAssembler implements DTOAssembler <IdTypeDTO, IdTypeDomain> {

    private static final DTOAssembler <IdTypeDTO, IdTypeDomain> instance =
            new IdTypeDTOAssembler();

    private IdTypeDTOAssembler() {

    }

    public static DTOAssembler <IdTypeDTO, IdTypeDomain> getIdTypeDTOAssembler() {
        return instance;
    }
    @Override
    public IdTypeDTO toDTO(final IdTypeDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new IdTypeDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new IdTypeDTO(domainTmp.getId(), domainTmp.getName());
    }

    @Override
    public IdTypeDomain toDomain(final IdTypeDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new IdTypeDTO());
        return new IdTypeDomain(dtoTmp.getId(), dtoTmp.getNombre());
    }

    @Override
    public List<IdTypeDTO> toDTO(List<IdTypeDomain> domainList) {


        var domainListTmp = ObjectHelper.getDefault(domainList, new ArrayList<IdTypeDomain>());
        var dtoList = new ArrayList<IdTypeDTO>();

        for (var domain : domainListTmp) {
            dtoList.add(toDTO(domain));
        }

        return dtoList;
    }
}