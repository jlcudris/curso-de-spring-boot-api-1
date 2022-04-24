package com.cursojava.curso.models;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="usuarios")
public class Usuario {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter @Column(name ="nombre")
    private String nombre;
    @Getter @Setter @Column(name ="apellido")
    private String apellido;
    @Getter @Setter @Column(name ="telefono")
    private String telefono;
    @Getter @Setter @Column(name ="email")
    private String email;
    @Getter @Setter @Column(name ="password")
    private String password;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellido, String telefono, String email, String password) {
        this.id =id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }


}
