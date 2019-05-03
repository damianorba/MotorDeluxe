package es.urjc.code.motorDeluxe;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface OfertaCompraRepository extends CrudRepository<OfertaCompra, Long> {
	
	OfertaCompra findById(long id);
	List<OfertaCompra> findByfOferta(String fOferta);
	List<OfertaCompra> findBycoche(Coche coche);
	void deleteByCoche(Coche coche);
	OfertaCompra findByComprador(User comprador);
	List<OfertaCompra> findByVendedor(User vendedor);

	//Object findByFecha(String string);
}
