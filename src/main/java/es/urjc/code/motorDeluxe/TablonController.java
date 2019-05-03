package es.urjc.code.motorDeluxe;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.code.motorDeluxe.Anuncio;
import es.urjc.code.motorDeluxe.AnuncioRepository;
import es.urjc.code.motorDeluxe.User;
import es.urjc.code.motorDeluxe.UserRepository;
import es.urjc.code.motorDeluxe.Coche;
import es.urjc.code.motorDeluxe.CocheRepository;



	
	@Controller
	public class TablonController {
		
		//Si se abre la URL http://127.0.0.1:8080/h2-console y se configura
		//la URL JDBC con el valor jdbc:h2:mem:testdb se puede acceder a la 
		//base de datos de la aplicación

		@Autowired
		private AnuncioRepository repository;
		
		@Autowired
		private CocheRepository cocheRepository;
		
		@Autowired
		private UserRepository usuarioRepository;
		
		@Autowired
		private OfertaCompraRepository ofertaCompraRepository;
		
		
		@Autowired
		private VentaRepository ventaRepository;

		@PostConstruct
		public void init() {
			
			/*Rellenamos la base con un administrador, dos usuarios que venden y un usuario que va a realizar la compra*/
			
			
			User u1 = new User("dortiz","pass","Damian Ortiz Barahona","50574692D","demn007@gmail.com","648799392","ROLE_USER");
			User u2 = new User("prodriguez","pass","Paula Rodriguez de Zoluaga","73263327S","prr.zoluaga@wanadoo.com","64537872", "ROLE_USER");
			User u3 = new User("btercero","pass","Benedicto tercero","59563719W","benedict@plus.com","634522718","ROLE_USER"); 
			User u4 = new User("valvarez","pass","Victor Alvarez","83826171S","valva@pl0s.com","739993816","ROLE_USER", "ROLE_ADMIN");
			User u5 = new User("eestuchito","pass","Estuche Estuchito","83826171S","sta@plas.com","7651591","ROLE_USER");
			
		
			/*Insertamos 2 coches*/
			
			Coche c1 = new Coche("0830DTJ", "OPEL","ASTRA GTC","NEGRO","no",3,30.000,2009,12000.00,u5);
			Coche c2 = new Coche("2052GCP", "CITROEN","C4","BLANCO","techo solar",5,10.000,2011,15000.00,u2);
			
			
			/*Persisto a  los usuarios*/
			

			usuarioRepository.save(u1);
			usuarioRepository.save(u2);
			usuarioRepository.save(u3);
			usuarioRepository.save(u4);
			usuarioRepository.save(u5);
			
	        /*Persisto los coches */
			
			cocheRepository.save(c1);
			cocheRepository.save(c2);
			
			/*Creamos anuncios y los asignamos a los usuarios*/
			
			Anuncio a1 = new Anuncio("Opel Astra GTC de oprotunidad","coche en prefecto estado");
			Anuncio a2 = new Anuncio("Citroen C4","Vendo monovolumen");
			a1.setUsuario(u1);
			a2.setUsuario(u2);
			a1.setCoche(c1);
			a2.setCoche(c2);
			
			/*Creamor una oferta de compra */
			
			OfertaCompra comp1 = new OfertaCompra("17/03/2019", 10.000);
			comp1.setCoche(c1);
			comp1.setComprador(u1);
			comp1.setVendedor(c1.getPropietario());
			
			ofertaCompraRepository.save(comp1);
			
			//Creamos Oferta
			
			OfertaCompra comp2 = new OfertaCompra("04/04/2019", 17.400);
			comp2.setCoche(c2);
			comp2.setComprador(u2);
			comp2.setVendedor(c2.getPropietario());
			
			ofertaCompraRepository.save(comp2);
			
			//Guardamos los anuncios p1,p2,p3
			repository.save(a1);
			repository.save(a2);
			
			
		}
		

		@RequestMapping("/")
		public String tablon(Model model, Pageable page,HttpServletRequest request) {

			model.addAttribute("anuncios", repository.findAll(page));
			model.addAttribute("usuarios", usuarioRepository.count());
			model.addAttribute("coches", cocheRepository.count());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			return "index";
		}
		
	    @GetMapping("/login")
	    public String login() {
	    	return "login";
	    }
	    
	    @GetMapping("/nuevoUsuario")
	    public String nuevoUsuario() {
	    	return "nuevoUsuario";
	    }
	    
	    @GetMapping("/nuevoAnuncio")
	    public String nuevoAnuncio() {
	    	return "nuevoAnuncio";
	    }
	    @GetMapping("/admin")
	    public String adminpane() {
	    	return "admin";
	    }
	    
	    @GetMapping("/loginerror")
	    public String loginerror() {
	    	return "loginerror";
	    }
		
		@RequestMapping("/oferta")
		public String tablon(Model model,HttpServletRequest request) {
			String nombre = request.getUserPrincipal().getName();
			User u = usuarioRepository.findByName(nombre);
			model.addAttribute("ofertas", ofertaCompraRepository.findByVendedor(u));
			return "ver_ofertas";
		}

		@RequestMapping("/anuncio/nuevo")
		public String nuevoAnuncio(Model model, @RequestParam String nombre, 
				@RequestParam String asunto,@RequestParam String matricula,@RequestParam String marca,
				@RequestParam String modelo,@RequestParam String color,@RequestParam String extras,
				@RequestParam int numPuertas,@RequestParam double kilometros,@RequestParam int anioMatriculacion,
				@RequestParam double precio,HttpServletRequest request) {

			User u = usuarioRepository.findByName(request.getUserPrincipal().getName());
			
			
			
			Coche c = new Coche(matricula, marca,modelo,color,extras,numPuertas,kilometros,anioMatriculacion,precio,u);
			cocheRepository.save(c);
			
			Anuncio a = new Anuncio(nombre,asunto);
			
			a.setCoche(c);
			a.setUsuario(u);
			
			repository.save(a);
			
			return "anuncio_guardado";

		}
		
		@RequestMapping("/usuario/nuevo")
		public String nuevoUsuario(Model model,@RequestParam String name,@RequestParam String password, @RequestParam String nombre_completo, 
				@RequestParam String dni,@RequestParam String email,@RequestParam String telefono) {

			
			User u = new User(name,password,nombre_completo,dni,email,telefono,"ROLE_USER");
			usuarioRepository.save(u);
			

			
			return "usuario_guardado";

		}		
		
		@RequestMapping("/coche/nuevo")
		public String nuevoAnuncio(Model model, Coche coche) {

			cocheRepository.save(coche);
			return "anuncioNuevo";

		}
		
		@RequestMapping("/anuncio/{id}")
		public String verAnuncio(Model model, @PathVariable long id) {
			
			Anuncio anuncio = repository.findById(id);
			Coche coche = anuncio.getCoche();
			model.addAttribute("anuncio", anuncio);
			model.addAttribute("coche", coche);
			return "ver_anuncio";
		}
		
		
		@PostMapping("/usuario/nuevo")
		public String nuevoUsuario(Model model, User usuario,HttpServletRequest request) {
			
			
			usuario.setRoles((new ArrayList<>(Arrays.asList("ROLE_USER"))));
			usuarioRepository.save(usuario);
			
			return "usuario_guardado";
			
		}
		
		
		@RequestMapping("/vender")
		public String comprar(Model model,@RequestParam long idc,@RequestParam long ido) {
			
			OfertaCompra ofe = ofertaCompraRepository.findById(ido);
			Coche coche = cocheRepository.findById(idc);
			List<OfertaCompra> lista = ofertaCompraRepository.findBycoche(coche);
			
			//Registramos venta
			Venta venta = new Venta(ofe.getFechaOferta(),ofe.getPrecioOferta());
			venta.setCoche(coche);
			venta.setComprador(ofe.getComprador());
			venta.setVendedor(coche.getPropietario());			
			ventaRepository.save(venta);
			
			//Borramos Anuncio

			Anuncio anu = repository.findByCoche(coche);
			repository.delete(anu.getId());
			
			//Borramos Ofertas
			for(OfertaCompra str : lista)
				{
					ofertaCompraRepository.delete(str);
				}
			
			return ("venta_realizada");
		}
		
		
		@RequestMapping("/anuncio/oferta")
		public String nuevosAnuncio(Model model, @RequestParam String fechaOferta, @RequestParam double precioOferta, @RequestParam long id,HttpServletRequest request ) {

			OfertaCompra ofer = new OfertaCompra(fechaOferta, precioOferta);
			Coche coche3 = cocheRepository.findById(id);
			
			ofer.setCoche(coche3);
			ofer.setComprador(usuarioRepository.findByName(request.getUserPrincipal().getName()));
			ofer.setVendedor(coche3.getPropietario());
			coche3.setPropietario(usuarioRepository.findByName(request.getUserPrincipal().getName()));
			coche3.setPrecio(precioOferta);
			ofertaCompraRepository.save(ofer);

			model.addAttribute("coche", coche3);
			
			return "oferta_realizada";

		}
		
		@RequestMapping("admin/eliminarUsuario")
		public String eliminaUsuario(Model model, @RequestParam String username)
		{
			User usu = usuarioRepository.findByName(username);
			usuarioRepository.delete(usu.getId());
			return "usuario_eliminado";
			
		}
		

		@RequestMapping("admin/borrar_anuncio")
		public String eliminaAnuncio(Model model, @RequestParam String matricula)
		{
			Coche co = cocheRepository.findByMatricula(matricula);
			cocheRepository.delete(co.getId());
			return "anuncio_eliminado";
			
		}
		
		@RequestMapping("admin/usuario/nuevo")
		public String nuevosUsuario(Model model,@RequestParam String name,@RequestParam String password, @RequestParam String nombre_completo, 
				@RequestParam String dni,@RequestParam String email,@RequestParam String telefono) {

			
			User u = new User(name,password,nombre_completo,dni,email,telefono,"ROLE_USER");
			usuarioRepository.save(u);
			

			
			return "usuario_guardado";
		}
		
		@RequestMapping("admin/anuncio/nuevo")
		public String nuevosAnuncio(Model model, @RequestParam String nombre, 
				@RequestParam String asunto,@RequestParam String matricula,@RequestParam String marca,
				@RequestParam String modelo,@RequestParam String color,@RequestParam String extras,
				@RequestParam int numPuertas,@RequestParam double kilometros,@RequestParam int anioMatriculacion,
				@RequestParam double precio,HttpServletRequest request) {

			User u = usuarioRepository.findByName(request.getUserPrincipal().getName());
			
			
			
			Coche c = new Coche(matricula, marca,modelo,color,extras,numPuertas,kilometros,anioMatriculacion,precio,u);
			cocheRepository.save(c);
			
			Anuncio a = new Anuncio(nombre,asunto);
			
			a.setCoche(c);
			a.setUsuario(u);
			
			repository.save(a);
			
			return "anuncio_guardado";

		}
	
	}
	


