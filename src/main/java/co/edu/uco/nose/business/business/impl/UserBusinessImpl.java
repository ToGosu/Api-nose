package co.edu.uco.nose.business.business.impl;

import co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.business.business.UserBusiness;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public final class UserBusinessImpl implements UserBusiness {

    private DAOFactory daoFactory;


    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    );
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^\\d+$");

    public UserBusinessImpl (final DAOFactory daoFactory) {
        if (daoFactory == null) {
            throw NoseException.create("El DAOFactory no puede ser nulo.");
        }
        this.daoFactory = daoFactory;
    }

    @Override
    public void registerNewUserInformation(UserDomain userDomain) {

        validateIdTypeExists(userDomain.getIdType().getId());

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

        daoFactory.getUserDAO().create(userEntity);
    }
    /**
     * Regla 0: Valida que el tipo de identificación exista.
     */
    private void validateIdTypeExists(final UUID idTypeId) {
        if (idTypeId == null || UUIDHelper.getUUIDHelper().isDefaultUUID(idTypeId)) {
            // Si el ID es nulo o default, la regla de dominio ya debió fallar,
            // pero reforzamos aquí.
            throw NoseException.create("El ID del tipo de identificación no puede ser nulo o por defecto.");
        }

        IdTypeEntity idTypeFilter = IdTypeEntity.createDefault();
        idTypeFilter.setId(idTypeId);

        // Asumimos que el DAOFactory puede proveer un IdTypeDAO
        // y que este DAO tiene un método findByFilter (o findById).
        List<IdTypeEntity> results = daoFactory.getIdTypeDAO().findByFilter(idTypeFilter);

        if (results.isEmpty()) {
            throw NoseException.create("El tipo de identificación seleccionado ('" + idTypeId + "') no existe o no es válido.");
        }
    }
    /**
     * Regla 1: Valida la consistencia interna del objeto UserDomain.
     */
    private void validateDomainRules(final UserDomain user) {
        if (user == null) {
            throw NoseException.create("La información del usuario es obligatoria.");
        }
        if (user.getIdType() == null || user.getIdType().getId() == null || UUIDHelper.getUUIDHelper().isDefaultUUID(user.getIdType().getId())) {
            throw NoseException.create("El tipo de identificación es obligatorio y debe ser válido.");
        }
        if (user.getIdNumber() == null ||
                !NUMERIC_PATTERN.matcher(user.getIdNumber()).matches() ||
                user.getIdNumber().length() > 25) {
            throw NoseException.create("El número de identificación no es válido (obligatorio, solo números, máx 25 caracteres).");
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

        if (user.getSecondSurname() == null || user.getSecondSurname().length() > 20) {
            throw NoseException.create("El segundo apellido no es válido (obligatorio, máx 20 caracteres).");
        }


        if (user.getHomeCity() == null) {
            throw NoseException.create("La ciudad de residencia es obligatoria.");
        }


        if (user.getEmail() == null || !EMAIL_PATTERN.matcher(user.getEmail()).matches() || user.getEmail().length() > 250) {
            throw NoseException.create("El correo electrónico no es válido (obligatorio, formato válido, máx 250 caracteres).");
        }


        if (user.getMobileNumber() == null ||
                !NUMERIC_PATTERN.matcher(user.getMobileNumber()).matches() ||
                user.getMobileNumber().length() > 20) {
            throw NoseException.create("El número de teléfono móvil no es válido (obligatorio, solo números, sin espacios, máx 20 caracteres).");
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
        try {
            if (UUIDHelper.getUUIDHelper().isDefaultUUID(id)) {
                throw NoseException.create("El ID del usuario que desea eliminar no es válido.", "Capa de Negocio");
            }

            UserEntity filter = new UserEntity();
            filter.setId(id);
            List<UserEntity> entityList = daoFactory.getUserDAO().findByFilter(filter);

            if (entityList.isEmpty()) {
                throw NoseException.create("El usuario con el ID " + id + " no fue encontrado y no puede ser eliminado.",
                        "Capa de Negocio");
            }

            daoFactory.getUserDAO().delete(id);

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw NoseException.create(exception, "Error inesperado al eliminar el usuario.",
                    "Capa de Negocio");
        }
    }

    @Override
    public void updateUserInformation(UUID id, UserDomain userDomain) {

        try {
            userDomain.setId(id);
            validateDomainRules(userDomain);

            validateIdentificationDoesNotExistForOtherUser(
                    userDomain.getIdType().getId(),
                    userDomain.getIdNumber(),
                    id
            );
            validateEmailDoesNotExistForOtherUser(userDomain.getEmail(), id);
            validateMobileNumberDoesNotExistForOtherUser(userDomain.getMobileNumber(), id);

            var userEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userDomain);
            userEntity.setId(id);

            daoFactory.getUserDAO().update(userEntity);

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw NoseException.create(exception, "Error inesperado al actualizar el usuario.", "Capa de Negocio");
        }
    }
    /**
     * Valida que la identificación no exista PARA OTRO USUARIO.
     */
    private void validateIdentificationDoesNotExistForOtherUser(final UUID typeId, final String number, final UUID currentUserId) {
        IdTypeEntity idTypeFilter = IdTypeEntity.createDefault();
        idTypeFilter.setId(typeId);

        UserEntity filter = new UserEntity();
        filter.setIdType(idTypeFilter);
        filter.setIdNumber(number);

        List<UserEntity> results = daoFactory.getUserDAO().findByFilter(filter);

        if (!results.isEmpty() && !results.get(0).getId().equals(currentUserId)) {
            throw NoseException.create("La identificación ingresada ya pertenece a otro usuario.");
        }
    }
    /**
     * Valida que el email no exista PARA OTRO USUARIO.
     */
    private void validateEmailDoesNotExistForOtherUser(final String email, final UUID currentUserId) {
        UserEntity filter = new UserEntity();
        filter.setEmail(email);

        List<UserEntity> results = daoFactory.getUserDAO().findByFilter(filter);

        if (!results.isEmpty() && !results.get(0).getId().equals(currentUserId)) {
            throw NoseException.create("El correo electrónico ingresado ya pertenece a otro usuario.");
        }
    }
    /**
     * Valida que el número de telefono móvil no exista PARA OTRO USUARIO.
     */
    private void validateMobileNumberDoesNotExistForOtherUser(final String mobileNumber, final UUID currentUserId) {
        UserEntity filter = new UserEntity();
        filter.setMobileNumber(mobileNumber);

        List<UserEntity> results = daoFactory.getUserDAO().findByFilter(filter);

        if (!results.isEmpty() && !results.get(0).getId().equals(currentUserId)) {
            throw NoseException.create("El número de teléfono móvil ingresado ya pertenece a otro usuario.");
        }
    }

    @Override
    public List<UserDomain> findAllUsers() {

        try {
            List<UserEntity> entityList = daoFactory.getUserDAO().findAll();

            List<UserDomain> domainList = new ArrayList<>();

            for (UserEntity entity : entityList) {
                domainList.add(UserEntityAssembler.getUserEntityAssembler().toDomain(entity));
            }

            return domainList;

        } catch (final NoseException exception) {
            throw exception;

        } catch (final Exception exception) {
            throw NoseException.create(exception, "Error inesperado al consultar todos los usuarios.",
                    "Capa de Negocio");
        }
    }

    @Override
    public List<UserDomain> findUsersByFilter(UserDomain userFilters) {

        try {

            UserEntity filterEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userFilters);

            List<UserEntity> entityList = daoFactory.getUserDAO().findByFilter(filterEntity);

            List<UserDomain> domainList = new ArrayList<>();

            for (UserEntity entity : entityList) {
                domainList.add(UserEntityAssembler.getUserEntityAssembler().toDomain(entity));
            }
            return domainList;

        } catch (final NoseException exception) {
            throw exception;

        } catch (final Exception exception) {
            throw NoseException.create(exception, "Error inesperado al consultar los usuarios por filtro.",
                    "Capa de Negocio");
        }
    }

    @Override
    public UserDomain findSpecificUser(UUID id) {
        try {

            UserEntity filter = new UserEntity();
            filter.setId(id);
            List<UserEntity> entityList = daoFactory.getUserDAO().findByFilter(filter);

            if (entityList.isEmpty()) {
                throw NoseException.create("El usuario con el ID " + id + " no fue encontrado.", "Capa de Negocio");
            }

            return UserEntityAssembler.getUserEntityAssembler().toDomain(entityList.get(0));

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw NoseException.create(exception, "Error inesperado al buscar un usuario específico.",
                    "Capa de Negocio");
        }
    }

    @Override
    public void confirmMobileNumber(UUID id, int confirmationCode) {

    }

    @Override
    public void confirmEmail(UUID id) {

    }

    @Override
    public void sendMobileNumberConfirmation(UUID id) {

    }

    @Override
    public void sendEmailConfirmation(UUID id) {

    }
}