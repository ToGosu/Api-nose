package co.edu.uco.nose.buisness.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

//no heredable
public final class CountryDomain  extends Domain{
	
	private String name;
	private String description;
	
	public CountryDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setDescription(TextHelper.getDefault());
	}

	public CountryDomain(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
		setDescription(TextHelper.getDefault());
	}
	
	public CountryDomain(final UUID id,final String name) {
		super(id);
		this.name = name;
		this.description = TextHelper.getDefault();
	}

	


	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(final String descrption) {
		this.description = TextHelper.getDefaultWithTrim(descrption);
	}

}
