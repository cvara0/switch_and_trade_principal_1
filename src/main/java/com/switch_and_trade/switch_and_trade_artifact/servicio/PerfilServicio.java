package com.switch_and_trade.switch_and_trade_artifact.servicio;

import com.switch_and_trade.switch_and_trade_artifact.entidad.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.repositorio.PerfilRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilServicio {
    private final PerfilRepositorio perfilRepositorio;

    @Transactional
    public void crear(Perfil dto) {
        Perfil perfil = new Perfil();
        perfil.setNombre(dto.getNombre());
        perfil.setApellido(dto.getApellido());
        perfil.setTelefono(dto.getTelefono());
        perfil.setProvincia(dto.getProvincia());
        //perfil.setLocalidad(dto.getLocalidad());
        perfilRepositorio.save(perfil);
    }

    @Transactional
    public void editar(Perfil dto) {
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

    @Transactional(readOnly = true)
    public List<Perfil> traerTodoPorNombreOApellido(String nombre, String apellido) {
        return null;//perfilRepositorio.findAllByNombreOrApellidoIgnoreCaseOrderByNombreAsc(nombre, apellido);
    }

    @Transactional(readOnly = true)
    public List<Perfil> traerTodoPorNombreYApellido(String nombre, String apellido) {
        return null;//perfilRepositorio.findAllByNombreAndApellidoIgnoreCaseOrderByNombreAsc(nombre, apellido);
    }

    @Transactional(readOnly = true)
    public List<Perfil> traerTodoPorNombre(String nombre) {
        return null;//perfilRepositorio.findAllByNombreIgnoreCaseOrderByNombreAsc(nombre);
    }

    @Transactional(readOnly = true)
    public List<Perfil> traerTodoPorApellido(String apellido) {
        return null;//perfilRepositorio.findAllByApellidoIgnoreCaseOrderByApellidoAsc(apellido);
    }

    @Transactional(readOnly = true)
    public List<Perfil> traerTodoPorLocalidad(String localidad) {
        return null;//perfilRepositorio.findAllByLocalidadOrderByLocalidadAsc(localidad);
    }

    @Transactional(readOnly = true)
    public List<Perfil> traerTodoPorProvincia(String provincia) {
        return null;//perfilRepositorio.findAllByProvinciaOrderByProvinciaAsc(provincia);
    }
}
