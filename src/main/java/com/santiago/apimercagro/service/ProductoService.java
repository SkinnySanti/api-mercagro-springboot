package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.ProductoDTO;
import com.santiago.apimercagro.exception.NotFoundException;
import com.santiago.apimercagro.mapper.ProductoMapper;
import com.santiago.apimercagro.model.Producto;
import com.santiago.apimercagro.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll().stream()
                .map(ProductoMapper::toDto)
                .toList();
    }

    @Override
    public ProductoDTO buscarProductoPorId(Long id) {
        Producto productoEntontrado = productoRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Producto no encontrado"));
        return ProductoMapper.toDto(productoEntontrado);
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Producto producto = ProductoMapper.toEntity(productoDTO);
        return ProductoMapper.toDto(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Producto no encontrado"));
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setUnidadMedida(productoDTO.getUnidadMedida());
        producto.setFechaActualizacion(productoDTO.getFechaActualizacion());

        return ProductoMapper.toDto(productoRepository.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {
        if(!productoRepository.existsById(id)){
            throw new NotFoundException("Producto no encontrado para eliminar");
        }
        productoRepository.deleteById(id);
    }
}
