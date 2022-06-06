package com.switch_and_trade.switch_and_trade_artifact.servicio;

import com.switch_and_trade.switch_and_trade_artifact.modelo.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.repositorio.PerfilRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilServicio {
    private final PerfilRepositorio perfilRepositorio;

    //inicio metodos basicos
    @Transactional
    public void insertar(Perfil dto) {
        Perfil perfil = new Perfil();
        perfil.setNombre(dto.getNombre());
        perfil.setApellido(dto.getApellido());
        perfil.setTelefono(dto.getTelefono());
        perfil.setProvincia(dto.getProvincia());
        //perfil.setLocalidad(dto.getLocalidad());
        perfilRepositorio.save(perfil);
    }

    @Transactional
    public void actualizar(Perfil dto) {
        Perfil perfil = perfilRepositorio.findById(dto.getId()).get();
        perfil.setNombre(dto.getNombre());
        perfil.setApellido(dto.getApellido());
        perfil.setTelefono(dto.getTelefono());
        perfil.setProvincia(dto.getProvincia());
        //perfil.setLocalidad(dto.getLocalidad());
        perfilRepositorio.save(perfil);
    }

    @Transactional(readOnly = true)
    public Perfil traerPorId(Long id) {
        return perfilRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Perfil> traerTodo() {
        return perfilRepositorio.findAll();
    }

    @Transactional
    public void eliminarPorId(Long id) {
        perfilRepositorio.deleteById(id);
    }
    //fin metodos basicos

    // inicio metodos personalizados



// fin metodos personalizados

    /*
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
    * */
}
