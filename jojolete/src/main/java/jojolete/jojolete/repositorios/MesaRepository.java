package jojolete.jojolete.repositorios;

import jojolete.jojolete.models.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    Mesa findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}