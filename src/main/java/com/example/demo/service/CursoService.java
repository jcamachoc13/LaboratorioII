package com.example.demo.service;

import com.example.demo.model.Curso;
import com.example.demo.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public void guardarCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public void actualizarCurso(Long id, Curso cursoActualizado) {
        Curso curso = obtenerPorId(id);
        curso.setNombreCurso(cursoActualizado.getNombreCurso());
        cursoRepository.save(curso);
    }

    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    public List<Curso> buscarPorNombre(String nombre) {
        return cursoRepository.findByNombreCurso(nombre);
    }
}
