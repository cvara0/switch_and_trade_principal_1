package com.switch_and_trade.switch_and_trade_artifact.repositorio;

import com.switch_and_trade.switch_and_trade_artifact.modelo.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilRepositorio extends JpaRepository<Perfil, Long> {


    @Query(value = "SELECT * FROM perfil ORDER BY nombre_perfil ASC",nativeQuery = true)
    List<Perfil> traerTodoOrdenNombreAsc();

    @Query(value = "SELECT * FROM perfil ORDER BY apellido_perfil ASC",nativeQuery = true)
    List<Perfil> traerTodoOrdenApellidoAsc();

    @Query(value = "SELECT * FROM perfil JOIN provincia ON perfil.id_provincia_perfil=provincia.id_provincia ORDER BY provincia.nombre_provincia ASC",nativeQuery = true)
    List<Perfil> traerTodoOrdenProvinciaAsc();

    @Query(value = "SELECT * FROM perfil JOIN provincia ON perfil.id_provincia_perfil=provincia.id_provincia JOIN localidad ON provincia.id_provincia=localidad.id_provincia_localidad ORDER BY localidad.nombre_localidad ASC",nativeQuery = true)
    List<Perfil> traerTodoOrdenLocalidadAsc();

    @Query(value = "SELECT * FROM perfil WHERE telefono_perfil=?1",nativeQuery = true)
    Perfil traerPorTelefono(Integer telefono);


   /* @Modifying
    @Query("UPDATE Perfil p SET p.eliminado = false WHERE p.id = ?1")
    void enableById(Long id);*/


}
