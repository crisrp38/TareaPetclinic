package org.springframework.samples.petclinic.vet;

import org.hibernate.mapping.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import jakarta.persistence.Cacheable;
import jakarta.transaction.Transactional;

public interface SpecialtyRepository extends Repository<Vet, Integer>{
	
	
	Page<Vet> findAll(Pageable pageable) throws DataAccessException;
	
}
