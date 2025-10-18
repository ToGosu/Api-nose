package co.edu.uco.nose.buisness.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.entity.CountryEntity;

public class StateDomain extends Domain{
	
	private String name;
	private CountryDomain country;
	
	public StateDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setCountry(new CountryDomain());

	}

	public StateDomain(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
		setCountry(new CountryDomain());

	}
	
	public StateDomain(final UUID id,final String name, final CountryDomain country) {
		super(id);
		setName(name);
		setCountry(new CountryDomain());
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
		this.country = country;
	}

}