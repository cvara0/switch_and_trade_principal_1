package com.switch_and_trade.switch_and_trade_artifact.repositorio;

import com.switch_and_trade.switch_and_trade_artifact.modelo.*;
import org.hibernate.annotations.Loader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepositorio  extends JpaRepository<Publicacion, Long> {

/*
    List<Publicacion> traerTodoOrdenNombrePerfilAsc();

    List<Publicacion> traerTodoOrdenTipoVehiculoAsc();

    List<Publicacion> traerTodoOrdenTipoPropiedadAsc();


    @Column(name="id_publicacion")

    @Column(name = "eliminado_publicacion", nullable = false)

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_perfil_publicacion", referencedColumnName = "id_perfil", nullable = false)
    private Perfil perfil;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_vehiculo_publicacion", referencedColumnName = "id_vehiculo", nullable = true)
    private Vehiculo vehiculo;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_propiedad_publicacion", referencedColumnName = "id_propiedad", nullable = true)
    private Propiedad propiedad;*/
}
