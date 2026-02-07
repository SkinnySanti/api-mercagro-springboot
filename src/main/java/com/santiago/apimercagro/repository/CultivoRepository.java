package com.santiago.apimercagro.repository;

import com.santiago.apimercagro.model.Cultivo;
import com.santiago.apimercagro.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CultivoRepository extends JpaRepository<Cultivo,Long> {
    Optional<Cultivo> findCultivoByNombre(String nombreCultivo);

}
