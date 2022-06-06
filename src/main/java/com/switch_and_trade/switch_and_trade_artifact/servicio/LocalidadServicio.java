package com.switch_and_trade.switch_and_trade_artifact.servicio;

import com.switch_and_trade.switch_and_trade_artifact.modelo.Localidad;
import com.switch_and_trade.switch_and_trade_artifact.repositorio.LocalidadRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalidadServicio {

    private final LocalidadRepositorio localidadRepositorio;

    //inicio metodos basicos
    public void insertar(Localidad dto){
        Localidad localidad=new Localidad();
        localidad.setNombre(dto.getNombre());
        localidad.setProvincia(dto.getProvincia());
        localidad.setEliminado(dto.getEliminado());
        localidadRepositorio.save(localidad);
    }
    public void actualizar (Localidad dto){
        Localidad localidad=localidadRepositorio.findById(dto.getId()).get();
        localidad.setNombre(dto.getNombre());
        localidad.setProvincia(dto.getProvincia());
        localidad.setEliminado(dto.getEliminado());
        localidadRepositorio.save(localidad);
    }
    public List<Localidad> traerTodo(){
        return localidadRepositorio.findAll();
    }

    public Localidad traerPorId(Long id){
        return localidadRepositorio.findById(id).get();
    }

    @Transactional
    public void eliminarPorId(Long id) {
        localidadRepositorio.deleteById(id);
    }
//fin metodos basicos

// inicio metodos personalizados



// fin metodos personalizados

    /*
    * @Query(value = "SELECT * FROM localidad ORDER BY nombre_localidad ASC", nativeQuery = true)
    List<Localidad> traerTodoOrdenNombreAsc();

    @Query(value = "SELECT * FROM localidad WHERE eliminado_localidad=1", nativeQuery = true)
    List<Localidad> traerTodoEliminado();

    @Query(value = "SELECT * FROM localidad WHERE eliminado_localidad=0", nativeQuery = true)
    List<Localidad> traerTodoNoEliminado();*/

}
