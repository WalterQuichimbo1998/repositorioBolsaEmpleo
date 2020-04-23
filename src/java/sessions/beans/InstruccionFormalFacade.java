/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import control.AccesoBean;
import controller.InstruccionFormal;
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
public class InstruccionFormalFacade extends AbstractFacade<InstruccionFormal> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstruccionFormalFacade() {
        super(InstruccionFormal.class);
    }
     public List<InstruccionFormal> lista(){
        Query q=em.createNativeQuery("SELECT * FROM instruccion_formal WHERE id_hoja_vida_estudiante ="+AccesoBean.obtenerIdHojaVida().getIdHojaVidaEstudiante()+";", InstruccionFormal.class);
        List<InstruccionFormal> lista=q.getResultList();
        return lista;
    }
  
}
