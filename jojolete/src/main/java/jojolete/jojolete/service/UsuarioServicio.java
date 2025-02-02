package jojolete.jojolete.service;

import java.util.List;
import jojolete.jojolete.dto.UsuarioDTO;
import jojolete.jojolete.models.Usuario;
import jojolete.jojolete.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> getAllUsuario() {
        return usuarioRepositorio.findAll();
    }

    public String saveUsuario(Usuario usuario) {
        // Verificar si el correo ya existe en la base de datos
        if (usuarioRepositorio.existsByCorreo(usuario.getCorreo())) {
            return "Correo existente"; // Si ya existe, retorna el mensaje
        }

        try {
            usuarioRepositorio.save(usuario); // Si no existe, guarda el usuario
            return "Usuario guardado exitosamente";
        } catch (Exception e) {
            return "Error al guardar el usuario: " + e.getMessage();
        }
    }

    // Método modificado para buscar tanto por correo como por contraseña
    public UsuarioDTO loginUsuario(String correo, String contrasena) {
        // Busca un usuario que coincida tanto con el correo como con la contraseña
        Usuario usuario = usuarioRepositorio.findByCorreoAndContrasena(correo, contrasena);

        if (usuario != null) {
            // Si el usuario es encontrado con las credenciales correctas
            return new UsuarioDTO(usuario.getNombre(), usuario.getApellido()); // Devuelves el DTO con los datos
        } else {
            // Si no se encuentra el usuario o las credenciales no coinciden
            return null; // Puedes devolver null o lanzar una excepción si prefieres manejar el error de otra forma
        }
    }
}
