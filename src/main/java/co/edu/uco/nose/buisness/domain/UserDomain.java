package co.edu.uco.nose.buisness.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class UserDomain extends Domain {

    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private String identification;
    private CityDomain residenceCity;
    private String email;
    private String phoneNumber;
    private boolean emailConfirmed;
    private boolean mobileNumberConfirmed;
    private UUID id;

    public UserDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setIdentification(TextHelper.getDefault());
        setResidenceCity(new CityDomain());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setEmailConfirmed(false);
        setMobileNumberConfirmed(false);
    }

    public UserDomain(final UUID id) {
        super(id);
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setIdentification(TextHelper.getDefault());
        setResidenceCity(new CityDomain());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setEmailConfirmed(false);
        setMobileNumberConfirmed(false);
    }

    public UserDomain(final UUID id, final String firstName) {
        super(id);
        this.firstName = TextHelper.getDefaultWithTrim(firstName);
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

    public String getFirstLastName() {
        return firstLastName;
    }
    public void setFirstLastName(final String firstLastName) {
        this.firstLastName = TextHelper.getDefaultWithTrim(firstLastName);
    }

    public String getSecondLastName() {
        return secondLastName;
    }
    public void setSecondLastName(final String secondLastName) {
        this.secondLastName = TextHelper.getDefaultWithTrim(secondLastName);
    }

    public String getIdentification() {
        return identification;
    }
    public void setIdentification(final String identification) {
        this.identification = TextHelper.getDefaultWithTrim(identification);
    }

    public CityDomain getResidenceCity() {
        return residenceCity;
    }
    public void setResidenceCity(final CityDomain residenceCity) {
        this.residenceCity = residenceCity;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(final String email) {
        this.email = TextHelper.getDefaultWithTrim(email);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = TextHelper.getDefaultWithTrim(phoneNumber);
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }
    public void setEmailConfirmed(final boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public boolean isMobileNumberConfirmed() {
        return mobileNumberConfirmed;
    }
    public void setMobileNumberConfirmed(final boolean mobileNumberConfirmed) {
        this.mobileNumberConfirmed = mobileNumberConfirmed;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }
}