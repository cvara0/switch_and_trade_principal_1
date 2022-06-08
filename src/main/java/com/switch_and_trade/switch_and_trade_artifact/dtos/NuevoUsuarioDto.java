package com.switch_and_trade.switch_and_trade_artifact.dtos;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Localidad;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Provincia;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Rol;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class NuevoUsuarioDto {

    //perfil
    private String nombre;

    private String apellido;

    private Long telefono;

    private String foto;

    private String nombreProvincia;

    private String nombreLocalidad;
    //usuario
    private String email;

    private String clave;

    private Rol rol;

    private Boolean eliminado;
}
