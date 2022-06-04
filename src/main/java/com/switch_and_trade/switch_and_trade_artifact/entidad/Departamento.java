package com.switch_and_trade.switch_and_trade_artifact.entidad;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
@Table(name="departamento")
@SQLDelete(sql = "UPDATE departamento SET eliminado_departamento = true WHERE id = ?")
public class Departamento {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_departamento")
    private Long id;

    @Column(name="nombre_departamento")
    private String nombre;

    @Column(name = "eliminado_departamento", nullable = false)
    private Boolean eliminado;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="id_provincia_departamento", referencedColumnName = "id_provincia")
    private Provincia provincia;
}
