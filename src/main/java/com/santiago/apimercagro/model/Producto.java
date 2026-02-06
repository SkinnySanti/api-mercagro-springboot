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

    @NotNull @Size(max = 20)
    private String nombre;

    @NotNull @Min(0) @Digits(integer = 10, fraction = 2)
    private BigDecimal precio;

    @Column(name = "unidad_medida", length = 20) @Size(max = 20, message = "Maximo 20 caracteres")
    private String unidadMedida = "kg";

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;
}
