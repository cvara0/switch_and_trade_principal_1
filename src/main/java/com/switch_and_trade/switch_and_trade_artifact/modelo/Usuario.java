package com.switch_and_trade.switch_and_trade_artifact.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "usuario", indexes = {@Index(name = "idx_email_usuario", columnList = "email_usuario")})
@SQLDelete(sql = "UPDATE usuario SET eliminado_usuario = true WHERE id = ?")
public class Usuario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "email_usuario", length = 60, unique = true, nullable = false)
    private String email;

    @Column(name = "clave_usuario", nullable = false)
    private String clave;

    @Enumerated(STRING)
    @Column(name = "rol_usuario", nullable = false)
    private Rol rol;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "id_perfil_usuario", referencedColumnName = "id_perfil")
    private Perfil perfil;

    @Column(name = "eliminado_usuario", nullable = false)
    private Boolean eliminado;

}
