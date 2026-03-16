/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focusflow;

import focusflow.Dao.ISesionDAO;
import focusflow.Dao.SesionDAOCollectionsImpl;
import focusflow.Presenter.FocusPresenter;
import focusflow.Vista.IFocusView;
import focusflow.vista.ConsolaViewImpl;

/**
 *
 * @author tomif
 */
public class FocusFlowApplication {
    public static void main(String[] args) {
        System.out.println("Arrancando el sistema FocusFlow...\n");
        ISesionDAO dao = new SesionDAOCollectionsImpl();    // Almacen
        IFocusView vista = new ConsolaViewImpl();   // Vista
        FocusPresenter presentador = new FocusPresenter(vista, dao);    // PresentadorIS
        presentador.inicarFocusFlow();
    }
}
