package co.edu.uco.nose.business.business;

import co.edu.uco.nose.business.domain.CityDomain;

import java.util.List;
import java.util.UUID;

public interface CityBusiness {

    List<CityDomain> findAllCities ();

    List<CityDomain> findCitiesByFilter (CityDomain cityFilters);

    CityDomain findSpecificCity (UUID id);

}