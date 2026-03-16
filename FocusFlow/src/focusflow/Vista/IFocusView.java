package focusflow.Vista;
import focusflow.Models.SesionEstudio;
import focusflow.Models.TipoDescanso;
import java.util.List;

public interface IFocusView {
    // Mostrar texto simple en la pantalla
    void mostrarMensaje(String mensaje);
    
    String pedirObjetivoDeEstudio();
    
    void simularFoto(String momento);
    
    boolean preguntarSiInterrumpe();
    
    int pedirNivelEstres();
    
    void mostrarPantallaDescanso(TipoDescanso descanso);
    
    void mostrarResumen(List<SesionEstudio> historial);
}