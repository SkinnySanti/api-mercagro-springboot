package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.RequestSolicitudCompraDTO;
import com.santiago.apimercagro.dto.response.ResponseSolicitudCompraDTO;

import java.util.List;

public interface ISolicitudCompraService {
    List<ResponseSolicitudCompraDTO> listarSolicitudes();
    ResponseSolicitudCompraDTO buscarSolicitudPorId(Long id);
    ResponseSolicitudCompraDTO crearSolicitud(RequestSolicitudCompraDTO solicitudCompraDTO);
    ResponseSolicitudCompraDTO actualizarSolicitud(Long id, RequestSolicitudCompraDTO solicitudCompraDTO);
    void eliminarSolicitud(Long id);
}
