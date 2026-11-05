package com.icop.parcialpromociontroncosol.repository;

import com.icop.parcialpromociontroncosol.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SocioRepository extends JpaRepository<Socio, Long> {
    boolean existsByDni(String dni);
}
