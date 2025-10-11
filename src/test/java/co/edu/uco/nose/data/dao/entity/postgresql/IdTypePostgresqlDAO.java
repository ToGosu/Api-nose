package co.edu.uco.nose.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.data.dao.entity.*;
import co.edu.uco.nose.dto.IdTypeDTO;

public final class IdTypePostgresqlDAO extends SqlConnection implements IdTypeDTO {

	protected IdTypePostgresqlDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IdTypeDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IdTypeDTO> findByFilter(IdTypeDTO filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdTypeDTO findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
