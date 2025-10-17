package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class CityEntity extends Entity{
	
	
	private String name;
	private StateEntity state;
	
	public CityEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setState(new StateEntity());
	}
	

	public CityEntity(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
		setState(new StateEntity());
	}
	
	public CityEntity(final UUID id,final String name, final StateEntity state) {
		super(id);
		setName(name);
		setState(new StateEntity());
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}
	public StateEntity getState() {
		return state;
	}
	public void setState(final StateEntity state) {
		this.state = state;
	}
}