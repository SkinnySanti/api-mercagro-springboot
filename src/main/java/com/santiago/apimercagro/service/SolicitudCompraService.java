package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.RequestSolicitudCompraDTO;
import com.santiago.apimercagro.dto.response.ResponseSolicitudCompraDTO;
import com.santiago.apimercagro.exception.NotFoundException;
import com.santiago.apimercagro.mapper.SolicitudCompraMapper;
import com.santiago.apimercagro.model.Cultivo;
import com.santiago.apimercagro.model.SolicitudCompra;
import com.santiago.apimercagro.repository.CultivoRepository;
import com.santiago.apimercagro.repository.SolicitudCompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudCompraService implements ISolicitudCompraService{


    private final SolicitudCompraRepository solicitudCompraRepository;
    private final SolicitudCompraMapper solicitudCompraMapper;
    private final CultivoRepository cultivoRepository;

    public SolicitudCompraService(SolicitudCompraRepository solicitudCompraRepository, SolicitudCompraMapper solicitudCompraMapper, CultivoRepository cultivoRepository) {
        this.solicitudCompraRepository = solicitudCompraRepository;
        this.solicitudCompraMapper = solicitudCompraMapper;
        this.cultivoRepository = cultivoRepository;
    }

    @Override
    public List<ResponseSolicitudCompraDTO> listarSolicitudes() {
        return solicitudCompraMapper.toDtoList(solicitudCompraRepository.findAll());
    }

    @Override
    public ResponseSolicitudCompraDTO buscarSolicitudPorId(Long id) {
        SolicitudCompra solicitudCompra = solicitudCompraRepository.findById(id).orElseThrow(()->
                new NotFoundException("Solicitud no encontrada: " + id));
        return solicitudCompraMapper.toDto(solicitudCompra);
    }

    @Override
    public ResponseSolicitudCompraDTO crearSolicitud(RequestSolicitudCompraDTO solicitudCompraDTO) {
        Cultivo cultivo = cultivoRepository.findCultivoByNombre(solicitudCompraDTO.getNombreCultivo())
                .orElseThrow(()-> new NotFoundException("Cultivo no encontrado"));
        SolicitudCompra solicitudCompra = solicitudCompraMapper.toEntity(solicitudCompraDTO, cultivo);
        return solicitudCompraMapper.toDto(solicitudCompraRepository.save(solicitudCompra));
    }

    @Override
    public ResponseSolicitudCompraDTO actualizarSolicitud(Long id, RequestSolicitudCompraDTO solicitudCompraDTO) {
        SolicitudCompra solicitudCompra = solicitudCompraRepository.findById(id).orElseThrow(()->
                new NotFoundException("Solicitud no encontrada: " + id));

        Cultivo cultivo = cultivoRepository.findCultivoByNombre(solicitudCompraDTO.getNombreCultivo())
                .orElseThrow(()-> new NotFoundException("Cultivo no encontrado: "));

        solicitudCompra.setCultivo(cultivo);
        solicitudCompra.setMensaje(solicitudCompraDTO.getMensaje());
        solicitudCompra.setEstadoSolicitud(solicitudCompraDTO.getEstado());

        return solicitudCompraMapper.toDto(solicitudCompraRepository.save(solicitudCompra));
    }

    @Override
    public void eliminarSolicitud(Long id) {
        if(!solicitudCompraRepository.existsById(id)){
            throw new NotFoundException("Solicitud no encontrada: " + id);
        }
        solicitudCompraRepository.deleteById(id);
    }
}
