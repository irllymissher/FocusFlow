package focusflow;

import focusflow.dao.ISesionDAO;
import focusflow.dao.IUsuarioDAO;
import focusflow.dao.SesionDAOBack4AppImpl;
import focusflow.dao.UsuarioDAOBack4AppImpl;
import focusflow.presenter.FocusPresenter;
import focusflow.vista.IFocusView;
import focusflow.vista.VistaConsolaImpl;

public class FocusFlowApplication {

    public static void main(String[] args) {
        System.out.println("Arrancando el sistema FocusFlow (Modo servidor)...\n");

        // --- MODO LOCAL ---
        //ISesionDAO daoSesiones = new SesionDAOCollectionsImpl();
        //IUsuarioDAO daoUsuarios = new UsuarioDAOCollectionsImpl();

        // --- MODO SERVIDOR ---
        ISesionDAO daoSesiones = new SesionDAOBack4AppImpl();
        IUsuarioDAO daoUsuarios = new UsuarioDAOBack4AppImpl();
        IFocusView vista = new VistaConsolaImpl();

        // El presentador ni se entera de que le hemos cambiado la base de datos
        FocusPresenter presentador = new FocusPresenter(vista, daoSesiones, daoUsuarios);

        presentador.iniciarFocusFlow();
    }
}
