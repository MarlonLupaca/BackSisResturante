package jojolete.jojolete.repositorios;

import java.util.List;
import jojolete.jojolete.models.Plato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatoRepositorio extends JpaRepository<Plato, Long>{
    List<Plato> findByEstadoTrue();
    
}
