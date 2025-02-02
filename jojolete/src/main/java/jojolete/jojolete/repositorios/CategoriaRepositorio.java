package jojolete.jojolete.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jojolete.jojolete.models.Categoria;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
}
