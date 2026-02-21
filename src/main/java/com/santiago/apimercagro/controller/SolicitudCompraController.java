package com.santiago.apimercagro.controller;

import com.santiago.apimercagro.dto.RequestSolicitudCompraDTO;
import com.santiago.apimercagro.dto.response.ResponseSolicitudCompraDTO;
import com.santiago.apimercagro.service.ISolicitudCompraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/apiMercagro/v1/solicitudes")
public class SolicitudCompraController {

    private final ISolicitudCompraService solicitudCompraService;

    public SolicitudCompraController(ISolicitudCompraService solicitudCompraService) {
        this.solicitudCompraService = solicitudCompraService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseSolicitudCompraDTO>> listarSolicitudes(){
        return ResponseEntity.ok(solicitudCompraService.listarSolicitudes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSolicitudCompraDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(solicitudCompraService.buscarSolicitudPorId(id));
    }

    @PostMapping
    public ResponseEntity<ResponseSolicitudCompraDTO> crearSolicitud(
            @Valid @RequestBody RequestSolicitudCompraDTO requestSolicitudCompraDTO){
        ResponseSolicitudCompraDTO solicitudCompraCreada = solicitudCompraService.crearSolicitud(requestSolicitudCompraDTO);

        /*1) Se utiliza toda la url de la petición HTTP
         * 2) Se agrega el segmento del ID al path creado
         * 3) Se mapea el ID con el atributo correspondiente
         * 4) Se convierte el resultado a URI */
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(solicitudCompraCreada.getIdSolicitud())
                .toUri();

        return ResponseEntity.created(location).body(solicitudCompraCreada);
//        return ResponseEntity.created(URI.create("/apiMercagro/v1/solicitudes/"
//                + solicitudCompraCreada.getIdSolicitud())).body(solicitudCompraCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSolicitudCompraDTO> actualizarSolicitud(
            @PathVariable Long id,
            @Valid @RequestBody RequestSolicitudCompraDTO requestSolicitudCompraDTO){
        return ResponseEntity.ok(solicitudCompraService.actualizarSolicitud(id, requestSolicitudCompraDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable Long id){
        solicitudCompraService.eliminarSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}
