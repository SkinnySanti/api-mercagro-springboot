package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {
    List<ProductoDTO> listarProductos();
    ProductoDTO buscarProductoPorId(Long id);
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO);
    void eliminarProducto(Long id);
}
