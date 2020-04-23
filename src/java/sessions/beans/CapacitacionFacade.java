/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import control.AccesoBean;
import controller.Capacitacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class CapacitacionFacade extends AbstractFacade<Capacitacion> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CapacitacionFacade() {
        super(Capacitacion.class);
    }
    public List<Capacitacion> lista(){
        Query q=em.createNativeQuery("SELECT * FROM capacitacion WHERE id_hoja_vida_estudiante ="+AccesoBean.obtenerIdHojaVida().getIdHojaVidaEstudiante()+";", Capacitacion.class);
        List<Capacitacion> lista=q.getResultList();
        return lista;
    }
}
