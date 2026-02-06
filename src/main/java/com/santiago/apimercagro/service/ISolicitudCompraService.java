package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.SolicitudCompraDTO;

import java.util.List;
import java.util.Optional;

public interface ISolicitudCompraService {
    List<SolicitudCompraDTO> listarSolicitudes();
    Optional<SolicitudCompraDTO> buscarSolicitudPorId(Long id);
    SolicitudCompraDTO crearSolicitud(SolicitudCompraDTO solicitudCompraDTO);
    SolicitudCompraDTO actualizarSolicitud(Long id, SolicitudCompraDTO solicitudCompraDTO);
    void eliminarSolicitud(Long id);
}
