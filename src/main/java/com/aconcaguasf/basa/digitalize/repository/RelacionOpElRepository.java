package com.aconcaguasf.basa.digitalize.repository;

import com.aconcaguasf.basa.digitalize.model.Movimientos;
import com.aconcaguasf.basa.digitalize.model.RelacionOpEl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RelacionOpElRepository extends JpaRepository<RelacionOpEl, Long> {

    @Query("SELECT m FROM RelacionOpEl m WHERE m.operacion_id = :idOperacion")
    List<RelacionOpEl> findByOperacion_id(@Param("idOperacion") String idOperacion);

}
