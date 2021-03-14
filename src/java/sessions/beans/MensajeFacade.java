/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Mensaje;

/**
 *
 * @author CyberMás
 */
@Stateless
public class MensajeFacade extends AbstractFacade<Mensaje> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MensajeFacade() {
        super(Mensaje.class);
    }
    public List<Mensaje> listaMensajes(Integer idP,Integer idO) {
        List<Mensaje> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM mensaje WHERE id_postulante ='" + idP + "' AND id_oferta='"+idO+"' ORDER BY fecha_registro DESC, id_mensaje DESC;", Mensaje.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
}
