package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class StateDTO extends DTO{
	private String name;
	private CountryDTO country;
	
	public StateDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setCountry(CountryDTO.createDefault());
		setName(TextHelper.getDefault());
		
	}
	
	public StateDTO(final UUID id) {
		super (id);
		setCountry(CountryDTO.createDefault());
		setName(TextHelper.getDefault());
		
	}
	
	public StateDTO(final UUID id , final CountryDTO country,  final String name) {
		super(id);
		setCountry(country);
		setName(name);
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}

	public CountryDTO getCountry() {
		return country;
	}

	public void setCountry(final CountryDTO country) {
		this.country = ObjectHelper.getDefault(country, CountryDTO.createDefault());
	}
	
	public static StateDTO createDefault() {
		return new StateDTO();
	}
}