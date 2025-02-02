package jojolete.jojolete.dto;

public class UsuarioDTO {
    private String nombres;
    private String apellidos;

    // Constructor
    public UsuarioDTO(String nombres, String apellidos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    // Getters y setters
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}