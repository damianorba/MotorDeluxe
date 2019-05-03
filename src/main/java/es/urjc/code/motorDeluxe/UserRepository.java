package es.urjc.code.motorDeluxe;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findById(long id);
	//List<User> findByDni(String dni);
	User findByName(String name);
}
