package com.santiago.apimercagro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotBlank @Size(max = 50, message = "Maximo de caracteres: 50")
    @Column(nullable = false, unique = true, name = "username")
    private String username;

    @NotBlank @Size(min = 8, message = "Minimos de caracteres: 8")
    @Column(nullable = false, name = "password")
    private String password;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private String nombreCompleto;

    private boolean activo = true;

    @OneToMany(mappedBy = "usuario")
    private List<Rol> roles = new ArrayList<>();
}
