package com.santiago.apimercagro.dto;

import jakarta.validation.constraints.*;
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
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 20, message = "Maximo 20 caracteres")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio no puede ser negativo o 0")
    @Digits(integer = 10, fraction = 2, message = "Formato invalido")
    private BigDecimal precio;

    @Size(max = 20, message = "Maximo 20 caracteres")
    private String unidadMedida = "kg";

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fechaActualizacion;
}
