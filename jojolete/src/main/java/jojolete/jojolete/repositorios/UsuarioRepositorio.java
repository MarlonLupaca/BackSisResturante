package jojolete.jojolete.repositorios;

import jojolete.jojolete.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

    public Usuario findByCorreoAndContrasena(String correo, String contrasena);
    boolean existsByCorreo(String correo);

}
