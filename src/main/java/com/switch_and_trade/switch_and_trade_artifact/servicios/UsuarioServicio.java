package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Rol;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Usuario;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.UsuarioRepositorio;
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
public class UsuarioServicio implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;
    private final BCryptPasswordEncoder encoder;
    private final EmailServicio emailServicio;

    // inicio metodos basicos
    @Transactional
    public void insertar(Usuario dto) {
        if (usuarioRepositorio.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("ya hay un usuario asociado al email ingresado!");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setTelefono(dto.getTelefono());
        usuario.setFoto(dto.getFoto());
        usuario.setLocalidad(dto.getLocalidad());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());
        usuario.setClave(encoder.encode(dto.getClave()));


        if (usuarioRepositorio.findAll().isEmpty()) usuario.setRol(Rol.ADMINISTRADOR);
        else usuario.setRol(dto.getRol());

        emailServicio.send(dto.getEmail());
        usuarioRepositorio.save(usuario);
    }

    @Transactional
    public void actualizar(Usuario dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setClave(dto.getClave());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setTelefono(dto.getTelefono());
        usuario.setFoto(dto.getFoto());
        usuario.setLocalidad(dto.getLocalidad());

        usuarioRepositorio.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario traerPorId(Long id) {
        return usuarioRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Usuario> traerTodo() {
        return usuarioRepositorio.findAll();
    }


    @Transactional
    public void deleteById(Long id) {
        usuarioRepositorio.deleteById(id);
    }

    // fin metodos basicos
    //inicio metodos personalizados
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("There is no user associated with the email entered"));
        GrantedAuthority authority = () -> "ROLE_" + usuario.getRol().name();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);

        session.setAttribute("id", usuario.getId());
        session.setAttribute("email", usuario.getEmail());
        session.setAttribute("role", usuario.getRol().name());

        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getClave(), singletonList(authority));
    }

    //fin metodos basicos

    // inicio metodos personalizados
    public List<Usuario> traerTodoOrdenNombreAsc(){
        return usuarioRepositorio.traerTodoOrdenNombreAsc();
    }
    public List<Usuario> traerTodoOrdenApellidoAsc(){
        return usuarioRepositorio.traerTodoOrdenApellidoAsc();
    }

    public List<Usuario> traerTodoOrdenProvinciaAsc(){
        return usuarioRepositorio.traerTodoOrdenProvinciaAsc();
    }

    public List<Usuario> traerTodoOrdenLocalidadAsc(){
        return usuarioRepositorio.traerTodoOrdenLocalidadAsc();
    }

    public Usuario traerPorTelefono(Integer telefono){
        return usuarioRepositorio.traerPorTelefono(telefono);
    }

// fin metodos personalizados

    //fin metodos personalizados
}