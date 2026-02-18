package com.santiago.apimercagro.controller;

import com.santiago.apimercagro.dto.request.RequestCultivoDTO;
import com.santiago.apimercagro.dto.response.ResponseCultivoDTO;
import com.santiago.apimercagro.service.ICultivoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("apiMercagro/v1/cultivo")
@RestController
public class CultivoController {


    private final ICultivoService cultivoService;

    public CultivoController(ICultivoService cultivoService) {
        this.cultivoService = cultivoService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseCultivoDTO>> listarProductos(){
        return ResponseEntity.ok(cultivoService.listarCultivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCultivoDTO> buscarPorId(@PathVariable Long id){
        ResponseCultivoDTO responseCultivoDTO = cultivoService.buscarCultivoPorId(id);
        return ResponseEntity.ok(responseCultivoDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseCultivoDTO> crearCultivo(@Valid @RequestBody
                                                               RequestCultivoDTO requestCultivoDTO){
        ResponseCultivoDTO cultivoCreado = cultivoService.crearCultivo(requestCultivoDTO);
        return ResponseEntity.created(URI.create("/apiMercagro/v1/cultivo/" + cultivoCreado.getIdCultivo()))
                .body(cultivoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCultivoDTO> actualizarCultivo(@PathVariable Long id,
                                                                @Valid @RequestBody
                                                                RequestCultivoDTO requestCultivoDTO){
        return ResponseEntity.ok(cultivoService.actualizarCultivo(id,requestCultivoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCultivo(@PathVariable Long id){
        cultivoService.eliminarCultivo(id);
        return ResponseEntity.noContent().build();
    }
}
