package com.santiago.apimercagro.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductoDTO {
    private Long idProducto;
    private String nombre;
    private BigDecimal precio;
    private String unidadMedida = "kg";
    private LocalDate fechaActualizacion;
}
