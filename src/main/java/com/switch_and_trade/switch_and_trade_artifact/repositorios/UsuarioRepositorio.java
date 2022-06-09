package com.switch_and_trade.switch_and_trade_artifact.repositorios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByEliminado(Boolean eliminado);


    @Query(value = "SELECT * FROM usuario ORDER BY nombre_usuario ASC",nativeQuery = true)
    List<Usuario> traerTodoOrdenNombreAsc();

    @Query(value = "SELECT * FROM usuario ORDER BY apellido_usuario ASC",nativeQuery = true)
    List<Usuario> traerTodoOrdenApellidoAsc();

    @Query(value = "SELECT * FROM usuario JOIN provincia ON usuario.id_provincia_usuario=provincia.id_provincia ORDER BY provincia.nombre_provincia ASC",nativeQuery = true)
    List<Usuario> traerTodoOrdenProvinciaAsc();

    @Query(value = "SELECT * FROM usuario JOIN provincia ON usuario.id_provincia_usuario=provincia.id_provincia JOIN localidad ON provincia.id_provincia=localidad.id_provincia_localidad ORDER BY localidad.nombre_localidad ASC",nativeQuery = true)
    List<Usuario> traerTodoOrdenLocalidadAsc();

    @Query(value = "SELECT * FROM usuario WHERE telefono_usuario=?1",nativeQuery = true)
    Usuario traerPorTelefono(Integer telefono);
}
