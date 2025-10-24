package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class UserEntity extends Entity {

	
	private IdTypeEntity idType;
	private String identificationNumber;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secondSurname;
	private CityEntity homeCity;
	private String email;
	private String mobilePhoneNumber;
	private boolean emailConfirmed;
	private boolean MobileNumberConfirmed;
	private boolean emailConfirmedIsDefaultValue;
	private boolean mobileNumberConfirmedIsDefualtValue;
	
	public UserEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setIdType(IdTypeEntity.createDefault());
		setIdNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setFirstSurname(TextHelper.getDefault());
		setSecondSurname(TextHelper.getDefault());
		setHomeCity(CityEntity.createDefault());
		setEmail(TextHelper.getDefault());
		setMobileNumber(TextHelper.getDefault());
		setEmailConfirmed(false);
		setEmailConfirmedIsDefaultValue(true);
		setMobileNumberConfirmed(false);
		setMobileNumberConfirmedIsDefualtValue(true);
	}
	
	public UserEntity(final UUID id) {
		super(id);
		setIdType(IdTypeEntity.createDefault());
		setIdNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setFirstSurname(TextHelper.getDefault());
		setSecondSurname(TextHelper.getDefault());
		setHomeCity(CityEntity.createDefault());
		setEmail(TextHelper.getDefault());
		setMobileNumber(TextHelper.getDefault());
		setEmailConfirmed(false);
		setEmailConfirmedIsDefaultValue(true);
		setMobileNumberConfirmed(false);
		setMobileNumberConfirmedIsDefualtValue(true);
	}
	
	public UserEntity(final UUID id, final IdTypeEntity identificationType, final String identificationNumber, final String firstName, 
			final String secondName, final String firstSurname, final String secondSurname, final CityEntity cityResidence, final String email, 
			final String mobilePhoneNumber, final boolean confirmedEmail, final boolean confirmedMobilePhoneNumber) {
		super(id);
		setIdType(identificationType);
		setIdNumber(identificationNumber);
		setFirstName(firstName);
		setSecondName(secondName);
		setFirstSurname(firstSurname);
		setSecondSurname(secondSurname);
		setHomeCity(cityResidence);
		setEmail(email);
		setMobileNumber(mobilePhoneNumber);
		setEmailConfirmed(confirmedEmail);
		setEmailConfirmedIsDefaultValue(false);
		setMobileNumberConfirmed(confirmedMobilePhoneNumber);
		setMobileNumberConfirmedIsDefualtValue(false);
	}
	
	public IdTypeEntity getIdType() {
		return idType;
	}
	
	public void setIdType(final IdTypeEntity idType) {
		this.idType = ObjectHelper.getDefault(idType, IdTypeEntity.createDefault());
	}
	
	public String getIdNumber() {
		return identificationNumber;
	}
	
	public void setIdNumber(final String identificationNumber) {
		this.identificationNumber = TextHelper.getDefaultWithTrim(identificationNumber);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(final String firstName) {
		this.firstName = TextHelper.getDefaultWithTrim(firstName);
	}
	
	public String getSecondName() {
		return secondName;
	}
	
	public void setSecondName(final String secondName) {
		this.secondName = TextHelper.getDefaultWithTrim(secondName);
	}
	
	public String getFirstSurname() {
		return firstSurname;
	}
	
	public void setFirstSurname(final String firstSurname) {
		this.firstSurname = TextHelper.getDefaultWithTrim(firstSurname);
	}
	
	public String getSecondSurname() {
		return secondSurname;
	}
	
	public void setSecondSurname(final String secondSurname) {
		this.secondSurname = TextHelper.getDefaultWithTrim(secondSurname);
	}
	
	public CityEntity getHomeCity() {
		return homeCity;
	}
	
	public void setHomeCity(final CityEntity cityResidence) {
		this.homeCity = ObjectHelper.getDefault(cityResidence, CityEntity.createDefault());
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(final String email) {
		this.email = TextHelper.getDefaultWithTrim(email);
	}
	
	public String getMobileNumber() {
		return mobilePhoneNumber;
	}
	
	public void setMobileNumber(final String mobilePhoneNumber) {
		this.mobilePhoneNumber = TextHelper.getDefaultWithTrim(mobilePhoneNumber);
	}

	public boolean isEmailConfirmed() {
		return emailConfirmed;
	}

	public void setEmailConfirmed(final boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
		setEmailConfirmedIsDefaultValue(false);
	}

	public boolean isMobileNumberConfirmed() {
		return MobileNumberConfirmed;
	}

	public void setMobileNumberConfirmed(final boolean mobileNumberConfirmed) {
		MobileNumberConfirmed = mobileNumberConfirmed;
		setMobileNumberConfirmedIsDefualtValue(false);
	}

	public boolean isEmailConfirmedIsDefaultValue() {
		return emailConfirmedIsDefaultValue;
	}

	public void setEmailConfirmedIsDefaultValue(final boolean emailConfirmedIsDefaultValue) {
		this.emailConfirmedIsDefaultValue = emailConfirmedIsDefaultValue;
	}

	public boolean isMobileNumberConfirmedIsDefualtValue() {
		return mobileNumberConfirmedIsDefualtValue;
	}

	public void setMobileNumberConfirmedIsDefualtValue(final boolean mobileNumberConfirmedIsDefualtValue) {
		this.mobileNumberConfirmedIsDefualtValue = mobileNumberConfirmedIsDefualtValue;
	}
}