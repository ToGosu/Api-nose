package co.edu.uco.nose.business.facade;

import co.edu.uco.nose.dto.IdTypeDTO;

import java.util.List;
import java.util.UUID;

public interface IdTypeFacade {

    List<IdTypeDTO> findAllIdTypes ();

    List<IdTypeDTO> findIdTypesByFilter (IdTypeDTO idTypeFilters);

    IdTypeDTO findSpecificIdType (UUID id);

}