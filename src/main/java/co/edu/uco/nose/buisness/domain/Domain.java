package co.edu.uco.nose.buisness.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
//anti nulos
//no instanciable
class Domain {
	
	private UUID id;
	
	protected Domain(final UUID id) {
		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(id);
	}
	
	
}
