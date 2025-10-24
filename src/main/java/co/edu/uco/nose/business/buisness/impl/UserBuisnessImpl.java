package co.edu.uco.nose.business.buisness.impl;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.business.buisness.UserBuisness;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.*;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.UserEntity;

public final class UserBuisnessImpl implements UserBuisness {

    private DAOFactory daoFactory;


    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    );

    public UserBuisnessImpl (final DAOFactory daoFactory) {
        if (daoFactory == null) {
            throw NoseException.create("El DAOFactory no puede ser nulo.");
        }
        this.daoFactory = daoFactory;
    }

    @Override
    public void registerNewUserInformation(UserDomain userDomain) {

        validateDomainRules(userDomain);

        validateIdentificationDoesNotExist(
                userDomain.getIdType().getId(),
                userDomain.getIdNumber()
        );
        //Validar que no exista por email
        validateEmailDoesNotExist(userDomain.getEmail());

        //Validar que no exista por número de teléfono
        validateMobileNumberDoesNotExist(userDomain.getMobileNumber());

        // Generar el nuevo identificador único
        final UUID newUserId = generateNewUniqueUserId();

        //Preparar y registrar
        var userEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userDomain);
        userEntity.setId(newUserId);
        userEntity.setEmailConfirmed(false);
        userEntity.setMobileNumberConfirmed(false);

        daoFactory.getUserDAO().create(userEntity);
    }
    /**
     * Regla 1: Valida la consistencia interna del objeto UserDomain.
     */
    private void validateDomainRules(final UserDomain user) {
        if (user == null) {
            throw NoseException.create("La información del usuario es obligatoria.");
        }
        if (user.getIdType() == null) {
            throw NoseException.create("El tipo de identificación es obligatorio.");
        }

        if (user.getIdNumber() == null || user.getIdNumber().trim().isEmpty() || user.getIdNumber().length() > 25) {
            throw NoseException.create("El número de identificación no es válido (obligatorio, máx 25 caracteres).");
        }

        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty() || user.getFirstName().length() > 20) {
            throw NoseException.create("El primer nombre no es válido (obligatorio, máx 20 caracteres).");
        }

        if (user.getFirstSurname() == null || user.getFirstSurname().trim().isEmpty() || user.getFirstSurname().length() > 20) {
            throw NoseException.create("El primer apellido no es válido (obligatorio, máx 20 caracteres).");
        }

        if (user.getSecondName() == null || user.getSecondName().length() > 20) {
            throw NoseException.create("El segundo nombre no es válido (obligatorio, máx 20 caracteres).");
        }

        if (user.getSecondName() == null || user.getSecondName().length() > 20) {
            throw NoseException.create("El segundo apellido no es válido (obligatorio, máx 20 caracteres).");
        }

        if (user.getHomeCity() == null) {
            throw NoseException.create("La ciudad de residencia es obligatoria.");
        }

        if (user.getEmail() == null || !EMAIL_PATTERN.matcher(user.getEmail()).matches() || user.getEmail().length() > 250) {
            throw NoseException.create("El correo electrónico no es válido (obligatorio, formato válido, máx 250 caracteres).");
        }

        if (user.getMobileNumber() == null || user.getMobileNumber().trim().isEmpty() || user.getMobileNumber().length() > 20) {
            throw NoseException.create("El número de teléfono móvil no es válido (obligatorio, máx 20 caracteres).");
        }
    }
    /**
     * Regla 2: Valida que la identificación no exista.
     */
    private void validateIdentificationDoesNotExist(final UUID typeId, final String number) {

        IdTypeEntity idTypeFilter = IdTypeEntity.createDefault();
        idTypeFilter.setId(typeId);

        UserEntity filter = new UserEntity();
        filter.setIdType(idTypeFilter);
        filter.setIdNumber(number);

        List<UserEntity> results = daoFactory.getUserDAO().findByFilter(filter);

        if (!results.isEmpty()) {
            throw NoseException.create("Ya existe un usuario con la misma identificación.");
        }
    }
    /**
     * Regla 3: Valida que el email no exista.
     */
    private void validateEmailDoesNotExist(final String email) {
        UserEntity filter = new UserEntity();
        filter.setEmail(email);

        List<UserEntity> results = daoFactory.getUserDAO().findByFilter(filter);

        if (!results.isEmpty()) {
            throw NoseException.create("Ya existe un usuario con el mismo correo electrónico.");
        }
    }
    /**
     * Regla 4: Valida que el número de telefono móvil no exista.
     */
    private void validateMobileNumberDoesNotExist(final String mobileNumber) {
        UserEntity filter = new UserEntity();
        filter.setMobileNumber(mobileNumber);

        List<UserEntity> results = daoFactory.getUserDAO().findByFilter(filter);

        if (!results.isEmpty()) {
            throw NoseException.create("Ya existe un usuario con el mismo número de teléfono móvil.");
        }
    }
    /**
     * Regla 5: Genera un UUID y verifica su unicidad.
     */
    private UUID generateNewUniqueUserId() {
        UUID newId;
        boolean exists;

        do {
            newId = UUIDHelper.getUUIDHelper().generateNewUUID();
            UserEntity filter = new UserEntity();
            filter.setId(newId);
            exists = !daoFactory.getUserDAO().findByFilter(filter).isEmpty();
        } while (exists);

        return newId;
    }

    @Override
    public void dropUserInformation(UUID id) {

    }

    @Override
    public void updateUserInformation(UUID id, UserDomain userDomain) {

    }

    @Override
    public List<UserDomain> findAllUsers() {
        return List.of();
    }

    @Override
    public List<UserDomain> findUsersByFilter(UserDomain userFilters) {
        return List.of();
    }

    @Override
    public UserDomain findSpecificUser(UUID id) {
        return null;
    }

    @Override
    public void confirmMobileNumber(UUID id, int confirmationCode) {

    }

    @Override
    public void confirmEmail(UUID id, int confirmationCode) {

    }

    @Override
    public void sendMobileNumberConfirmation(UUID id) {

    }

    @Override
    public void sendEmailConfirmation(UUID id) {

    }
}
