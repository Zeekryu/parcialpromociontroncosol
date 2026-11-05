package com.icop.parcialpromociontroncosol.repository;

import com.icop.parcialpromociontroncosol.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    boolean existsByTituloLibroAndDevueltoFalse(String tituloLibro);
}
