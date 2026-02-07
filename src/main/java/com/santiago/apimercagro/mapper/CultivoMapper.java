package com.santiago.apimercagro.mapper;

import com.santiago.apimercagro.dto.CultivoDTO;
import com.santiago.apimercagro.dto.SolicitudCompraDTO;
import com.santiago.apimercagro.exception.NotFoundException;
import com.santiago.apimercagro.model.Cultivo;
import com.santiago.apimercagro.model.Producto;
import com.santiago.apimercagro.model.SolicitudCompra;
import com.santiago.apimercagro.repository.CultivoRepository;
import com.santiago.apimercagro.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CultivoMapper {

    private static ProductoRepository productoRepository;
    private static CultivoRepository cultivoRepository;

    public static CultivoDTO toDto(Cultivo cultivo){
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

    public static Cultivo toEntity(CultivoDTO cultivoDTO){
        if(cultivoDTO == null) return null;
        //Obtener producto para la creación del cultivo
        Producto producto = productoRepository.findProductoByNombre(cultivoDTO.getNombreProducto()).orElseThrow(()->
                new NotFoundException("Producto no encontrado: " + cultivoDTO.getNombreProducto()));
        //Obtener el cultivo para la creación de la solicitud
        Cultivo cultivo = cultivoRepository.findCultivoByNombre(cultivoDTO.getNombreCultivo()).orElseThrow(()->
                new NotFoundException("Cultivo no encontrado: " + cultivoDTO.getNombreCultivo()));

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
                .estadoCultivo(cultivoDTO.getEstado())
                .fechaCreacion(cultivoDTO.getFechaCreacion())
                .solicitudesCompras(solicitudesCompras)
                .build();
    }

    public static List<CultivoDTO> toDtoList(List<Cultivo> cultivos){
        if(cultivos == null || cultivos.isEmpty()) return Collections.emptyList();

        return cultivos.stream()
                .map(CultivoMapper::toDto)
                .toList();
    }
}
