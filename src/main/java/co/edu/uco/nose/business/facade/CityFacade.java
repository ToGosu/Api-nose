package co.edu.uco.nose.business.facade;

import co.edu.uco.nose.dto.CityDTO;

import java.util.List;
import java.util.UUID;

public interface CityFacade {

    List<CityDTO> findAllCities();

    List<CityDTO> findCitiesByFilter(CityDTO cityFilters);

    CityDTO findSpecificCity(UUID id);

}