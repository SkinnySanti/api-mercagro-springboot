package com.santiago.apimercagro.dto;

import com.santiago.apimercagro.model.EstadoSolicitud;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SolicitudCompraDTO {
    private Long idSolicitudCompra;
    private String nombreCultivo;
    private String nombreProducto;
    private String mensaje;
    private EstadoSolicitud estado;
    private LocalDate fechaCreacion;
}
