package focusflow.Dao;

import focusflow.Dao.ISesionDAO;
import java.util.ArrayList;
import java.util.List;
import focusflow.Models.SesionEstudio;

public class SesionDAOCollectionsImpl implements ISesionDAO {

    /*
    Como aun no hemos hecho nada relacionado con
    Back4App salvo la teoria entonces nos piden
    simular una bas de datos con ArrayList, parecida
    a la lista de empleados.
    */
    private List<SesionEstudio> baseDatosSimulada;

    public SesionDAOCollectionsImpl() {
        this.baseDatosSimulada = new ArrayList<>();
    }

    /*
    Por cada sesion creada por el Usuario se sebe de
    guardar la sesion dentro de la coleccion (base de
    datos).
    */
    @Override
    public void guardarSesion(SesionEstudio sesion) {
        baseDatosSimulada.add(sesion);
        System.out.println("[DAO] -> Sesión guardada correctamente en Base de datos local.");
    }

    
    @Override
    public List<SesionEstudio> listarSesiones() {
        return baseDatosSimulada;
    }
}