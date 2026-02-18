package com.santiago.apimercagro.model;

import com.santiago.apimercagro.enums.EstadoCultivo;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cultivo")
public class Cultivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_cultivo")
    private Long idCultivo;

    private String nombre;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "producto_id", referencedColumnName = "id_producto")
    private Producto producto;
    private String descripcion;
    private Integer cantidad;

    @Column(name = "unidad_medida", length = 20)
    private String unidadMedida = "kg";
    private BigDecimal precio;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 20)
    private EstadoCultivo estadoCultivo = EstadoCultivo.REGISTRO;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cultivo")
    private List<SolicitudCompra> solicitudesCompras = new ArrayList<>();
}
