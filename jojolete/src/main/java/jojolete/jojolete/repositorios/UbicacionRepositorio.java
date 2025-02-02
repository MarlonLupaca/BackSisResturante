package jojolete.jojolete.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jojolete.jojolete.models.Ubicacion;

@Repository
public interface UbicacionRepositorio extends JpaRepository<Ubicacion, Long> {
    
}
