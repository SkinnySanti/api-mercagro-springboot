package com.santiago.apimercagro.dto;

import com.santiago.apimercagro.model.EstadoCultivo;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CultivoDTO {
    private Long idCultivo;
    private String nombreCultivo;
    private String nombreProducto;
    private String descripcion;
    private Integer cantidad;
    private String unidadMedida;
    private BigDecimal precio;
    private EstadoCultivo estado;
    private LocalDate fechaCreacion;
    private List<SolicitudCompraDTO> solicitudesCompra;
}
