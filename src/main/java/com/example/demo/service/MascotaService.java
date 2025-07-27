package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Mascota;
import com.example.demo.model.Persona;
import com.example.demo.repository.MascotaRepository;
import com.example.demo.repository.PersonaRepository;

@Service
public class MascotaService {
    private final MascotaRepository mascotaRepository;
    private final PersonaRepository personaRepository;

    public MascotaService(MascotaRepository mascotaRepository, PersonaRepository personaRepository) {
        this.mascotaRepository = mascotaRepository;
        this.personaRepository = personaRepository;
    }

    public List<Mascota> listarPorPersona(Long personaId) {
        return mascotaRepository.findByPersonaId(personaId);
    }

    public void guardar(Long personaId, Mascota mascota) {
        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        mascota.setPersona(persona);
        mascotaRepository.save(mascota);
    }

    public Mascota obtenerPorId(Long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    public void actualizar(Long id, Mascota mascotaActualizada) {
        Mascota mascota = obtenerPorId(id);
        mascota.setNombre(mascotaActualizada.getNombre());
        mascotaRepository.save(mascota);
    }

    public void eliminar(Long id) {
        mascotaRepository.deleteById(id);
    }
}
