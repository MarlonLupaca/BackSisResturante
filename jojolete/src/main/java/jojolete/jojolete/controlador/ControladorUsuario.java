package jojolete.jojolete.controlador;

import java.util.List;
import jojolete.jojolete.dto.UsuarioDTO;
import jojolete.jojolete.models.Usuario;
import jojolete.jojolete.models.LoginRequest;  // Importar el modelo LoginRequest
import jojolete.jojolete.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")  // Permite todas las conexiones (ajustar según sea necesario)
public class ControladorUsuario {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    // Endpoint para obtener todos los usuarios
    @GetMapping("/allUsuario")
    public List<Usuario> getAllUsuarios() {
        return usuarioServicio.getAllUsuario();
    }

    // Endpoint para guardar un usuario
    @PostMapping("/postUsuario")
    public String postUsuario(@RequestBody Usuario usuario) {
        return usuarioServicio.saveUsuario(usuario);
    }

    // Nuevo Endpoint para login
    @PostMapping("/login")
    public UsuarioDTO loginUsuario(@RequestBody LoginRequest loginRequest) {
        // Llamamos al servicio para verificar las credenciales
        UsuarioDTO usuarioDTO = usuarioServicio.loginUsuario(loginRequest.getCorreo(), loginRequest.getContrasena());

        if (usuarioDTO != null) {
            // Si las credenciales son correctas, retornamos el objeto con los datos del usuario
            return usuarioDTO;
        } else {
           UsuarioDTO no = new UsuarioDTO("No existe", "No existe");
            return no; // O puedes lanzar una excepción si lo prefieres
        }
    }
}
