package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class StateDTO extends DTO{
	private String name;
	
	
	
	public StateDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}

	public StateDTO(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
	}
	
	public StateDTO(final UUID id,final String name) {
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