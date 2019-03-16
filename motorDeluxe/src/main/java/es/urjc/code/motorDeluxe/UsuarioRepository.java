package es.urjc.code.motorDeluxe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByNombre(String nombre);
	Usuario findByDni (String dni);
	

}
