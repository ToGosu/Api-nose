package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class UserDTO extends DTO {

    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private String identification;
    private CityDTO residenceCity;
    private String email;
    private String phoneNumber;
    private boolean emailConfirmed;
    private boolean mobileNumberConfirmed;

    public UserDTO() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setIdentification(TextHelper.getDefault());
        setResidenceCity(new CityDTO());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setEmailConfirmed(false);
        setMobileNumberConfirmed(false);
    }

    public UserDTO(final UUID id) {
        super(id);
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setIdentification(TextHelper.getDefault());
        setResidenceCity(new CityDTO());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setEmailConfirmed(false);
        setMobileNumberConfirmed(false);
    }
//Instancia por defecto en el DTO
    public static final UserDTO build() { //significa que el metodo esta vacio por eso lo amarillo en el final
        return new UserDTO();
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

    public CityDTO getResidenceCity() {
        return residenceCity;
    }

    public void setResidenceCity(CityDTO residenceCity) {
        this.residenceCity = residenceCity;
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
}