package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Propiedad;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Publicacion;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Vehiculo;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.PublicacionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicacionServicio {
    private final PublicacionRepositorio publicacionRepositorio;
    private final VehiculoServicio vehiculoServicio;
    private final PropiedadServicio propiedadServicio;
    private final PerfilServicio perfilServicio;

    //inicio metodos basicos
    @Transactional
    public void insertar(Publicacion dto) {
        Publicacion publicacion = new Publicacion();
        publicacion.setPerfil(dto.getPerfil());
        publicacion.setPropiedad(dto.getPropiedad());
        publicacion.setVehiculo(dto.getVehiculo());
        publicacion.setEliminado(dto.getEliminado());

        publicacionRepositorio.save(publicacion);
    }

    @Transactional
    public void actualizar(Publicacion dto) {
        Publicacion publicacion = publicacionRepositorio.findById(dto.getId()).get();

        publicacion.setPerfil(dto.getPerfil());
        publicacion.setPropiedad(dto.getPropiedad());
        publicacion.setVehiculo(dto.getVehiculo());
        publicacion.setEliminado(dto.getEliminado());

        publicacionRepositorio.save(publicacion);
    }

    @Transactional(readOnly = true)
    public Publicacion traerPorId(Long id) {
        return publicacionRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Publicacion> traerTodo() {
        return publicacionRepositorio.findAll();
    }

    @Transactional
    public void eliminarPorId(Long id) {
        publicacionRepositorio.deleteById(id);
    }
//fin metodos basicos
// inicio metodos personalizados

    public List<Publicacion> traerPublicacionesQueDeseanMiPropiedad(Publicacion miPublicacion) {
        List<Publicacion> publicacionesQueDeseanMiPropiedad = new ArrayList<>();

        for (Publicacion unaPublicacion : traerTodo()) {
            if (!miPublicacion.getPropiedad().getDeseado()
                    && miPublicacion.getPropiedad().getTipoPropiedad().getTipo().equals(unaPublicacion.getPropiedad().getTipoPropiedad().getTipo())
                    && unaPublicacion.getPropiedad().getDeseado()
                    && miPublicacion.getPerfil().getId() != unaPublicacion.getPerfil().getId()) {
                /*si la propiedad de la publicacion es ofrecida
            y el tipo de la propiedad de la publicacion coincide con el tipo de todas las propiedades deseadas
            y alguna propiedad de todas las publicaciones es deseada
            y el id del perfil de la publicacion es diferente al id de algun perfil de todas las publicaciones
            entonces tengo todas las publicaciones que desean mi propiedad
             */
                publicacionesQueDeseanMiPropiedad.add(unaPublicacion);
            }

        }
        return publicacionesQueDeseanMiPropiedad;
    }

    public List<Publicacion> traerPublicacionesQueDeseanMiVehiculo(Publicacion miPublicacion) {
        List<Publicacion> publicacionesQueDeseanMiVehiculo = new ArrayList<>();
        for (Publicacion unaPublicacion : traerTodo()) {
            if (!miPublicacion.getVehiculo().getDeseado()
                    && miPublicacion.getVehiculo().getTipoVehiculo().getTipo().equals(unaPublicacion.getVehiculo().getTipoVehiculo().getTipo())
                    && unaPublicacion.getVehiculo().getDeseado()
                    && miPublicacion.getPerfil().getId() != unaPublicacion.getPerfil().getId()) {
                /*si la propiedad de la publicacion es ofrecida
            y el tipo de la propiedad de la publicacion coincide con el tipo de todas las propiedades deseadas
            y alguna propiedad de todas las publicaciones es deseada
            y el id del perfil de la publicacion es diferente al id de algun perfil de todas las publicaciones
            entonces tengo todas las publicaciones que desean mi propiedad
             */
                publicacionesQueDeseanMiVehiculo.add(unaPublicacion);
            }

        }
        return publicacionesQueDeseanMiVehiculo;
    }

    public List<Publicacion> traerPublicacionesQueOfrecenMiPropiedadDeseada(Publicacion miPublicacion) {
        List<Publicacion> publicacionesQueOfrecenMiPropiedadDeseada=new ArrayList<>();
        for (Publicacion unaPublicacion : traerTodo()) {
            if (miPublicacion.getPropiedad().getDeseado()
                    && miPublicacion.getPropiedad().getTipoPropiedad().getTipo().equals(unaPublicacion.getPropiedad().getTipoPropiedad().getTipo())
                    && !unaPublicacion.getPropiedad().getDeseado()
                    && miPublicacion.getPerfil().getId() != unaPublicacion.getPerfil().getId()) {

                publicacionesQueOfrecenMiPropiedadDeseada.add(unaPublicacion);
            }

        }
        return publicacionesQueOfrecenMiPropiedadDeseada;
    }

    public List<Publicacion> traerPublicacionesQueOfrecenMiVehiculoDeseado(Publicacion miPublicacion) {
        List<Publicacion> publicacionesQueOfrecenMiVehiculoDeseado=new ArrayList<>();
        for (Publicacion unaPublicacion : traerTodo()) {
            if (miPublicacion.getVehiculo().getDeseado()
                    && miPublicacion.getVehiculo().getTipoVehiculo().getTipo().equals(unaPublicacion.getVehiculo().getTipoVehiculo().getTipo())
                    && !unaPublicacion.getVehiculo().getDeseado()
                    && miPublicacion.getPerfil().getId() != unaPublicacion.getPerfil().getId()) {

                publicacionesQueOfrecenMiVehiculoDeseado.add(unaPublicacion);
            }
        }
        return publicacionesQueOfrecenMiVehiculoDeseado;
    }




}

// fin metodos personalizados

  /*


@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_publicacion")
    private Long id;

    @Column(name = "eliminado_publicacion", nullable = false)
    private Boolean eliminado;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_perfil_publicacion", referencedColumnName = "id_perfil", nullable = false)
    private Perfil perfil;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_vehiculo_publicacion", referencedColumnName = "id_vehiculo", nullable = true)
    private Vehiculo vehiculo;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_propiedad_publicacion", referencedColumnName = "id_propiedad", nullable = true)
    private Propiedad propiedad;
 * */