package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Mascota;
import com.example.demo.service.MascotaService;

@Controller
@RequestMapping("/personas/{personaId}/mascotas")
public class MascotaController {
        private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService){
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public String listar(@PathVariable Long personaId, Model model) {
        model.addAttribute("mascotas", mascotaService.listarPorPersona(personaId));
        model.addAttribute("personaId", personaId); 
        return "mascotas"; 
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(@PathVariable Long personaId, Model model){
        model.addAttribute("mascota", new Mascota());
        model.addAttribute("personaId", personaId);
        return "crear_mascota";
    }
    
    @PostMapping("/guardar")
    public String guardar(@PathVariable Long personaId, @ModelAttribute Mascota mascota){
        mascotaService.guardar(personaId, mascota);
        return "redirect:/personas/"+ personaId + "/mascotas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long personaId, @PathVariable Long id, Model model) {
        model.addAttribute("mascota", mascotaService.obtenerPorId(id));
        model.addAttribute("personaId", personaId);
        return "editar_mascota";
    }

        @PostMapping("/actualizar/{id}")
    public String actualizarPersona(@PathVariable Long personaId, @PathVariable Long id, @ModelAttribute Mascota mascota){
        mascotaService.actualizar(id, mascota);
        return "redirect:/personas/"+ personaId + "/mascotas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long personaId, @PathVariable Long id) {
        mascotaService.eliminar(id);
        return "redirect:/personas/"+ personaId + "/mascotas";
    }
}