/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import control.AccesoBean;
import controller.Oficio;
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
public class OficioFacade extends AbstractFacade<Oficio> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OficioFacade() {
        super(Oficio.class);
    }
      public List<Oficio> lista(){
        Query q=em.createNativeQuery("SELECT * FROM oficio WHERE id_hoja_vida_estudiante ="+AccesoBean.obtenerIdHojaVida().getIdHojaVidaEstudiante()+";", Oficio.class);
        List<Oficio> lista=q.getResultList();
        return lista;
    }
}
