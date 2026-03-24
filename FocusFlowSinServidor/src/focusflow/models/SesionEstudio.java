package focusflow.models;

import java.util.UUID;

public class SesionEstudio {

    // --- Atributos ---
    private String id;
    private String objetivo;
    private int tiempoEstudiado;
    private int nivelEstres;
    private EstadoSesion estadoSesion;
    private TipoDescanso tipoDescanso;
    private String usuarioId;
    private String descansoId;

    public SesionEstudio(String objetivo) {
        this.id = UUID.randomUUID().toString();
        this.objetivo = objetivo;
    }

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

    public TipoDescanso getTipoDescanso() {
        return tipoDescanso;
    }

    public EstadoSesion getEstadoSesion() {
        return estadoSesion;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public String getDescansoId() {
        return descansoId;
    }

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

    public void setDescansoId(String descansoId) {
        this.descansoId = descansoId;
    }

    public void setEstadoSesion(EstadoSesion estadoSesion) {
        this.estadoSesion = estadoSesion;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        String idSeguro = (id == null) ? "SIN-ID" : id.substring(0, Math.min(id.length(), 8));

        // TRADUCTOR: Leemos el ID de la base de datos y le asignamos su nombre real
        String nombreDescansoTexto = "Sin asignar";
        if (descansoId != null) {
            switch (descansoId) {
                case "LBpJrCtx6z":
                    nombreDescansoTexto = "RUNNING";
                    break;
                case "RiLYvRzHrq":
                    nombreDescansoTexto = "FOTOGRAFIA";
                    break;
                case "kPAl5KFAKg":
                    nombreDescansoTexto = "ESTIRAMIENTO";
                    break;
                case "KvWrKPdQi1":
                    nombreDescansoTexto = "SNACK_AGUA";
                    break;
                case "z8SkIzdNeL":
                    nombreDescansoTexto = "RESPIRACION";
                    break;
            }
        }

        return "=== SESIÓN [" + idSeguro + "...] ===\n"
                + "Objetivo: " + objetivo + "\n"
                + "Tiempo: " + tiempoEstudiado + " min (" + estadoSesion + ")\n"
                + "Estrés: " + nivelEstres + "/10\n"
                + "Descanso asignado: " + nombreDescansoTexto + "\n"
                + "==================================";
    }
}
