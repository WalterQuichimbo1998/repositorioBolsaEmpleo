/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import control.AccesoBean;
import controller.LogroPersonal;
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
public class LogroPersonalFacade extends AbstractFacade<LogroPersonal> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogroPersonalFacade() {
        super(LogroPersonal.class);
    }
    public List<LogroPersonal> lista(){
        Query q=em.createNativeQuery("SELECT * FROM logro_personal WHERE id_hoja_vida_estudiante ="+AccesoBean.obtenerIdHojaVida().getIdHojaVidaEstudiante()+";", LogroPersonal.class);
        List<LogroPersonal> lista=q.getResultList();
        return lista;
    }
}
