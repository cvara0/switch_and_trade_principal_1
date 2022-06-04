package com.switch_and_trade.switch_and_trade_artifact.entidad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "perfil", indexes = {@Index(name = "idx_nombre_perfil", columnList = "nombre_perfil")})
@SQLDelete(sql = "UPDATE perfil SET deleted = true WHERE id = ?")
public class Perfil {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_perfil")
    private Long id;

    @Column(name = "nombre_perfil",length = 50,nullable = false)
    private String nombre;

    @Column(name = "apellido_perfil",length = 50,nullable = false)
    private String apellido;

    @Column(name = "telefono_perfil",nullable = false)
    private Long telefono;

    @Column(name="foto_perfil")
    private String foto;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="id_provincia_perfil", referencedColumnName = "id_provincia")
    private Provincia provincia;

}
