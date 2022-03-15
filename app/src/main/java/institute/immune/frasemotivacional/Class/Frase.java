package institute.immune.frasemotivacional.Class;

public class Frase {
    private Integer id_frase;
    private String frase;
    private Integer id_estado;
    private String fecha_uso;

    Frase(Integer id_frase, String frase, Integer id_estado, String fecha_uso){
        this.id_frase = id_frase;
        this.frase = frase;
        this.id_estado = id_estado;
        this.fecha_uso = fecha_uso;
    }

    public Integer getId_frase() {
        return id_frase;
    }

    public void setId_frase(Integer id_frase) {
        this.id_frase = id_frase;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public String getFecha_uso() {
        return fecha_uso;
    }

    public void setFecha_uso(String fecha_uso) {
        this.fecha_uso = fecha_uso;
    }
}
