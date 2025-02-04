package jojolete.jojolete.repositorios;

import jojolete.jojolete.dto.DetalleMesaDTO;
import jojolete.jojolete.models.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleMesaRepository extends JpaRepository<DetalleMesaDTO, Long> {
    List<DetalleMesaDTO> findByMesa_Nombre(String mesaNombre);
    void deleteByMesa_Nombre(String mesaNombre);
    void deleteById(Long id);
}