package com.santiago.apimercagro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "producto_id", referencedColumnName = "id_producto")
    private Producto producto;

    @Size(max = 50)
    private String descripcion;

    @Positive @NotNull
    private Integer cantidad;

    @Size(max = 20, message = "Maximo 20 caracteres") @Column(name = "unidad_medida", length = 20)
    private String unidadMedida = "kg";

    @NotNull @Min(0) @Digits(integer = 10, fraction = 2)
    private BigDecimal precio;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 20)
    private EstadoCultivo estadoCultivo = EstadoCultivo.REGISTRO;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cultivo")
    private List<SolicitudCompra> solicitudesCompras = new ArrayList<>();
}
