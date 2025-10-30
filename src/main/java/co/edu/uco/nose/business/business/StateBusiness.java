package co.edu.uco.nose.business.business;

import co.edu.uco.nose.business.domain.StateDomain;

import java.util.List;
import java.util.UUID;

public interface StateBusiness {

    List<StateDomain> findAllStates ();

    List<StateDomain> findStatesByFilter (StateDomain stateFilters);

    StateDomain findSpecificState(UUID id);

}