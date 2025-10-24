package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class CityEntity extends Entity{
	
	
	private String name;
	private StateEntity state;
	
	public CityEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setState(StateEntity.createDefault());
		setName(TextHelper.getDefault());
	}
	
	public CityEntity(final UUID id) {
		super(id);
		setState(StateEntity.createDefault());
		setName(TextHelper.getDefault());
	}
	
	public CityEntity(final UUID id, final StateEntity department, final String name) {
		super(id);
		setState(department);
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}

	public StateEntity getState() {
		return state;
	}

	public void setState(StateEntity state) {
		this.state = ObjectHelper.getDefault(state, StateEntity.createDefault());
	}
	
	public static CityEntity createDefault() {
		return new CityEntity();
	}
}