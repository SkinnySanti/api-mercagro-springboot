package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.CultivoDTO;

import java.util.List;
import java.util.Optional;

public interface ICultivoService {
    List<CultivoDTO> listarCultivos();
    Optional<CultivoDTO> buscarCultivoPorId(Long id);
    CultivoDTO crearCultivo(CultivoDTO cultivoDTO);
    CultivoDTO actualizarCultivo(Long id, CultivoDTO cultivoDTO);
    void eliminarCultivo(Long id);

}
