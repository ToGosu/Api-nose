// Archivo: PruebaConnection.java

package co.edu.uco.nose.data.dao.factory.postgresql;

import java.util.UUID;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.StateEntity;
import co.edu.uco.nose.entity.UserEntity;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class PruebaConnection {

    // PRE-REQUISITO: Asegúrate de que los INSERTs para estos UUIDs ya se ejecutaron en tu BD.
    private static final UUID PAIS_ID = UUID.fromString("1f3d4a8e-2e9f-4b6c-8e4a-4a2b6e8d2c1b");
    private static final UUID DEPARTAMENTO_ID = UUID.fromString("8f2e1a9c-5b8d-4c3e-9f2a-1b3d5c7e9a2d");
    private static final UUID CIUDAD_ID = UUID.fromString("3c8d2b7e-9f4a-4b6c-8e2a-1a3b5c7d9e4f");
    private static final UUID TIPO_ID = UUID.fromString("5e9d2c1b-8f3a-4b7c-9e2a-1b3d5c8e9f4a");

    public static void main(String[] args) {
        DAOFactory factory = DAOFactory.getFactory();

        try {
            factory.initTransaction();
            System.out.println("✅ Transacción iniciada correctamente");

            var userDAO = factory.getUserDAO();
            UUID usuarioId = UUID.randomUUID();

            CountryEntity country = new CountryEntity();
            country.setId(PAIS_ID);

            StateEntity state = new StateEntity();
            state.setId(DEPARTAMENTO_ID);
            state.setCountry(country);

            CityEntity city = new CityEntity();
            city.setId(CIUDAD_ID);
            city.setState(state);

            IdTypeEntity idType = new IdTypeEntity();
            idType.setId(TIPO_ID);

            UserEntity user = new UserEntity();
            user.setId(usuarioId);
            user.setIdType(idType);
            user.setIdNumber("987654321");
            user.setFirstName("Ana");
            user.setSecondName(null);
            user.setFirstSurname("Gomez");
            user.setSecondSurname("Arias");
            user.setHomeCity(city);
            user.setEmail("ana.gomez@example.com");
            user.setMobileNumber("3109876543");
            user.setEmailConfirmed(false);
            user.setMobileNumberConfirmed(false);

            // 1. Probamos CREATE
            userDAO.create(user);
            System.out.println("✅ (CREATE) Usuario insertado correctamente.");

            // 2. Probamos FIND BY ID
            UserEntity retrievedUser = userDAO.findById(usuarioId);
            if (!UUIDHelper.getUUIDHelper().isDefaultUUID(retrievedUser.getId())) {
                System.out.println("\n🔍 (FIND) Usuario encontrado: " + retrievedUser.getFirstName());
            } else {
                throw new Exception("Error: El usuario recién creado no fue encontrado.");
            }

            // 3. Probamos UPDATE
            retrievedUser.setMobileNumberConfirmed(true);
            userDAO.update(retrievedUser);
            System.out.println("✏️ (UPDATE) Usuario actualizado correctamente.");

            // 4. Probamos DELETE
            userDAO.delete(usuarioId);
            System.out.println("🗑️ (DELETE) Usuario eliminado correctamente.");

            UserEntity deletedUser = userDAO.findById(usuarioId);
            if(UUIDHelper.getUUIDHelper().isDefaultUUID(deletedUser.getId())) {
                System.out.println("✅ Verificación de borrado exitosa.");
            } else {
                throw new Exception("Error: El usuario no fue eliminado correctamente.");
            }

            factory.commitTransaction();
            System.out.println("\n✅ Transacción confirmada (COMMIT)");

        } catch (final Exception exception) {
            System.out.println("❌ Ocurrió un error, revirtiendo transacción...");
            factory.rollbackTransaction();
            exception.printStackTrace();

        } finally {
            factory.closeConnection();
            System.out.println("🔌 Conexión cerrada.");
        }
    }
}