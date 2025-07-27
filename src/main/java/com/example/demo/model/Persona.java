package com.example.demo.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nombre; 
    private String correo; 

    @OneToMany(mappedBy = "persona", cascade=CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Direccion> direcciones = new java.util.ArrayList<>();

    public java.util.List<Direccion> getDirecciones(){
        return direcciones;
    }

    public void setDirecciones(java.util.List<Direccion> direcciones){
        this.direcciones = direcciones;
    }

    @OneToMany(mappedBy = "persona", cascade=CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Mascota> mascotas = new java.util.ArrayList<>();
    public java.util.List<Mascota> getMascotas(){
        return mascotas;
    }
    
    public void setMascotas(java.util.List<Mascota> mascotas){
        this.mascotas = mascotas;
    }

    @ManyToMany
    @JoinTable(
        name = "persona_curso",
        joinColumns = @JoinColumn(name = "persona_id"),
        inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Curso> cursos = new ArrayList<>();

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNombre(){
        return nombre; 
    }

    public void setNombre(String nombre){
        this.nombre = nombre; 
    }

    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

}


// 1:N -> Direcciones 
// N:1 -> Mascotas (Muchas mascotas, 1 solo propietario (Persona))
// N:N -> Cursos (1 persona tiene muchos cursos, 1 curso tiene muchas personas )

