package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.CultivoDTO;
import com.santiago.apimercagro.exception.NotFoundException;
import com.santiago.apimercagro.mapper.CultivoMapper;
import com.santiago.apimercagro.model.Cultivo;
import com.santiago.apimercagro.enums.EstadoCultivo;
import com.santiago.apimercagro.model.Producto;
import com.santiago.apimercagro.repository.CultivoRepository;
import com.santiago.apimercagro.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultivoService implements ICultivoService{


    private final CultivoRepository cultivoRepository;
    private final ProductoRepository productoRepository;
    private final CultivoMapper cultivoMapper;

    public CultivoService(CultivoRepository cultivoRepository, ProductoRepository productoRepository,
                          CultivoMapper cultivoMapper) {
        this.cultivoRepository = cultivoRepository;
        this.productoRepository = productoRepository;
        this.cultivoMapper = cultivoMapper;
    }

    @Override
    public List<CultivoDTO> listarCultivos() {
        return cultivoMapper.toDtoList(cultivoRepository.findAll());
    }

    @Override
    public CultivoDTO buscarCultivoPorId(Long id) {
        Cultivo cultivoEncontrado = cultivoRepository.findById(id).orElseThrow(()->
                new NotFoundException("Cultivo no encontrado"));

        return cultivoMapper.toDto(cultivoEncontrado);
    }

    @Override
    public CultivoDTO crearCultivo(CultivoDTO cultivoDTO) {
        //Verificaiones para creacion del cultivo
        if(cultivoDTO == null) throw new RuntimeException("CultivoDto es null");
        //Verificacion para cultivos PUBLICADOS
        if(cultivoDTO.getEstado().equals(EstadoCultivo.PUBLICADO)){
            //Buscamos si existe el producto por nombre
            Producto producto = productoRepository.findProductoByNombre(cultivoDTO.getNombreProducto())
                    .orElseThrow(()-> new NotFoundException("Producto no asigando para el cultivo PUBLICADO " + cultivoDTO.getNombreProducto()));
            //Validacion para ver si tiene un precio el cultivo
            if(cultivoDTO.getPrecio() == null) throw new RuntimeException("El precio es obligatorio para publicar el cultivo");
            //Validadcion para verificar si el precio del cultivo es mayor al del precio del producto
            if(cultivoDTO.getPrecio().compareTo(producto.getPrecio()) == 0 || cultivoDTO.getPrecio().compareTo(producto.getPrecio()) > 0)
                throw new RuntimeException("El precio debe estar por debajo del precio de mercado: " + producto.getPrecio());
        }

        Cultivo cultivo = cultivoMapper.toEntity(cultivoDTO);

        return cultivoMapper.toDto(cultivoRepository.save(cultivo));
    }

    @Override
    public CultivoDTO actualizarCultivo(Long id, CultivoDTO cultivoDTO) {
        Cultivo cultivo = cultivoRepository.findById(id).orElseThrow(()->
                new NotFoundException("Cultivo no encontrado"));
        Producto producto = productoRepository.findProductoByNombre(cultivoDTO.getNombreProducto())
                        .orElseThrow(()->new NotFoundException("Producto no encontrado"
                                + cultivoDTO.getNombreProducto()));

        cultivo.setNombre(cultivoDTO.getNombreCultivo());
        cultivo.setProducto(producto);
        cultivo.setDescripcion(cultivoDTO.getDescripcion());
        cultivo.setCantidad(cultivoDTO.getCantidad());
        cultivo.setUnidadMedida(cultivoDTO.getUnidadMedida());
        cultivo.setPrecio(cultivoDTO.getPrecio());
        cultivo.setEstadoCultivo(cultivoDTO.getEstado());
        cultivo.setFechaCreacion(cultivoDTO.getFechaCreacion());

        return cultivoMapper.toDto(cultivoRepository.save(cultivo));
    }

    @Override
    public void eliminarCultivo(Long id) {
        if(!cultivoRepository.existsById(id)){
            throw new NotFoundException("Cultivo no encontrado: " + id);
        }
        cultivoRepository.deleteById(id);
    }
}
