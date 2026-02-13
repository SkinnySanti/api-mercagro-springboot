package com.santiago.apimercagro.mapper;

import com.santiago.apimercagro.dto.CultivoDTO;
import com.santiago.apimercagro.dto.SolicitudCompraDTO;
import com.santiago.apimercagro.exception.NotFoundException;
import com.santiago.apimercagro.model.*;
import com.santiago.apimercagro.repository.CultivoRepository;
import com.santiago.apimercagro.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CultivoMapper {

    private final ProductoRepository productoRepository;
    private final CultivoRepository cultivoRepository;

    public CultivoDTO toDto(Cultivo cultivo){
        if (cultivo == null) return null;

        List<SolicitudCompraDTO> solicitudesCompra = cultivo.getSolicitudesCompras().stream()
                .map(solicitudCompra -> SolicitudCompraDTO.builder()
                        .nombreCultivo(cultivo.getNombre())
                        .nombreProducto(cultivo.getProducto().getNombre())
                        .mensaje(solicitudCompra.getMensaje())
                        .estado(solicitudCompra.getEstadoSolicitud())
                        .fechaCreacion(solicitudCompra.getFechaCreacion())
                        .build()
                ).toList();

        return CultivoDTO.builder()
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

    public Cultivo toEntity(CultivoDTO cultivoDTO){
        if(cultivoDTO == null) return null;
        //Obtener producto para la creación del cultivo
        Producto producto = productoRepository.findProductoByNombre(cultivoDTO.getNombreProducto()).orElseThrow(()->
                new NotFoundException("Producto no encontrado: " + cultivoDTO.getNombreProducto()));
        //Obtener el cultivo para la creación de la solicitud
        Cultivo cultivo = cultivoRepository.findCultivoByNombre(cultivoDTO.getNombreCultivo()).orElseThrow(()->
                new NotFoundException("Cultivo no encontrado: " + cultivoDTO.getNombreCultivo()));

        //Se valida que el estado ingresado sea el correcto
        EstadoCultivo estadoCultivo;
        try{
            estadoCultivo = cultivoDTO.getEstado();
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Estado invalido: " + cultivoDTO.getEstado() +
                    ". Valores permitidos: " + Arrays.toString(EstadoCultivo.values()));
        }
        var solicitudesCompras = cultivoDTO.getSolicitudesCompra().stream()
                .map(solicitudCompraDTO ->
                        SolicitudCompra.builder()
                                .cultivo(cultivo)
                                .mensaje(solicitudCompraDTO.getMensaje())
                                .estadoSolicitud(solicitudCompraDTO.getEstado())
                                .fechaCreacion(solicitudCompraDTO.getFechaCreacion())
                                .build()
                ).toList();

        return Cultivo.builder()
                .nombre(cultivoDTO.getNombreCultivo())
                .producto(producto)
                .descripcion(cultivoDTO.getDescripcion())
                .cantidad(cultivoDTO.getCantidad())
                .unidadMedida(cultivoDTO.getUnidadMedida())
                .precio(cultivoDTO.getPrecio())
                .estadoCultivo(estadoCultivo)
                .fechaCreacion(cultivoDTO.getFechaCreacion())
                .solicitudesCompras(solicitudesCompras)
                .build();
    }

    public List<CultivoDTO> toDtoList(List<Cultivo> cultivos){
        if(cultivos == null || cultivos.isEmpty()) return Collections.emptyList();

        return cultivos.stream()
                .map(this::toDto)
                .toList();
    }
}
