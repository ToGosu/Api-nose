package co.edu.uco.nose.buisness.assembler.dto.impl;

import java.util.List;

import co.edu.uco.nose.buisness.assembler.dto.DTOAssembler;
import co.edu.uco.nose.buisness.domain.IdTypeDomain;
import co.edu.uco.nose.dto.IdTypeDTO;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class IdTypeDTOAssembler implements DTOAssembler<IdTypeDTO, IdTypeDomain> {

    @Override
    public IdTypeDTO toDTO(final IdTypeDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new IdTypeDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var dto = new IdTypeDTO(domainTmp.getId(), domainTmp.getName());
        dto.setDescription(domainTmp.getDescrption()); // Note: getDescrption is misspelled in domain
        return dto;
    }

    @Override
    public IdTypeDomain toDomain(final IdTypeDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new IdTypeDTO());
        var domain = new IdTypeDomain(dtoTmp.getId(), dtoTmp.getName());
        domain.setDescrption(dtoTmp.getDescription()); // Note: setDescrption is misspelled in domain
        return domain;
    }

	@Override
	public List<IdTypeDTO> toDTO(List<IdTypeDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}
}