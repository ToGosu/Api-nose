package co.edu.uco.nose.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.UserEntity;

public final class UserPostgresqlDAO extends SqlConnection implements UserDAO {



    @Override
    public void create(final UserEntity entity) {

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql=new StringBuilder();
        sql.append("INSERT INTO User(id, idType, phoneNumber, firstName, secondName, firstLastName, secondLastName, residenceCity, email, phoneNumber, emailConfirmed, mobileNumberConfirmed) ");
        sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        try (var preparedStatement=this.getConnection().prepareStatement(sql.toString())){
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getIdType().getId());
            preparedStatement.setString(3, entity.getPhoneNumber());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getSecondName());
            preparedStatement.setString(6, entity.getFirstLastName());
            preparedStatement.setString(7, entity.getSecondLastName());
            preparedStatement.setObject(8, entity.getResidenceCity().getId());
            preparedStatement.setString(9, entity.getEmail());
            preparedStatement.setBoolean(10, entity.isEmailConfirmed());
            preparedStatement.setBoolean(11, entity.isMobileNumberConfirmed());
            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_SQL_CREATE.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_CREATE.getContent(),
                    (SQLException) exception
            );
        } catch (final Exception exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_UNEXPECTED_CREATE.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_CREATE.getContent(),
                    (SQLException) exception
            );
        } catch (final Throwable exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_CRITICAL_CREATE.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_CRITICAL_CREATE.getContent(),
                    (SQLException) exception
            );
        }
    }

    public UserPostgresqlDAO(Connection connection) {
        super(connection);
        // TODO Auto-generated constructor stub
    }

    // Java
    @Override
    public List<UserEntity> findByFilter(UserEntity filterEntity) {
        if (filterEntity == null) {
            return findAll();
        }

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("SELECT id, idType, firstname, secondname, firstlastname, secondlastname, ");
        sql.append("email, phone, password, emailconfirmation, phoneconfirmation ");
        sql.append("FROM users");

        final java.util.List<String> whereClauses = new java.util.ArrayList<>();

        if (filterEntity.getId() != null) {
            whereClauses.add("id = ?");
        }
        if (filterEntity.getIdType() != null && filterEntity.getIdType().getId() != null) {
            whereClauses.add("idtype = ?");
        }
        if (filterEntity.getFirstName() != null && !filterEntity.getFirstName().trim().isEmpty()) {
            whereClauses.add("firstname ILIKE ?");
        }
        if (filterEntity.getSecondName() != null && !filterEntity.getSecondName().trim().isEmpty()) {
            whereClauses.add("secondname ILIKE ?");
        }
        if (filterEntity.getFirstLastName() != null && !filterEntity.getFirstLastName().trim().isEmpty()) {
            whereClauses.add("firstlastname ILIKE ?");
        }
        if (filterEntity.getSecondLastName() != null && !filterEntity.getSecondLastName().trim().isEmpty()) {
            whereClauses.add("secondlastname ILIKE ?");
        }
        if (filterEntity.getEmail() != null && !filterEntity.getEmail().trim().isEmpty()) {
            whereClauses.add("email ILIKE ?");
        }
        if (filterEntity.getPhoneNumber() != null && !filterEntity.getPhoneNumber().trim().isEmpty()) {
            whereClauses.add("phonenumber ILIKE ?");
        }


        if (!whereClauses.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", whereClauses));
        }

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {

            int index = 1;
            if (filterEntity.getId() != null) {
                preparedStatement.setObject(index++, filterEntity.getId());
            }
            if (filterEntity.getIdType() != null && filterEntity.getIdType().getId() != null) {
                preparedStatement.setObject(index++, filterEntity.getIdType().getId());
            }
            if (filterEntity.getFirstName() != null && !filterEntity.getFirstName().trim().isEmpty()) {
                preparedStatement.setString(index++, "%" + filterEntity.getFirstName().trim() + "%");
            }
            if (filterEntity.getSecondName() != null && !filterEntity.getSecondName().trim().isEmpty()) {
                preparedStatement.setString(index++, "%" + filterEntity.getSecondName().trim() + "%");
            }
            if (filterEntity.getFirstLastName() != null && !filterEntity.getFirstLastName().trim().isEmpty()) {
                preparedStatement.setString(index++, "%" + filterEntity.getFirstLastName().trim() + "%");
            }
            if (filterEntity.getSecondLastName() != null && !filterEntity.getSecondLastName().trim().isEmpty()) {
                preparedStatement.setString(index++, "%" + filterEntity.getSecondLastName().trim() + "%");
            }
            if (filterEntity.getEmail() != null && !filterEntity.getEmail().trim().isEmpty()) {
                preparedStatement.setString(index++, "%" + filterEntity.getEmail().trim() + "%");
            }
            if (filterEntity.getPhoneNumber() != null && !filterEntity.getPhoneNumber().trim().isEmpty()) {
                preparedStatement.setString(index++, "%" + filterEntity.getPhoneNumber().trim() + "%");
            }


            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                final java.util.List<UserEntity> users = new java.util.ArrayList<>();
                while (resultSet.next()) {
                    final var user = new UserEntity(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("firstname"),
                            resultSet.getString("secondname"),
                            resultSet.getString("firstlastname"),
                            resultSet.getString("secondlastname"),
                            resultSet.getString("identification"),
                            new CityEntity(),
                            new IdTypeEntity(),
                            resultSet.getString("email"),
                            resultSet.getString("phonenumber"),
                            resultSet.getBoolean("emailconfirmation"),
                            resultSet.getBoolean("phoneconfirmation")
                    );
                    users.add(user);
                }
                return users;
            }

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_FIND_BY_FILTER_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_SQL.getContent(),
                    exception
            );
        } catch (final Exception exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_FIND_BY_FILTER_UNEXPECTED.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_UNEXPECTED.getContent(),
                    (SQLException) exception
            );
        } catch (final Throwable exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_FIND_BY_FILTER_CRITICAL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_CRITICAL.getContent(),
                    (SQLException) exception
            );
        }
    }


    @Override
    public List<UserEntity> findAll() {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("SELECT id, idType, firstname, secondname, firstlastname, secondlastname, ");
        sql.append("email, phone, username, password, emailconfirmation, phoneconfirmation ");
        sql.append("FROM users");

        final List<UserEntity> users = new java.util.ArrayList<>();

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final var user = new UserEntity(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("secondname"),
                        resultSet.getString("firstlastname"),
                        resultSet.getString("secondlastname"),
                        resultSet.getString("identification"),
                        new CityEntity(),
                        new IdTypeEntity(),
                        resultSet.getString("email"),
                        resultSet.getString("phonenumber"),
                        resultSet.getBoolean("emailconfirmation"),
                        resultSet.getBoolean("phoneconfirmation")
                );
                users.add(user);
            }
            return users;

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_FIND_ALL_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_ALL_SQL.getContent(),
                    (SQLException) exception
            );
        } catch (final Exception exception) {
            throw new NoseException(
                MessagesEnum.USER_ERROR_FIND_ALL_UNEXPECTED.getContent(),
                MessagesEnum.TECHNICAL_ERROR_FIND_ALL_UNEXPECTED.getContent(),
                    (SQLException) exception
            );
        } catch (final Throwable exception) {
            throw new NoseException(
              MessagesEnum.USER_ERROR_FIND_ALL_CRITICAL.getContent(),
              MessagesEnum.TECHNICAL_ERROR_FIND_ALL_CRITICAL.getContent(),
                    (SQLException) exception
            );
        }
    }



    @Override
    public UserEntity findById(UUID id) {

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();

        sql.append("SELECT id, identitydocument, name, firstlastname, secondlastname, email, phone, username, " +
                "password, emailconfirmation, phoneconfirmation FROM users WHERE id = ?");

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {

            preparedStatement.setObject(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new UserEntity(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("secondname"),
                        resultSet.getString("firstlastname"),
                        resultSet.getString("secondlastname"),
                        resultSet.getString("identification"),
                        new CityEntity(),
                        new IdTypeEntity(),
                        resultSet.getString("email"),
                        resultSet.getString("phonenumber"),
                        resultSet.getBoolean("emailconfirmation"),
                        resultSet.getBoolean("phoneconfirmation")
                );
            } else {
                return null;
            }

        } catch (SQLException exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_FIND_BY_ID_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_SQL.getContent(),
                    exception
            );
        } catch (Exception exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_FIND_BY_ID_UNEXPECTED.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_UNEXPECTED.getContent(),
                    (SQLException) exception
            );
        } catch (Throwable exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_FIND_BY_ID_CRITICAL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_CRITICAL.getContent(),
                    (SQLException) exception
            );
        }
    }

    @Override
    public void update(UserEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
        final var sql = new StringBuilder();
        sql.append("UPDATE User SET idType = ?, phoneNumber = ?, firstName = ?, secondName = ?, firstLastName = ?, secondLastName = ?, residenceCity = ?, email = ?, phoneNumber = ?, emailConfirmed = ?, mobileNumberConfirmed = ? WHERE id = ?");
        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, entity.getIdType().getId());
            preparedStatement.setString(2, entity.getPhoneNumber());
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getSecondName());
            preparedStatement.setString(5, entity.getFirstLastName());
            preparedStatement.setString(6, entity.getSecondLastName());
            preparedStatement.setObject(7, entity.getResidenceCity().getId());
            preparedStatement.setString(8, entity.getEmail());
            preparedStatement.setString(9, entity.getPhoneNumber());
            preparedStatement.setBoolean(10, entity.isEmailConfirmed());
            preparedStatement.setBoolean(11, entity.isMobileNumberConfirmed());
            preparedStatement.setObject(12, entity.getId());
            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_SQL_UPDATE.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE.getContent(),
                    (SQLException) exception
            );
        } catch (final Exception exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_UNEXPECTED_UPDATE.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_UPDATE.getContent(),
                    (SQLException) exception
            );
        } catch (final Throwable exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_CRITICAL_UPDATE.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_CRITICAL_UPDATE.getContent(),
                    (SQLException) exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
        final var sql = new StringBuilder();
        sql.append("DELETE FROM User WHERE id = ?");
        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_SQL_DELETE.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE.getContent(),
                    (SQLException) exception
            );
        } catch (final Exception exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_UNEXPECTED_DELETE.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_DELETE.getContent(),
                    (SQLException) exception
            );
        } catch (final Throwable exception) {
            throw new NoseException(
                    MessagesEnum.USER_ERROR_CRITICAL_DELETE.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_CRITICAL_DELETE.getContent(),
                    (SQLException) exception
            );
        }
    }

}

