package com.switch_and_trade.switch_and_trade_artifact.repositorio;

import com.switch_and_trade.switch_and_trade_artifact.modelo.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepositorio  extends JpaRepository<Provincia, Long> {
}
