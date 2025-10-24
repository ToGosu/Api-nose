package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class IdTypeEntity extends Entity{
	
	private String name;
	
	public IdTypeEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}
	
	public IdTypeEntity(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
	}
	
	public IdTypeEntity(final UUID id, final String nombre) {
		super(id);
		setName(nombre);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.getDefault(name);
	}
	
	public static IdTypeEntity createDefault() {
		return new IdTypeEntity();
	}

}