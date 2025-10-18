package co.edu.uco.nose.buisness.assembler.entity;

public interface EntityAssembler<E, D> {
	
	E toEntity(D domain);
	
	D toDomain(E entity);

}
