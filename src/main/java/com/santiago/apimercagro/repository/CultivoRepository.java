package com.santiago.apimercagro.repository;

import com.santiago.apimercagro.model.Cultivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CultivoRepository extends JpaRepository<Cultivo,Long> {

}
