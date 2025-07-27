
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.example.demo.model.Mascota; 

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByPersonaId(Long personaId);
}
