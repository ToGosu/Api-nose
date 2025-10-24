package co.edu.uco.nose.business.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class CityDomain extends Domain{
	
	
	private String name;
	private StateDomain state;
	
	public CityDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setState(StateDomain.createDefault());
		setName(TextHelper.getDefault());
	}
	
	public CityDomain(final UUID id) {
		super(id);
		setState(StateDomain.createDefault());
		setName(TextHelper.getDefault());
	}
	
	public CityDomain(final UUID id, final StateDomain department, final String name) {
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

	public StateDomain getState() {
		return state;
	}

	public void setState(StateDomain state) {
		this.state = ObjectHelper.getDefault(state, StateDomain.createDefault());
	}
	
	public static CityDomain createDefault() {
		return new CityDomain();
	}

	

}