/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vet;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Repository class for <code>Vet</code> domain objects All method names are compliant
 * with Spring Data naming conventions so this interface can easily be extended for Spring
 * Data. See:
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public interface VetRepository extends Repository<Vet, Integer> {

	/**
	 * Retrieve all <code>Vet</code>s from the data store.
	 * @return a <code>Collection</code> of <code>Vet</code>s
	 */
	@Transactional(readOnly = true)
	@Cacheable("vets")
	Collection<Vet> findAll() throws DataAccessException;

	/**
	 * Retrieve all <code>Vet</code>s from data store in Pages
	 * @param pageable
	 * @return
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@Cacheable("vets")
	Page<Vet> findAll(Pageable pageable) throws DataAccessException;

	void save(Vet v);
	Vet findById(@Param("id")Integer id);
	
public default void filtrarList() {
	 List<Vet> lista = new ArrayList<Vet>();  
	 // Imprimir la lista filtrado por lastname
	 for (Vet objeto : lista) {
        System.out.println(objeto.getLastName());
    }
}   

public default void filtrarFL() {
	 List<Vet> lista = new ArrayList<Vet>();  
 // Imprimir la lista filtrado por lastname y name
    for (Vet ob : lista) {
        System.out.println(ob.getLastName() + " " + ob.getFirstName());
    }
}    
 

public default List<Vet> filtrarLista(String filtro) {
        List<Vet> resultados = new ArrayList<Vet>();
        for (Vet obj : resultados) {
            if (obj.getFirstName().equals(filtro) || obj.getLastName().equals(filtro)) {
                resultados.add(obj);
            }
        }
        return resultados;
    }


public default void Ordenar() {
	List<Pet> lista = new ArrayList<Pet>();
	Pet elemento1 = new Pet();
	elemento1.setName("John");
	elemento1.setBirthDate(2010-01-01);
	lista.add(elemento1);

	Pet elemento2 = new Pet();
	elemento2.setName("Jane");
	elemento2.setBirthDate(2010-05-03);
	lista.add(elemento2);


	System.out.println("Toda la lista: ");
	// Iterar a través de la lista
	for (Pet obj : lista) {
    System.out.println(obj.getName() + " " + obj.getBirthDate());
	}

	System.out.println("\nResultados filtrados y ordenados: ");
	// Filtrar los elementos nacidos en 2010
	List<Pet> resultados = new ArrayList<Pet>();
	for (Pet obj : lista) {
		if (obj.getBirthDate().withYear(2010)) {
        resultados.add(obj);
		}
	}

	// Iterar a través de los resultados filtrados y ordenados
	for (Pet obj : resultados) {
		System.out.println(obj.getName() + " " + obj.getBirthDate());
	}
}
}

