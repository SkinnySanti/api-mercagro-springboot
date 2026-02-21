package com.santiago.apimercagro.dto.response;


import com.santiago.apimercagro.enums.EstadoSolicitud;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ResponseSolicitudCompraDTO {
    private Long idSolicitud;
    private String nombreCultivo;
    private String nombreProducto;
    private String mensaje;
    private EstadoSolicitud estado;
    private LocalDate fechaCreacion;
}
