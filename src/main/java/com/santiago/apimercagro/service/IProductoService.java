package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.ProductoDTO;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<ProductoDTO> listarProductos();
    Optional<ProductoDTO> buscarProductoPorId(Long id);
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO);
    void eliminarProducto(Long id);
}
