package co.edu.uco.nose.business.business;

import co.edu.uco.nose.business.domain.IdTypeDomain;

import java.util.List;
import java.util.UUID;

public interface IdTypeBusiness {

    List<IdTypeDomain> findAllIdTypes ();

    List<IdTypeDomain> findIdTypesByFilter (IdTypeDomain idTypeFilters);

    IdTypeDomain findSpecificIdType (UUID id);

}