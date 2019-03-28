package com.utn.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;

import com.utn.entity.PeliculaEntity;
import com.utn.model.Pelicula;

//capa que se ocupa de conectar al rest con la base de datos
@Component
public class PeliculaData {

	// tenemos que trabajar con la pelicula entity
	// genera el bean a partir de el persistance.xml
	EntityManagerFactory persistence = Persistence.createEntityManagerFactory("JPAUTN");
	// generamos el tunel entre la base de datos y nuestro obj entityManager
	EntityManager et = persistence.createEntityManager();

	public Pelicula peliculaXId(int id) {

		PeliculaEntity pentity = et.find(PeliculaEntity.class, id);
		// una vez recuperamos la entidad la mapeamos al objeto de salida
		Pelicula p = new Pelicula();
		p.setTitulo(pentity.getTitulo());
		p.setDescripcion(pentity.getDescripcion());
		return p;

	}

	public String guardarPelicula(Pelicula pelicula) {

		try {
			et.getTransaction().begin();

			PeliculaEntity pe = new PeliculaEntity();

			pe.setTitulo(pelicula.getTitulo());
			pe.setDescripcion(pelicula.getDescripcion());
			return "Guardo bien";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "Hubo problemas al guardar";
		}

	}

}
