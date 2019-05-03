package es.urjc.code.motorDeluxe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

	@Entity
	public class Venta {
		
		
		    @Id
		    @GeneratedValue(strategy = GenerationType.AUTO)
		    private long id;
		    
		    
		    private String fechaVenta;
		    private Double precio_venta;
		   
		    @OneToOne
		    private User vendedor;
		    
		    @OneToOne
		    private User comprador;
		    
		    @OneToOne
		    private Coche coche;
		    
		   
		    
		    protected Venta(){}//constructor para usar el repositorio

		    public Venta(String fechaVenta, Double precio_venta) {

				this.fechaVenta = fechaVenta;
				this.precio_venta = precio_venta;
				this.vendedor = new User();
				this.comprador = new User();
				this.coche = new Coche();
			
			}

			public long getId() {
				return id;
			}

			public void setId(long id) {
				this.id = id;
			}

			public String getFechaVenta() {
				return fechaVenta;
			}

			public void setFechaVenta(String fechaVenta) {
				this.fechaVenta = fechaVenta;
			}

			public Double getPrecio_venta() {
				return precio_venta;
			}

			public void setPrecio_venta(Double precio_venta) {
				this.precio_venta = precio_venta;
			}

			public User getVendedor() {
				return vendedor;
			}

			public void setVendedor(User vendedor) {
				this.vendedor = vendedor;
			}

			public User getComprador() {
				return comprador;
			}

			public void setComprador(User comprador) {
				this.comprador = comprador;
			}

			public Coche getCoche() {
				return coche;
			}

			public void setCoche(Coche coche) {
				this.coche = coche;
			}

		    		    
		    

	}


	


