package com.santiago.apimercagro.mapper;

import com.santiago.apimercagro.dto.ProductoDTO;
import com.santiago.apimercagro.model.Producto;

public class ProductoMapper {
    public ProductoDTO toDto(Producto producto){
        if(producto == null) return null;

        return ProductoDTO.builder()
                .idProducto(producto.getIdProducto())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .unidadMedida(producto.getUnidadMedida())
                .fechaActualizacion(producto.getFechaActualizacion())
                .build();
    }

    public Producto toEntity(ProductoDTO productoDTO){
        if(productoDTO == null) return null;

        return Producto.builder()
                .idProducto(productoDTO.getIdProducto())
                .nombre(productoDTO.getNombre())
                .precio(productoDTO.getPrecio())
                .unidadMedida(productoDTO.getUnidadMedida())
                .fechaActualizacion(productoDTO.getFechaActualizacion())
                .build();
    }
}
