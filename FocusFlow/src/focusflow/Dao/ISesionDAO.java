package focusflow.Dao;

import java.util.List;
import focusflow.Models.SesionEstudio;

public interface ISesionDAO {
    void guardarSesion(SesionEstudio sesion);       // guarda sesion iniciada o interrumpida
    List<SesionEstudio> listarSesiones();           // lista las sesiones de estudio
}