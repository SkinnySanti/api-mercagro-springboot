package com.santiago.apimercagro.mapper;

import com.santiago.apimercagro.dto.request.RequestCultivoDTO;
import com.santiago.apimercagro.dto.response.ResponseCultivoDTO;
import com.santiago.apimercagro.dto.response.ResponseSolicitudCompraDTO;
import com.santiago.apimercagro.enums.EstadoCultivo;
import com.santiago.apimercagro.model.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class CultivoMapper {
    public ResponseCultivoDTO toDto(Cultivo cultivo){
        if (cultivo == null) return null;

        List<ResponseSolicitudCompraDTO> solicitudesCompra = cultivo.getSolicitudesCompras().stream()
                .map(solicitudCompra -> ResponseSolicitudCompraDTO.builder()
                        .nombreCultivo(cultivo.getNombre())
                        .nombreProducto(cultivo.getProducto().getNombre())
                        .mensaje(solicitudCompra.getMensaje())
                        .estado(solicitudCompra.getEstadoSolicitud())
                        .fechaCreacion(solicitudCompra.getFechaCreacion())
                        .build()
                ).toList();

        return ResponseCultivoDTO.builder()
                .nombreCultivo(cultivo.getNombre())
                .nombreProducto(cultivo.getProducto().getNombre())
                .descripcion(cultivo.getDescripcion())
                .cantidad(cultivo.getCantidad())
                .unidadMedida(cultivo.getUnidadMedida())
                .precio(cultivo.getPrecio())
                .estado(cultivo.getEstadoCultivo())
                .fechaCreacion(cultivo.getFechaCreacion())
                .solicitudesCompra(solicitudesCompra)
                .build();
    }

    public Cultivo toEntity(RequestCultivoDTO requestCultivoDTO, Producto producto){
        if(requestCultivoDTO == null) return null;

        //Se valida que el estado ingresado sea el correcto
        EstadoCultivo estadoCultivo;
        try{
            estadoCultivo = requestCultivoDTO.getEstado();
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Estado invalido: " + requestCultivoDTO.getEstado() +
                    ". Valores permitidos: " + Arrays.toString(EstadoCultivo.values()));
        }

        return Cultivo.builder()
                .nombre(requestCultivoDTO.getNombreCultivo())
                .producto(producto)
                .descripcion(requestCultivoDTO.getDescripcion())
                .cantidad(requestCultivoDTO.getCantidad())
                .unidadMedida(requestCultivoDTO.getUnidadMedida())
                .precio(requestCultivoDTO.getPrecio())
                .estadoCultivo(estadoCultivo)
                .build();
    }

    public List<ResponseCultivoDTO> toDtoList(List<Cultivo> cultivos){
        if(cultivos == null || cultivos.isEmpty()) return Collections.emptyList();

        return cultivos.stream()
                .map(this::toDto)
                .toList();
    }
}
