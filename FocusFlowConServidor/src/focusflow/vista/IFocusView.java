package focusflow.vista;

import focusflow.models.SesionEstudio;
import focusflow.models.TipoDescanso;
import java.util.List;

public interface IFocusView {

    String pedirObjetivoEstudio();

    String pedirNombreUsuario();

    int pedirNivelEstres();

    boolean confirmarInterrupcion();

    void simularFoto(String momento);

    void mostrarPantallaDescanso(TipoDescanso descanso);

    void mostrarResumen(List<SesionEstudio> historial);

    void mostrarMensaje(String mensaje);

}
