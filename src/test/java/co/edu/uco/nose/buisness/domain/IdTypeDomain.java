package co.edu.uco.nose.buisness.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class IdTypeDomain extends Domain{
	
	private String name;
	private String description;
	
	public IdTypeDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}

	public IdTypeDomain(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
		
	}
	
	public IdTypeDomain(final UUID id,final String name) {
		super(id);
		this.name = name;
	}

	

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}
	
	public String getDescrption() {
		return description;
	}

	public void setDescrption(final String descrption) {
		this.description = TextHelper.getDefaultWithTrim(descrption);
	}
	
	

}