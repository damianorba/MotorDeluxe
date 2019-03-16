package es.urjc.code.motorDeluxe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

	Anuncio findByAsunto (String asunto);
	Anuncio findByNombre (String nombre);
}
