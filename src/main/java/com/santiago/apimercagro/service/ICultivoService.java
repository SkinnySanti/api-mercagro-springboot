package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.request.RequestCultivoDTO;
import com.santiago.apimercagro.dto.response.ResponseCultivoDTO;

import java.util.List;

public interface ICultivoService {
    List<ResponseCultivoDTO> listarCultivos();
    ResponseCultivoDTO buscarCultivoPorId(Long id);
    ResponseCultivoDTO crearCultivo(RequestCultivoDTO requestCultivoDTO);
    ResponseCultivoDTO actualizarCultivo(Long id, RequestCultivoDTO requestCultivoDTO);
    void eliminarCultivo(Long id);

}
