package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class StateEntity extends Entity{
	
	
	private String name;
	private CountryEntity country;
	
	
	public StateEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setCountry(new CountryEntity());
	}
	

	public StateEntity(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
		setCountry(new CountryEntity());
	}
	
	public StateEntity(final UUID id,final String name, final CountryEntity country) {
		super(id);
		setName(name);
		setCountry(new CountryEntity());
		
		
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}
	public CountryEntity getCountry() {
		return country;
	}
	public void setCountry(final CountryEntity country) {
		this.country = country;
	}

}