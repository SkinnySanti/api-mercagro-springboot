package com.santiago.apimercagro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;
    @Column(unique = true)
    private String nombre;
    private BigDecimal precio;

    @Column(name = "unidad_medida", length = 20)
    private String unidadMedida = "kg";

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;
}
