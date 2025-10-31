package co.edu.uco.nose.dto;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import java.util.UUID;

public class IdTypeDTO extends DTO {
	
	private String nombre;
	
	public IdTypeDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
	}
	
	public IdTypeDTO(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
	}
	
	public IdTypeDTO(final UUID id, final String nombre) {
		super(id);
		setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = TextHelper.getDefault(nombre);
	}
	
	public static IdTypeDTO createDefault() {
		return new IdTypeDTO();
	}
	
	

	


}