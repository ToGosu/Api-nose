package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class UserEntity extends Entity {

    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private String identification;
    private CityEntity residenceCity;
    private IdTypeEntity idType;
    private String email;
    private String phoneNumber;
    private boolean emailConfirmed;
    private boolean mobileNumberConfirmed;
    private boolean emailConfirmedIsDefaultValue;
	private boolean mobileNumberConfirmedIsDefaultValue;

    public UserEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setIdentification(TextHelper.getDefault());
        setResidenceCity(new CityEntity());
        setIdType(new IdTypeEntity());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setEmailConfirmed(false);
        setMobileNumberConfirmed(false);
        setEmailConfirmedIsDefaultValue(true);
        setMobileNumberConfirmedIsDefaultValue(true);
        
    }

    public UserEntity(final UUID id) {
        super(id);
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setIdentification(TextHelper.getDefault());
        setResidenceCity(new CityEntity());
        setIdType(new IdTypeEntity());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setEmailConfirmed(false);
        setMobileNumberConfirmed(false);
        setEmailConfirmedIsDefaultValue(true);
        setMobileNumberConfirmedIsDefaultValue(true);
    }
    
    public UserEntity(final UUID id, final String firstName, final String secondName, final String firstLastName,
			final String secondLastName, final String identification, final CityEntity residenceCity,
			final IdTypeEntity idType, final String email, final String phoneNumber, final boolean emailConfirmed,
			final boolean mobileNumberConfirmed) {
		super(id);
		setFirstName(firstName);
		setSecondName(secondName);
		setFirstLastName(firstLastName);
		setSecondLastName(secondLastName);
		setIdentification(identification);
		setResidenceCity(residenceCity);
		setIdType(idType);
		setEmail(email);
		setPhoneNumber(phoneNumber);
		setEmailConfirmed(emailConfirmed);
		setMobileNumberConfirmed(mobileNumberConfirmed);
		setEmailConfirmedIsDefaultValue(false);
		setMobileNumberConfirmedIsDefaultValue(false);
	}

    public static final UserEntity build() {
        return new UserEntity();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public CityEntity getResidenceCity() {
        return residenceCity;
    }

    public void setResidenceCity(CityEntity residenceCity) {
        this.residenceCity = residenceCity;
    }

    public IdTypeEntity getIdType() {
        return idType;
    }

    public void setIdType(IdTypeEntity idType) {
        this.idType = idType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public boolean isMobileNumberConfirmed() {
        return mobileNumberConfirmed;
    }

    public void setMobileNumberConfirmed(boolean mobileNumberConfirmed) {
        this.mobileNumberConfirmed = mobileNumberConfirmed;
    }

	public boolean isEmailConfirmedIsDefaultValue() {
		return emailConfirmedIsDefaultValue;
	}

	public void setEmailConfirmedIsDefaultValue(boolean emailConfirmedIsDefaultValue) {
		this.emailConfirmedIsDefaultValue = emailConfirmedIsDefaultValue;
	}

	public boolean isMobileNumberConfirmedIsDefaultValue() {
		return mobileNumberConfirmedIsDefaultValue;
	}

	public void setMobileNumberConfirmedIsDefaultValue(boolean mobileNumberConfirmedIsDefaultValue) {
		this.mobileNumberConfirmedIsDefaultValue = mobileNumberConfirmedIsDefaultValue;
	}
    
}