package com.elbuensabor.elbuensabor.entities;

import com.elbuensabor.elbuensabor.enums.EstadoPedido;
import com.elbuensabor.elbuensabor.enums.FormaPago;
import com.elbuensabor.elbuensabor.enums.TipoEnvio;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Agrega la propiedad cantidad
    private Integer cantidad;

    @NotNull
    @Column(name = "fecha_pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;

    @NotNull
    @Column(name = "hora_estimada_finalizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaEstimadaFinalizacion;

    @NotNull
    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    @NotNull
    @Column(name = "total_costo", precision = 10, scale = 2)
    private BigDecimal totalCosto;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @NotNull
    @Column(name = "tipo_envio")
    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoEnvio;

    @NotNull
    @Column(name = "forma_pago")
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    @ManyToOne()
    @Nullable
    @JoinColumn(name = "id_domicilio_entrega")
    private Domicilio domicilioEntrega;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    List<DetallePedido> pedidoDetalles=new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;

    public void setPedidoDetalles(List<DetallePedido> detallesPedido){
        this.pedidoDetalles=detallesPedido;
    }
}
