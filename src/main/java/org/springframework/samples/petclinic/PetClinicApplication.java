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

package org.springframework.samples.petclinic;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.samples.petclinic.vet.Specialty;
import org.springframework.samples.petclinic.vet.SpecialtyRepository;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetRepository;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 *
 */
//@Slf4j
@SpringBootApplication
@ImportRuntimeHints(PetClinicRuntimeHints.class)
public class PetClinicApplication {
/*
 1.Crear un objeto Vet sin Speciality
2.Persistir el objeto Vet en BBDD
3.Consultar por ID y comprobar que se ha creado correctamente
4.Editar el elemento recién creado para añadir una Speciality concreta
5.Listar todos los veterinarios existentes
 */
/*
4 Obtener las mascotas nacidas en 2010 ordenadas por fecha de nacimiento ascendente.
5 Crear 3 visitas nuevas para diferentes mascotas
6 Obtener todas las visitas para una mascota.
7 Obtener las 4 visitas más recientes de todo el sistema.
*/
	public static void main(String[] args) {
		SpringApplication.run(PetClinicApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoVetRepository(VetRepository vetRepository, SpecialtyRepository specialtyRepository) {   
		return (args) -> {
			Vet v = new Vet();
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Introduce nombre");
			String nombre = sc.nextLine();
			v.setFirstName(nombre);
			System.out.println("Introduce apellido");
			String ape= sc.nextLine();
			v.setLastName(ape);
			
			vetRepository.save(v);
			vetRepository.findById(v.getId());
			Specialty s = specialtyRepository.findById(1);
			v.addSpecialty(s);
			vetRepository.save(v);
			
			for(Vet vet : vetRepository.findAll()) {
			//	log.info("Vet:" + vet.getFirstName());
				
			}
			
			vetRepository.filtrarFL() ;
			vetRepository.filtrarList();
			
			System.out.println("Introduce nombre o ape ");
			String filtro = sc.nextLine();
			
			vetRepository.filtrarLista(filtro);
			vetRepository.Ordenar();
		
		};
	}
}

