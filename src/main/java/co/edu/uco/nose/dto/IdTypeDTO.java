package co.edu.uco.nose.dto;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.RetrieveDAO;

import java.util.List;
import java.util.UUID;

public class IdTypeDTO extends DTO {
	
	private String name;
	private String description;
	
	public IdTypeDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setDescription(TextHelper.getDefault());
	}
	
	public IdTypeDTO(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
		setDescription(TextHelper.getDefault());
	}
	
	public IdTypeDTO(final UUID id,final String name) {
		super(id);
		this.name = name;
		this.description = TextHelper.getDefault();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = TextHelper.getDefaultWithTrim(description);
	}
	
	

	


}