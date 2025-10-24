package co.edu.uco.nose.business.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class IdTypeDomain extends Domain{
	
	private String name;
	
	public IdTypeDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}
	
	public IdTypeDomain(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
	}
	
	public IdTypeDomain(final UUID id, final String nombre) {
		super(id);
		setName(nombre);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.getDefault(name);
	}
	
	public static IdTypeDomain createDefault() {
		return new IdTypeDomain();
	}
	
	

}