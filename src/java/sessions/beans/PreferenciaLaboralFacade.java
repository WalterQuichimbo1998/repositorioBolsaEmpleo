/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import control.AccesoBean;
import controller.PreferenciaLaboral;
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
public class PreferenciaLaboralFacade extends AbstractFacade<PreferenciaLaboral> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreferenciaLaboralFacade() {
        super(PreferenciaLaboral.class);
    }
     public List<PreferenciaLaboral> lista(){
        Query q=em.createNativeQuery("SELECT * FROM preferencia_laboral WHERE id_hoja_vida_estudiante ="+AccesoBean.obtenerIdHojaVida().getIdHojaVidaEstudiante()+";", PreferenciaLaboral.class);
        List<PreferenciaLaboral> lista=q.getResultList();
        return lista;
    }
    
}
