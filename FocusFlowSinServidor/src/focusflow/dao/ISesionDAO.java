package focusflow.dao;

import java.util.List;
import focusflow.models.SesionEstudio;

public interface ISesionDAO {

    // CRUD API
    void guardarSesion(SesionEstudio sesion);             // Create (POST)

    List<SesionEstudio> listarSesiones(String usuarioId); // Read (GET)

    // TODO: POSIBLES IMPLEMENTACIONES CRUD FUTURAS
    // SesionEstudio getSesionEstudioById(String sesionId); 
    // void actualizarSesion(SesionEstudio sesion);
    // void borrarSesion(String id);
}
