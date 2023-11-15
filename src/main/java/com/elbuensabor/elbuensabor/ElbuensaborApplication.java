package com.elbuensabor.elbuensabor;

import com.elbuensabor.elbuensabor.entities.*;
import com.elbuensabor.elbuensabor.enums.EstadoPedido;
import com.elbuensabor.elbuensabor.enums.TipoEnvio;
import com.elbuensabor.elbuensabor.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.elbuensabor.elbuensabor.repositories.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class ElbuensaborApplication {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private DomicilioRepository domicilioRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private FacturaRepository facturaRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	@Autowired
	private LocalidadRepository localidadRepository;
	@Autowired
	private RubroArticuloRepository rubroArticuloRepository;

	public static void main(String[] args) {
		SpringApplication.run(ElbuensaborApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ClienteRepository clienteRepository, DomicilioRepository domicilioRepository, PedidoRepository pedidoRepository, FacturaRepository facturaRepository, ArticuloRepository articuloRepository, RubroArticuloRepository rubroArticuloRepository, LocalidadRepository localidadRepository) {
		return args -> {

			Localidad localidad1 = Localidad.builder()
					.denominacion("Kaer Morhen")
					.fechaAlta(new Date())
					.build();
			localidadRepository.save(localidad1);

			Domicilio domicilio1 = Domicilio.builder()
					.calle("Novigrado")
					.codigoPostal(5500)
					.numero(123)
					.localidad(localidad1)
					.build();
			domicilioRepository.save(domicilio1);

			Cliente cliente1 = Cliente.builder()
					.nombre("Geralt")
					.apellido("de Rivia")
					.fechaAlta(new Date())
					.email("geralt.derivia@kaermorhen.com")
					.domicilios(new ArrayList<Domicilio>())
					.build();
			cliente1.addDomicilio(domicilio1);
			clienteRepository.save(cliente1);

			RubroArticulo rubroArticulo1 = RubroArticulo.builder()
					.fechaAlta(new Date())
					.denominacion("Pizza")
					.build();
			rubroArticuloRepository.save(rubroArticulo1);

			Articulo articulo1 = Articulo.builder()
					.costo(new BigDecimal(30000))
					.fechaAlta(new Date())
					.denominacion("Pizza con piña")
					.descripcion("que asco")
					.urlImagen("https://www.losandes.com.ar/resizer/flZBRU0x5cqahizPYN75eLEVMQQ=/980x640/smart/filters:quality(75):format(webp)/cloudfront-us-east-1.images.arcpublishing.com/grupoclarin/6ECEOA47QJH3LPLTJ6NG5JWBPU.jpg")
					.articuloRubro(rubroArticulo1)
					.build();
			articuloRepository.save(articulo1);

			ArrayList<DetallePedido> arraydetalle= new ArrayList<DetallePedido>();
			DetallePedido detalle1 = DetallePedido.builder()
					.articulo(articulo1)
					.subtotalCosto(articulo1.getCosto())
					.subtotal(articulo1.getCosto())
					.cantidad(1)
					.build();
			arraydetalle.add(detalle1);

			Pedido pedido1 = Pedido.builder()
					.fechaAlta(new Date())
					.fechaPedido(new Date())
					.cliente(cliente1)
					.domicilioEntrega(domicilio1)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.estado(EstadoPedido.PENDIENTE_PAGO)
					.pedidoDetalles(arraydetalle)
					.build();
			pedidoRepository.save(pedido1);



			System.out.println("Corriendo API\n");
		};
	}
}
