package com.examt2.examt2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.examt2.examt2.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

	static Libro findByTitulo(String tituloLibro) {
		// TODO Auto-generated method stub
		return null;
	}
}
