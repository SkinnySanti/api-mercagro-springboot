package com.santiago.apimercagro.dto;

import com.santiago.apimercagro.enums.EstadoSolicitud;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RequestSolicitudCompraDTO {
    @NotBlank(message = "El nombre del cultivo obligatorio")
    private String nombreCultivo;

    @Size(message = "Maximo 50 caracteres", max = 50)
    private String mensaje;

    private EstadoSolicitud estado;
}
