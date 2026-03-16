package focusflow.Models;

import focusflow.Models.EstadoSesion;
import java.util.UUID;
import focusflow.Models.TipoDescanso;

public class SesionEstudio {
    
    // --- Atributos ---
    private String id;
    private String objetivo;
    //private String rutaFotoInicio;        
    //private String rutaFotoFin;
    private int tiempoEstudiado;            // En minutos
    private int nivelEstres;                // Del 0 al 10
    private EstadoSesion estadoSesion;
    private TipoDescanso tipoDescanso;

    // --- Constructor ---
    // Solo pedimos el objetivo al crearla. El resto se llena durante el MVP.
    public SesionEstudio(String objetivo) {
        this.id = UUID.randomUUID().toString(); 
        this.objetivo = objetivo;
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public int getTiempoEstudiado() {
        return tiempoEstudiado;
    }

    public int getNivelEstres() {
        return nivelEstres;
    }

    public EstadoSesion getEstado() {
        return estadoSesion;
    }

    public TipoDescanso getTipoDescanso() {
        return tipoDescanso;
    }

    // --- Setters ---
    public void setId(String id) {
        this.id = id;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public void setTiempoEstudiado(int tiempoEstudiado) {
        this.tiempoEstudiado = tiempoEstudiado;
    }

    public void setNivelEstres(int nivelEstres) {
        this.nivelEstres = nivelEstres;
    }

    public void setEstado(EstadoSesion estado) {
        this.estadoSesion = estado;
    }

    public void setTipoDescanso(TipoDescanso tipoDescanso) {
        this.tipoDescanso = tipoDescanso;
    }
    
    
    

    // --- Método toString (Para imprimir bonito en consola) ---
    @Override
    public String toString() {
        return "=== SESIÓN [" + id.substring(0,8) + "...] ===\n" +
               "Objetivo: " + objetivo + "\n" +
               "Tiempo: " + tiempoEstudiado + " min (" + estadoSesion + ")\n" +
               "Estrés: " + nivelEstres + "/10\n" +
               "Descanso asignado: " + tipoDescanso + "\n" +
               "==================================";
    }
}