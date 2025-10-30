package co.edu.uco.nose.business.assembler.dto.impl;

import static co.edu.uco.nose.business.assembler.dto.impl.CountryDTOAssembler.getCountryDTOAssembler;

import java.util.List;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.StateDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.dto.StateDTO;

public class StateDTOAssembler implements DTOAssembler<StateDTO, StateDomain> {
	
    private static final DTOAssembler <StateDTO, StateDomain> instance =
            new StateDTOAssembler();

    private StateDTOAssembler() {

    }

    public static DTOAssembler <StateDTO, StateDomain> getStateDTOAssembler() {
        return instance;
    }

    @Override
    public StateDTO toDTO(final StateDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new StateDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var countryDtoTmp = getCountryDTOAssembler().toDTO(domainTmp.getCountry());
        return new StateDTO(domainTmp.getId(), countryDtoTmp, domainTmp.getName());
    }

    @Override
    public StateDomain toDomain(final StateDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new StateDTO());
        var countryDomainTmp  = getCountryDTOAssembler().toDomain(dtoTmp.getCountry());
        return new StateDomain(dtoTmp.getId(),countryDomainTmp ,dtoTmp.getName());
    }

    @Override
    public List<StateDTO> toDTO(List<StateDomain> domainList) {
        return List.of();
    }

}
