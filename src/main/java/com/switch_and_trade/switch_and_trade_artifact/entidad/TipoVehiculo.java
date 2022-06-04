package com.switch_and_trade.switch_and_trade_artifact.entidad;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.ArrayList;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
@Entity
@RequiredArgsConstructor
@Setter
@Getter
@Table(name="tipo_vehiculo")
@SQLDelete(sql = "UPDATE tipo_vehiculo SET eliminado_tipo_vehiculo = true WHERE id = ?")
public class TipoVehiculo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_tipo_vechiculo")
    private Long id;

    @Column(name="tipo_tipo_vehiculo")
    private String tipo;

    @Column(name = "eliminado_tipo_vehiculo", nullable = false)
    private Boolean eliminado;

}
