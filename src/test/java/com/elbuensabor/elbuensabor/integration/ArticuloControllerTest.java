package integration;

import com.elbuensabor.elbuensabor.ElbuensaborApplication;
import com.elbuensabor.elbuensabor.entities.Articulo;
import com.elbuensabor.elbuensabor.repositories.PedidoRepository;
import com.elbuensabor.elbuensabor.repositories.RubroArticuloRepository;
import com.elbuensabor.elbuensabor.services.ArticuloServiceImpl;
import com.elbuensabor.elbuensabor.services.PedidoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import com.elbuensabor.elbuensabor.entities.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ElbuensaborApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
class ArticuloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticuloServiceImpl articuloService;

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private RubroArticuloRepository rubroArticuloRepository;

    @Test
    void findMostSold() throws Exception {
        RubroArticulo rubroArticulo1 = RubroArticulo.builder()
                .denominacion("Lomo")
                .build();

        Articulo articulo1 = Articulo.builder()
                .denominacion("Lomo pizza")
                .articuloRubro(rubroArticulo1)
                .build();

        ArrayList<DetallePedido> arraydetalle= new ArrayList<DetallePedido>();
        DetallePedido detalle1 = DetallePedido.builder()
                .articulo(articulo1)
                .cantidad(23)
                .build();
        arraydetalle.add(detalle1);

        String fechaInicioString = "2003-01-01 15:30:00";
        String fechaFinString = "2030-01-20 15:30:30";
        String fechaPedidoString = "2023-01-01 00:00:00";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date fechaInicio = dateFormat.parse(fechaInicioString);
        Date fechaFin = dateFormat.parse(fechaFinString);
        Date fechaPedido = dateFormat.parse(fechaPedidoString);

        Pedido pedido1 = Pedido.builder()
                .fechaPedido(fechaPedido)
                .pedidoDetalles(arraydetalle)
                .build();
        try {
            rubroArticuloRepository.save(rubroArticulo1);
            articuloService.save(articulo1);
        }
        catch (Exception e) {
        }
        pedidoRepository.save(pedido1);

        this.mockMvc.perform(get("/ebs/articulo/findMostSold")
                        .param("date1", fechaInicioString)
                        .param("date2", fechaFinString)
                        .param("page", "0")
                        .param("size", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].denominacionArticulo", is("Lomo pizza")))
                .andExpect(jsonPath("$[0].cantidad", is(23)));

    }
}