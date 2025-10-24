package co.edu.uco.nose.test;

import co.edu.uco.nose.business.facade.impl.UserFacadeImpl;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.dto.CityDTO;
import co.edu.uco.nose.dto.IdTypeDTO;
import co.edu.uco.nose.dto.UserDTO;

import java.util.UUID;

public class TestUserRegistration {

    public static void main(String[] args) {

        try {

            var user = new UserDTO();

            var idType = new IdTypeDTO();
            idType.setId(UUID.fromString("a1a1a1a1-0001-0001-0001-000000000001"));

            var city = new CityDTO();
            city.setId(UUID.fromString("d4d4d4d4-0004-0004-0004-000000000001"));

            user.setIdType(idType);
            user.setHomeCity(city);
            user.setIdNumber("1040871105");
            user.setFirstName("Juan");
            user.setFirstSurname("Pablo");
            user.setSecondName("Alzate");
            user.setSecondSurname("Pulgarin");
            user.setEmail("juan.alzate1104@uco.net.co");
            user.setMobileNumber("3207793196");

            var facade = new UserFacadeImpl();

            facade.registerNewUserInformation(user);

            System.out.println("Gane el semestre!!!");

        } catch (NoseException e) {
            System.out.println(e.getUserMessage());
            System.out.println(e.getTechnicalMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}