/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import control.AccesoBean;
import controller.Idioma;
import controller.ReferenciaPersonal;
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
public class ReferenciaPersonalFacade extends AbstractFacade<ReferenciaPersonal> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReferenciaPersonalFacade() {
        super(ReferenciaPersonal.class);
    }
    public List<ReferenciaPersonal> lista(){
        Query q=em.createNativeQuery("SELECT * FROM referencia_personal WHERE id_hoja_vida_estudiante ="+AccesoBean.obtenerIdHojaVida().getIdHojaVidaEstudiante()+";", ReferenciaPersonal.class);
        List<ReferenciaPersonal> lista=q.getResultList();
        return lista;
    }
    
}
