package co.edu.uco.nose.business.business;

import co.edu.uco.nose.business.domain.CountryDomain;

import java.util.List;
import java.util.UUID;

public interface CountryBusiness {

    List<CountryDomain> findAllCountries ();

    List<CountryDomain> findCountriesByFilter (CountryDomain countryFilters);

    CountryDomain findSpecificCountry (UUID id);

}