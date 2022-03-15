package institute.immune.frasemotivacional.Class;

public class Usuario {
    private Integer id_usuario;
    private String nombre;
    private Integer id_estado;
    private Integer inicio_sesion;

    Usuario(Integer id_usuario, String nombre, Integer id_estado, Integer inicio_sesion){
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.id_estado = id_estado;
        this.inicio_sesion = inicio_sesion;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getInicio_sesion() {
        return inicio_sesion;
    }

    public void setInicio_sesion(Integer inicio_sesion) {
        this.inicio_sesion = inicio_sesion;
    }
}
