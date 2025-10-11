package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class CityEntity extends Entity{
	
	
	private String name;
	
	public CityEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}
	

	public CityEntity(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
	}
	
	public CityEntity(final UUID id,final String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}

}