package com.santiago.apimercagro.controller;

import com.santiago.apimercagro.dto.request.RequestCultivoDTO;
import com.santiago.apimercagro.dto.response.ResponseCultivoDTO;
import com.santiago.apimercagro.service.ICultivoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/apiMercagro/v1/cultivo")
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

        /*1) Se utiliza toda la url de la petición HTTP
        * 2) Se agrega el segmento del ID al path creado
        * 3) Se mapea el ID con el atributo correspondiente
        * 4) Se convierte el resultado a URI */
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cultivoCreado.getIdCultivo())
                .toUri();

        return ResponseEntity.created(location).body(cultivoCreado);
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
