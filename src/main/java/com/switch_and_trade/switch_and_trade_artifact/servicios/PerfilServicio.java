package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Rol;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.PerfilRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.util.Collections.singletonList;

@Service
@RequiredArgsConstructor
public class PerfilServicio implements UserDetailsService {

    private final PerfilRepositorio perfilRepositorio;
    private final BCryptPasswordEncoder encriptador;//esto se puede hacer porque tengo el bean creado
    private final EmailServicio emailServicio;

    // inicio metodos basicos


    @Transactional
    public void insertar(Perfil dto) {
        if (perfilRepositorio.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Emal ya registrado!");
        }
        Perfil perfil = new Perfil();
        perfil.setNombre(dto.getNombre());
        perfil.setApellido(dto.getApellido());
        perfil.setTelefono(dto.getTelefono());
        perfil.setFoto(dto.getFoto());
        perfil.setLocalidad(dto.getLocalidad());
        perfil.setEmail(dto.getEmail());
        //usuario.setRol(dto.getRol());
        perfil.setClave(encriptador.encode(dto.getClave()));//se encripta la constrasenia

        if (perfilRepositorio.findAll().isEmpty()) perfil.setRol(Rol.ADMINISTRADOR);
        else perfil.setRol(Rol.USUARIO);

        emailServicio.send(dto.getEmail());
        perfilRepositorio.save(perfil);
    }

    @Transactional
    public void actualizar(Perfil dto) {
        Perfil perfil = new Perfil();
        perfil.setEmail(dto.getEmail());
        perfil.setClave(dto.getClave());
        perfil.setNombre(dto.getNombre());
        perfil.setApellido(dto.getApellido());
        perfil.setTelefono(dto.getTelefono());
        perfil.setFoto(dto.getFoto());
        perfil.setLocalidad(dto.getLocalidad());

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
    /*
    sirve para saber si alguien existe o no existe en la bd cuando me quiera logear
    pregunta a la bd si existe una instancia con el email que le estoy pasando
    * */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {//sprint security envia el parametro mail automaticamente
        Perfil perfil = perfilRepositorio.findByEmail(email)//orElseThrow es un metodo de la clase optional que permite usar una expresion lambda
                .orElseThrow(() -> new UsernameNotFoundException("There is no user associated with the email entered"));//una lamabda es (parametro)->(return)
        //dudas con esto
        GrantedAuthority authority = () -> "ROLE_" + perfil.getRol().name();//funcion que implementa los roles
        //dudas con esto
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);

        session.setAttribute("id", perfil.getId());
        session.setAttribute("email", perfil.getEmail());
        session.setAttribute("rol", perfil.getRol().name());

        return new org.springframework.security.core.userdetails.User(perfil.getEmail(), perfil.getClave(), singletonList(authority));
        //Si existe un email en la base de datos , User recibe 3 parametros, email, clave y las autorizaciones, el alcance, si puede leer o modificar
        //si se trabaja sin roles se envia una lista vacia Collections.emptyList(), en vez de singleton...
        //registra en el contexto de seguridad de spring a ese usuario, si el usuario eprtenece al contexto ingresa a la aplicaciom, sino no
    }

    //fin metodos basicos
/*
    // inicio metodos personalizados
    public List<Perfil> traerTodoOrdenNombreAsc(){
        return perfilRepositorio.traerTodoOrdenNombreAsc();
    }
    public List<Perfil> traerTodoOrdenApellidoAsc(){
        return perfilRepositorio.traerTodoOrdenApellidoAsc();
    }

    public List<Perfil> traerTodoOrdenProvinciaAsc(){
        return perfilRepositorio.traerTodoOrdenProvinciaAsc();
    }

    public List<Perfil> traerTodoOrdenLocalidadAsc(){
        return perfilRepositorio.traerTodoOrdenLocalidadAsc();
    }

    public Perfil traerPorTelefono(Integer telefono){
        return perfilRepositorio.traerPorTelefono(telefono);
    }

// fin metodos personalizados

    */
}