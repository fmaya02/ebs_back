package com.elbuensabor.elbuensabor;

import com.elbuensabor.elbuensabor.entities.*;
import com.elbuensabor.elbuensabor.enums.EstadoPedido;
import com.elbuensabor.elbuensabor.enums.FormaPago;
import com.elbuensabor.elbuensabor.enums.TipoEnvio;
import com.elbuensabor.elbuensabor.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	CommandLineRunner init(ClienteRepository clienteRepository, DomicilioRepository domicilioRepository, PedidoRepository pedidoRepository, FacturaRepository facturaRepository, ArticuloRepository articuloRepository, RubroArticuloRepository rubroArticuloRepository, LocalidadRepository localidadRepository, NotaCreditoRepository notaCreditoRepository ) {

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

			Persona persona1 = Persona.builder()
					.nombre("Geralt")
					.apellido("de Rivia")
					.fechaAlta(new Date())
					.email("geralt.derivia@kaermorhen.com")
					.domicilios(new ArrayList<Domicilio>())
					.build();
			persona1.addDomicilio(domicilio1);
			clienteRepository.save(persona1);

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
					.persona(persona1)
					.domicilioEntrega(domicilio1)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.estado(EstadoPedido.PENDIENTE_PAGO)
					.pedidoDetalles(arraydetalle)
					.build();
			pedidoRepository.save(pedido1);

			Factura factura1 = Factura.builder()
					.fechaComprobante(new Date())
					.nro(1)
					.total(pedido1.getTotal())
					.fechaFacturacion(new Date())
					.formaPago(FormaPago.EFECTIVO)
					.pedido(pedido1)
					.build();
			facturaRepository.save(factura1);
			NotaCredito newNotaCredito = NotaCredito.builder()
					.factura(factura1)
					.fechaComprobante(new Date())
					.nro(2)
					.total(factura1.getPedido().getTotal())
					.build();
			notaCreditoRepository.save(newNotaCredito);
			System.out.println("Corriendo API\n");

		};
	}
}
