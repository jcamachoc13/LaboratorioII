package com.example.demo.controller;

import com.example.demo.model.Curso;
import com.example.demo.service.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("listaCursos", cursoService.listarCursos());
        return "cursos"; // ← Nombre del archivo HTML
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("curso", new Curso());
        return "crear_curso";
    }

    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute Curso curso) {
        cursoService.guardarCurso(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Curso curso = cursoService.obtenerPorId(id);
        model.addAttribute("curso", curso);
        return "editar_curso";
    }

    // Actualizar curso
    @PostMapping("/actualizar/{id}")
    public String actualizarCurso(@PathVariable Long id, @ModelAttribute Curso curso) {
        cursoService.actualizarCurso(id, curso);
        return "redirect:/cursos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return "redirect:/cursos";
    }
    @GetMapping("/{id}/personas")
    public String verPersonasDelCurso(@PathVariable Long id, Model model) {
        Curso curso = cursoService.obtenerPorId(id);
        model.addAttribute("curso", curso);
        model.addAttribute("listaPersonas", curso.getPersonas());
        return "personas_del_curso";
    }
}
