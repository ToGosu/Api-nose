package co.edu.uco.nose.business.facade;

import co.edu.uco.nose.dto.CountryDTO;

import java.util.List;
import java.util.UUID;

public interface CountryFacade {

    List<CountryDTO> findAllCountries ();

    List<CountryDTO> findCountriesByFilter (CountryDTO countryFilters);

    CountryDTO findSpecificCountry (UUID id);
}