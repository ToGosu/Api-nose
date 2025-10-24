package co.edu.uco.nose.business.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.entity.CountryEntity;

public class StateDomain extends Domain{
	
	private String name;
	private CountryDomain country;
	
	public StateDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setCountry(CountryDomain.createDefault());
		setName(TextHelper.getDefault());
		
	}
	
	public StateDomain(final UUID id) {
		super (id);
		setCountry(CountryDomain.createDefault());
		setName(TextHelper.getDefault());
		
	}
	
	public StateDomain(final UUID id , final CountryDomain country,  final String name) {
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

	public CountryDomain getCountry() {
		return country;
	}

	public void setCountry(final CountryDomain country) {
		this.country = ObjectHelper.getDefault(country, CountryDomain.createDefault());
	}
	
	public static StateDomain createDefault() {
		return new StateDomain();
	}
}