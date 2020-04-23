/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import controller.NivelHablado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TOSHIBA
 */

public class NivelHabladoFacade extends AbstractFacade<NivelHablado> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

 @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NivelHabladoFacade() {
        super(NivelHablado.class);
    }
    
}
