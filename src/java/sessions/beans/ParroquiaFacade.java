/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import controller.Canton;
import controller.Parroquia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class ParroquiaFacade extends AbstractFacade<Parroquia> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParroquiaFacade() {
        super(Parroquia.class);
    }
    public List<Parroquia> listaParroquia(Integer id){ 
        Query q=em.createNativeQuery("SELECT * FROM parroquia WHERE id_canton ="+id+";", Parroquia.class);  
        List<Parroquia> lista=q.getResultList();
        return lista;
    }
}
