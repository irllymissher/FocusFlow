package focusflow.models;

import com.google.gson.annotations.SerializedName;

public class Usuario {
    
    @SerializedName("objectId")
    private String id;
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
