package es.urjc.code.motorDeluxe;


import org.springframework.data.repository.CrudRepository;

public interface CocheRepository extends CrudRepository<Coche, Long> {
	
	Coche findByMatricula(String matricula);
	Coche findByMarca(String marca);
	Coche findById(long id);

}
