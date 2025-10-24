package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class StateEntity extends Entity{
	
	
	private String name;
	private CountryEntity country;
	 
	public StateEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setCountry(CountryEntity.createDefault());
		setName(TextHelper.getDefault());
		
	}
	
	public StateEntity(final UUID id) {
		super (id);
		setCountry(CountryEntity.createDefault());
		setName(TextHelper.getDefault());
		
	}
	
	public StateEntity(final UUID id , final CountryEntity country,  final String name) {
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

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(final CountryEntity country) {
		this.country = ObjectHelper.getDefault(country, CountryEntity.createDefault());
	}
	
	public static StateEntity createDefault() {
		return new StateEntity();
	}
}