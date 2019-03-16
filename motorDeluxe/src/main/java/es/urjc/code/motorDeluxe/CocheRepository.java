package es.urjc.code.motorDeluxe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CocheRepository extends JpaRepository<Coche, Long> {
	
	Coche findByMatricula(String matricula);
	Coche findByMarca(String marca);

}
