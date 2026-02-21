package com.santiago.apimercagro.dto.request;

import com.santiago.apimercagro.enums.EstadoCultivo;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RequestCultivoDTO {
    private Long idCultivo;
    private String nombreCultivo;
    private String nombreProducto;

    @Size(max = 50, message = "Maximo 50 caracteres")
    private String descripcion;

    @PositiveOrZero(message = "El numero no puede ser negativo")
    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;

    @Size(max = 20, message = "Maximo 20 caracteres")
    private String unidadMedida;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin("0.01")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal precio;

    @NotNull(message = "El estado es obligatorio")
    private EstadoCultivo estado;
}
