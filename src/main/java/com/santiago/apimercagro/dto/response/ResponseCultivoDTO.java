package com.santiago.apimercagro.dto.response;

import com.santiago.apimercagro.enums.EstadoCultivo;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ResponseCultivoDTO {
    private Long idCultivo;
    private String nombreCultivo;
    private String nombreProducto;
    private String descripcion;
    private Integer cantidad;
    private String unidadMedida;
    private BigDecimal precio;
    private EstadoCultivo estado;
    private LocalDate fechaCreacion;
    private List<ResponseSolicitudCompraDTO> solicitudesCompra;
}
