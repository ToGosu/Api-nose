package co.edu.uco.nose.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.data.dao.entity.*;

public final class CityPostgresqlDAO extends SqlConnection implements CityDAO{

	public  CityPostgresqlDAO(final Connection connection) {
		super(connection);
	}
	
	@Override
	public List<CityEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CityEntity> findByFilter(CityEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityEntity findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}