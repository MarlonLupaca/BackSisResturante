package jojolete.jojolete.repositorios;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jojolete.jojolete.models.Producto;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, String> {

    public Optional<Producto> findById(Long id);
}
