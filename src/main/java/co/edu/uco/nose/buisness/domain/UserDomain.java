package co.edu.uco.nose.buisness.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class UserDomain extends Domain{
	
	private String name;
	private String lastName;
	private String identification;
	
	public UserDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setIdentification(TextHelper.getDefault());
	}
	
	public UserDomain(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
	    setLastName(TextHelper.getDefault());
	    setIdentification(TextHelper.getDefault());
	}
	
	public UserDomain(final UUID id,final String name) {
		super(id);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(final String lastName) {
		this.lastName = TextHelper.getDefaultWithTrim(lastName);
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(final String lastname) {
		this.lastName = TextHelper.getDefaultWithTrim(lastname);
	}
	
	public String getIdentification() {
		return identification;
	}
	
	public void setIdentification(final String identification) {
		this.identification = TextHelper.getDefaultWithTrim(identification);
	}
	
	
	

}