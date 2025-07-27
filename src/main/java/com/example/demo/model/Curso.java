
package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; 
    
    private String nombreCurso;
    
    @ManyToMany(mappedBy = "cursos")
    //private Persona persona;
    private List<Persona> personas = new ArrayList<>();

    //getters, setters

    public Long getId(){
        return id; 
    }

    public void setId(Long id){
        this.id = id; 
    }

    public String getNombreCurso(){
        return nombreCurso; 
    }

    public void setNombreCurso(String nombreCurso){
        this.nombreCurso = nombreCurso; 
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
}
