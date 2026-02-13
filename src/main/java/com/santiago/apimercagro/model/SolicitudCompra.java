package com.santiago.apimercagro.model;

import com.santiago.apimercagro.enums.EstadoSolicitud;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "solicitud_compra")
public class SolicitudCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cultivo_id", referencedColumnName = "id_cultivo")
    private Cultivo cultivo;

    @Size(max = 50, message = "Maximo 50 caracteres")
    private String mensaje;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_solicitud", length = 20)
    private EstadoSolicitud estadoSolicitud = EstadoSolicitud.PENDIENTE;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
}
