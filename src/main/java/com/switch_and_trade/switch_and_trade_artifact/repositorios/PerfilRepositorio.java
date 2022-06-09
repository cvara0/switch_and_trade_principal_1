package com.switch_and_trade.switch_and_trade_artifact.repositorios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilRepositorio extends JpaRepository<Perfil, Long> {





   /* @Modifying
    @Query("UPDATE Perfil p SET p.eliminado = false WHERE p.id = ?1")
    void enableById(Long id);*/


}
