package com.santiago.apimercagro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private Roles nombre;

    @Column(length = 100)
    private String descripcion;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;
}
