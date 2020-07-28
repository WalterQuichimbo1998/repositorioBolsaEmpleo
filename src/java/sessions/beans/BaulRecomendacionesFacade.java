/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import control.AccesoBean;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.BaulRecomendaciones;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class BaulRecomendacionesFacade extends AbstractFacade<BaulRecomendaciones> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaulRecomendacionesFacade() {
        super(BaulRecomendaciones.class);
    }
     public List<BaulRecomendaciones> lista(){
        Query q=em.createNativeQuery("SELECT * FROM baul_recomendaciones WHERE id_hoja_vida_estudiante ="+AccesoBean.obtenerIdHojaVida().getIdHojaVidaEstudiante()+";", BaulRecomendaciones.class);
        List<BaulRecomendaciones> lista=q.getResultList();
        return lista;
    }
}
