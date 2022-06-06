package com.switch_and_trade.switch_and_trade_artifact.repositorio;

import com.switch_and_trade.switch_and_trade_artifact.modelo.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalidadRepositorio extends JpaRepository<Localidad, Long> {
    @Query(value = "SELECT * FROM localidad ORDER BY nombre_localidad ASC", nativeQuery = true)
    List<Localidad> traerTodoOrdenNombreAsc();

    @Query(value = "SELECT * FROM localidad WHERE eliminado_localidad=1", nativeQuery = true)
    List<Localidad> traerTodoEliminado();

    @Query(value = "SELECT * FROM localidad WHERE eliminado_localidad=0", nativeQuery = true)
    List<Localidad> traerTodoNoEliminado();
}