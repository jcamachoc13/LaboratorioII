package com.example.demo.controller;

import java.util.stream.Collectors;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Curso;
import com.example.demo.model.Persona;
import com.example.demo.service.CursoService;
import com.example.demo.service.PersonaService;
 

@Controller
@RequestMapping("/personas")
public class PersonaController {
    
    private final PersonaService personaService;
    private final CursoService cursoService; // Se agrega variable para listar los cursos de la persona 

    public PersonaController(PersonaService personaService, CursoService cursoService) {
        this.personaService = personaService;
        this.cursoService = cursoService; 
    }
    
    @GetMapping
    public String listar(Model model){
        model.addAttribute("listaPersonas", personaService.listarPersonas());
        return "personas";
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model){
        model.addAttribute("persona", new Persona());
        model.addAttribute("listaCursos", cursoService.listarCursos()); // Lista los cursos
        return "crear_persona";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Persona persona){
        personaService.guardarPersona(persona);
        return "redirect:/personas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Persona persona = personaService.obtenerPorId(id);
        model.addAttribute("persona", persona);
        model.addAttribute("listaCursos", cursoService.listarCursos());
        return "editar_persona";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPersona(@PathVariable Long id, @ModelAttribute Persona persona){
        List<Curso> cursosAsignados = persona.getCursos().stream()
            .map(curso -> cursoService.obtenerPorId(curso.getId()))
            .collect(Collectors.toList());

    persona.setCursos(cursosAsignados);
        personaService.actualizarPersona(id, persona);
        return "redirect:/personas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id) {
        personaService.eliminarPersona(id);

        return "redirect:/personas";
    }
    
    @GetMapping("/{id}/cursos")
    public String verCursosDePersona(@PathVariable Long id, Model model) {
        Persona persona = personaService.obtenerPorId(id);
        model.addAttribute("persona", persona);
        model.addAttribute("listaCursos", persona.getCursos());
        return "cursos_persona";
    }

}
