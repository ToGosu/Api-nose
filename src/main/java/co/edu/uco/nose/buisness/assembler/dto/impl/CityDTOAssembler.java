package co.edu.uco.nose.buisness.assembler.dto.impl;

import java.util.List;

import co.edu.uco.nose.buisness.assembler.dto.DTOAssembler;
import co.edu.uco.nose.buisness.domain.CityDomain;
import co.edu.uco.nose.dto.CityDTO;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class CityDTOAssembler implements DTOAssembler<CityDTO, CityDomain> {

    @Override
    public CityDTO toDTO(final CityDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new CityDomain(UUIDHelper.getUUIDHelper().getDefault()));
        // lastName is not present in domain, set default value
        return new CityDTO(domainTmp.getId(), domainTmp.getName());
    }

    @Override
    public CityDomain toDomain(final CityDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new CityDTO());
        return new CityDomain(dtoTmp.getId(), dtoTmp.getName());
    }

	@Override
	public List<CityDTO> toDTO(List<CityDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}
}