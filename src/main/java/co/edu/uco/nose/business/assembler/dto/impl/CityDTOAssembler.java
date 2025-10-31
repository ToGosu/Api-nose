package co.edu.uco.nose.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.nose.dto.CityDTO;
import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class CityDTOAssembler implements DTOAssembler<CityDTO, CityDomain> {

	
    private static final DTOAssembler<CityDTO, CityDomain> instance =
            new CityDTOAssembler();

    private CityDTOAssembler() {

    }

    public static DTOAssembler<CityDTO, CityDomain> getCityDTOAssembler() {
        return instance;
    }

    @Override
    public CityDTO toDTO(final CityDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new CityDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var stateDtoTmp = StateDTOAssembler.getStateDTOAssembler().toDTO(domainTmp.getState());
        return new CityDTO(domainTmp.getId(), stateDtoTmp, domainTmp.getName());
    }

    @Override
    public CityDomain toDomain(final CityDTO dto) {
            var dtoTmp = ObjectHelper.getDefault(dto, new CityDTO());
            var stateDomainTmp = StateDTOAssembler.getStateDTOAssembler().toDomain(dtoTmp.getDepartment());
            return new CityDomain(dtoTmp.getId(), stateDomainTmp,dtoTmp.getName());
    }

    @Override
    public List<CityDTO> toDTO(List<CityDomain> domainList) {
        var domainListTmp = ObjectHelper.getDefault(domainList, new ArrayList<CityDomain>());
        var dtoList = new ArrayList<CityDTO>();

        for (var domain : domainListTmp) {
            dtoList.add(toDTO(domain));
        }

        return dtoList;
    }
	
}