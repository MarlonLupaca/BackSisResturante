package jojolete.jojolete.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jojolete.jojolete.models.Medida;

@Repository
public interface MedidaRepositorio extends JpaRepository<Medida, Long> {
}
