package co.edu.uco.nose.business.facade;

import co.edu.uco.nose.dto.StateDTO;

import java.util.List;
import java.util.UUID;

public interface StateFacade {

    List<StateDTO> findAllStates ();

    List<StateDTO> findStatesByFilter (StateDTO stateFilters);

    StateDTO findSpecificState(UUID id);

}