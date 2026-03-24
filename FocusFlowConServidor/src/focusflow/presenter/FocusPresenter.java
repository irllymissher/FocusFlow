package focusflow.presenter;

import focusflow.dao.ISesionDAO;
import focusflow.dao.IUsuarioDAO;
import focusflow.models.EstadoSesion;
import focusflow.models.SesionEstudio;
import focusflow.models.TipoDescanso;
import focusflow.models.Usuario;
import focusflow.vista.IFocusView;

public class FocusPresenter {

    /*
    El presentador conoce los contratos del
    Modelo y de la Vista, pero nunca las implementaciones directas.
     */
    private IFocusView vista;
    private ISesionDAO daoSesiones;
    private IUsuarioDAO daoUsuarios;

    public FocusPresenter(IFocusView vista, ISesionDAO daoSesiones, IUsuarioDAO daoUsuarios) {
        this.vista = vista;
        this.daoSesiones = daoSesiones;
        this.daoUsuarios = daoUsuarios;
    }

    public void iniciarFocusFlow() {

        String nombreUsuario = vista.pedirNombreUsuario();
        Usuario usuarioEncontrado = daoUsuarios.getUsuarioPorNombre(nombreUsuario);
        String idUsuarioActual;

        if (usuarioEncontrado != null) {
            System.out.println("¡Bienvenido de nuevo, " + nombreUsuario + "!");
            idUsuarioActual = usuarioEncontrado.getId();
        } else {
            System.out.println("Es tu primera vez. ¡Creando perfil para " + nombreUsuario + "...");
            Usuario nuevoUsuario = new Usuario(nombreUsuario);
            idUsuarioActual = daoUsuarios.insertarUsuario(nuevoUsuario);
        }

        String objetivoEscrito = vista.pedirObjetivoEstudio();

        // ID automatico
        SesionEstudio sesionActual = new SesionEstudio(objetivoEscrito);
        sesionActual.setUsuarioId(idUsuarioActual);

        vista.simularFoto("INICIO");
        //sesionActual.setRutaFotoInicio("/local/fotos/foto_inicio.jpg");
 
        boolean haPulsadoParar = vista.confirmarInterrupcion();
        if (haPulsadoParar) {
            sesionActual.setEstadoSesion(EstadoSesion.INTERRUMPIDA);
            sesionActual.setTiempoEstudiado(45);    // Simulación cualquiera
        } else {
            sesionActual.setEstadoSesion(EstadoSesion.COMPLETADA);
            sesionActual.setTiempoEstudiado(90);
        }

        int estresUsuario = vista.pedirNivelEstres();
        
        sesionActual.setNivelEstres(estresUsuario);

        TipoDescanso descansoAsignado = calcularDescanso(estresUsuario);

        String idDeLaNube = "";
        switch (descansoAsignado) {
            case RUNNING:
                idDeLaNube = "LBpJrCtx6z";
                break;
            case FOTOGRAFIA:
                idDeLaNube = "RiLYvRzHrq";
                break;
            case ESTIRAMIENTO:
                idDeLaNube = "kPAl5KFAKg";
                break;
            case SNACK_AGUA:
                idDeLaNube = "KvWrKPdQi1";
                break;
            case RESPIRACION:
                idDeLaNube = "z8SkIzdNeL";
                break;
        }

        sesionActual.setDescansoId(idDeLaNube);

        vista.mostrarPantallaDescanso(descansoAsignado);

        daoSesiones.guardarSesion(sesionActual);

        vista.mostrarResumen(daoSesiones.listarSesiones(idUsuarioActual));
    }

    private TipoDescanso calcularDescanso(int estres) {
        if (estres <= 2) {
            return TipoDescanso.RUNNING;
        } else if (estres <= 4) {
            return TipoDescanso.FOTOGRAFIA;
        } else if (estres <= 6) {
            return TipoDescanso.ESTIRAMIENTO;
        } else if (estres <= 8) {
            return TipoDescanso.SNACK_AGUA;
        } else {
            return TipoDescanso.RESPIRACION;
        }
    }
}
