/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import modelo.OfertaLaboral;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class OfertaLaboralFacade extends AbstractFacade<OfertaLaboral> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OfertaLaboralFacade() {
        super(OfertaLaboral.class);
    }

    public List<OfertaLaboral> listaOferta(Integer n) {
        List<OfertaLaboral> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM oferta_laboral WHERE id_empresa =" + n + ";", OfertaLaboral.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<OfertaLaboral> listaOferta2(Integer n) {
        List<OfertaLaboral> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM oferta_laboral WHERE id_oferta =" + n + ";", OfertaLaboral.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<OfertaLaboral> comprobarOfertas(Integer id, Date d1, Date d2) {
        List<OfertaLaboral> lista = null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String f1 = formato.format(d1);
            String f2 = formato.format(d2);
            Query q = em.createNativeQuery("SELECT * FROM oferta_laboral WHERE id_empresa = '" + id + "' AND fecha_creacion BETWEEN '" + f1 + "' AND '" + f2 + "';", OfertaLaboral.class);
            f1 = "";
            f2 = "";
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
}
