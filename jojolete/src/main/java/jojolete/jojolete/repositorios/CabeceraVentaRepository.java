package jojolete.jojolete.repositorios;

import jojolete.jojolete.models.CabeceraVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabeceraVentaRepository extends JpaRepository<CabeceraVenta, Long> {
}
