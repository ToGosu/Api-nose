package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class StateDTO extends DTO{
	private String name;
	private CountryDTO country;
	
	
	
	public StateDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setCountry(new CountryDTO());
	}

	public StateDTO(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
		setCountry(new CountryDTO());

	}
	
	public StateDTO(final UUID id,final String name, final CountryDTO country) {
		super(id);
		setName(name);
		setCountry(new CountryDTO());

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
		this.country = country;
	}
	

}