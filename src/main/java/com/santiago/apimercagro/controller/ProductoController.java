package com.santiago.apimercagro.controller;

import com.santiago.apimercagro.dto.ProductoDTO;
import com.santiago.apimercagro.service.IProductoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiMercagro/v1/producto")
@Slf4j
public class ProductoController {


    private final IProductoService productoService;

    public ProductoController(IProductoService iProductoService) {
        this.productoService = iProductoService;
    }

    @GetMapping
    ResponseEntity<List<ProductoDTO>> listarProdcutos (){
        log.info("Listando usuarios...");
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductoDTO> buscarPorId(@PathVariable Long id){
        log.info("Buscando producto con id: {}", id);
        return ResponseEntity.ok(productoService.buscarProductoPorId(id));
    }

    @PostMapping
    ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO){
        log.info("Creando producto...");
        ProductoDTO productoCreado = productoService.crearProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id,
                                                   @RequestBody ProductoDTO productoDTO){
        log.info("Actualizando producto con id: {}", id);
        ProductoDTO productoActualizado = productoService.actualizarProducto(id, productoDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        log.info("Eliminando producto...");
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
