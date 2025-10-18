package co.edu.uco.nose.buisness.assembler.dto.impl;

import static co.edu.uco.nose.buisness.assembler.dto.impl.CountryDTOAssembler.getCountryDTOAssembler;

import java.util.List;

import co.edu.uco.nose.buisness.assembler.dto.DTOAssembler;
import co.edu.uco.nose.buisness.domain.StateDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.dto.StateDTO;

public class StateDTOAssembler implements DTOAssembler<StateDTO, StateDomain> {

	@Override
	public StateDTO toDTO(final StateDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new StateDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var countryTmp = getCountryDTOAssembler().toDTO(domainTmp.getCountry());
		return new StateDTO(domainTmp.getId(), domainTmp.getName(), countryTmp);
	}


	@Override
	public StateDomain toDomain(final StateDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new StateDTO());
		var countryDomainTmp = getCountryDTOAssembler().toDomain(dtoTmp.getCountry());
		return new StateDomain(dtoTmp.getId(), dtoTmp.getName(), countryDomainTmp);
	}


	@Override
	public List<StateDTO> toDTO(List<StateDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}


}
