/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import control.AccesoBean;
import controller.Idioma;
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
public class IdiomaFacade extends AbstractFacade<Idioma> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IdiomaFacade() {
        super(Idioma.class);
    }
    public List<Idioma> lista(){
        Query q=em.createNativeQuery("SELECT * FROM idioma WHERE id_hoja_vida_estudiante ="+AccesoBean.obtenerIdHojaVida().getIdHojaVidaEstudiante()+";", Idioma.class);
        List<Idioma> lista=q.getResultList();
        return lista;
    }
  
}
