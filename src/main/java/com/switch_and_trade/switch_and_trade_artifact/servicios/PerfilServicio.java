package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.PerfilRepositorio;
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

        //perfil.setLocalidad(dto.getLocalidad());
        /*
        * perfil.setUsuario(dto.getUsuario());
        * */
        perfilRepositorio.save(perfil);
    }

    @Transactional
    public void actualizar(Perfil dto) {
        Perfil perfil = perfilRepositorio.findById(dto.getId()).get();
        perfil.setNombre(dto.getNombre());
        perfil.setApellido(dto.getApellido());
        perfil.setTelefono(dto.getTelefono());
        perfil.setLocalidad(dto.getLocalidad());
        //perfil.setLocalidad(dto.getLocalidad());
        /*
        *
        * */
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


}
