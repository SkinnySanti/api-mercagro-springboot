package com.santiago.apimercagro.mapper;

import com.santiago.apimercagro.dto.RequestSolicitudCompraDTO;
import com.santiago.apimercagro.dto.response.ResponseSolicitudCompraDTO;
import com.santiago.apimercagro.model.Cultivo;
import com.santiago.apimercagro.model.SolicitudCompra;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class SolicitudCompraMapper {

    public ResponseSolicitudCompraDTO toDto(SolicitudCompra solicitudCompra){
        if(solicitudCompra == null) return null;

        return ResponseSolicitudCompraDTO.builder()
                .idSolicitud(solicitudCompra.getIdSolicitud())
                .nombreCultivo(solicitudCompra.getCultivo().getNombre())
                .nombreProducto(solicitudCompra.getCultivo().getProducto().getNombre())
                .mensaje(solicitudCompra.getMensaje())
                .estado(solicitudCompra.getEstadoSolicitud())
                .fechaCreacion(solicitudCompra.getFechaCreacion())
                .build();
    }

    public SolicitudCompra toEntity (RequestSolicitudCompraDTO requestSolicitudCompraDTO, Cultivo cultivo){
        if (requestSolicitudCompraDTO == null) return null;

        return SolicitudCompra.builder()
                .cultivo(cultivo)
                .mensaje(requestSolicitudCompraDTO.getMensaje())
                .estadoSolicitud(requestSolicitudCompraDTO.getEstado())
                .build();
    }

    public List<ResponseSolicitudCompraDTO> toDtoList (List<SolicitudCompra> solicitudCompras){
        if (solicitudCompras == null || solicitudCompras.isEmpty()) return Collections.emptyList();

        return solicitudCompras.stream()
                .map(this::toDto)
                .toList();
    }
}
