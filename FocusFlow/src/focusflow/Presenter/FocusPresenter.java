/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focusflow.Presenter;

import focusflow.Dao.ISesionDAO;
import focusflow.Models.EstadoSesion;
import focusflow.Models.SesionEstudio;
import focusflow.Models.TipoDescanso;
import focusflow.Vista.IFocusView;

/**
 *
 * @author tomif
 */
public class FocusPresenter {
    
    /*
    El presentador conoce los contrato del
    Modelo y de la Vista, pero nunnca las
    */
    private IFocusView vista;
    private ISesionDAO dao;
    
    public FocusPresenter(IFocusView vista, ISesionDAO dao) {
        this.vista = vista;
        this.dao = dao;
    }
    
    public void inicarFocusFlow() {
        
        String objetivoEscrito = vista.pedirObjetivoDeEstudio();
        
        // Se crea la sesion de estudio.ID creado automaticamente.
        SesionEstudio sesionActual = new SesionEstudio(objetivoEscrito);
        
        vista.simularFoto("INICIO");
        
        //sesionActual.setRutaFotoInicio("/local/fotos/foto_inicio.jpg");
        
        /*
        Se le pregunta al usuario si desde la Vista
        ha hecho algo para interrumpir el contador
        del estudio (poner S/N)
        */
        boolean haPulsadoParar = vista.preguntarSiInterrumpe();
        if (haPulsadoParar) {
            sesionActual.setEstado(EstadoSesion.INTERRUMPIDA);
            sesionActual.setTiempoEstudiado(45);    // Simulacion cualquiera
        } else {
            sesionActual.setEstado(EstadoSesion.COMPLETADA);
            sesionActual.setTiempoEstudiado(90);
        }
        
        // TODO: nivelEstres no puesto
        TipoDescanso descansoAsignado = calcularDescanso(vista.pedirNivelEstres());
        
        sesionActual.setTipoDescanso(descansoAsignado);
        
        /*
        La Vista muestra que tipo de descanso se le ha 
        asignado al usuario (una de las 5 opciones)
        */
        vista.mostrarPantallaDescanso(descansoAsignado);
        
        dao.guardarSesion(sesionActual);
        
        /*
        La Vista le solicita a DAO que le de
        la lista de sesiones realizadas por
        el usuario
        */
        vista.mostrarResumen(dao.listarSesiones()); 
    }
    
    /*
    El Presentador le pide a la Vista que le de
    el nivel de estres que el usuario ha sufrido
    durante su nivel de estudio. Una vez la recibe
    entonces puede calcular el tipo de descanso. 
    
    En un futuro se puede cambiar para modificar el
    tipo de descanso.
    */
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
