package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class IdTypeEntity extends Entity{
	
	
	private String name;
	private String description;
	
	public IdTypeEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setDescrption(TextHelper.getDefault());
	}
	

	public IdTypeEntity(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
		setDescrption(TextHelper.getDefault());
	}
	
	public IdTypeEntity(final UUID id,final String name) {
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
	public String getDescrption() {
		return description;
	}

	public void setDescrption(final String descrption) {
		this.description = TextHelper.getDefaultWithTrim(descrption);
	}
}