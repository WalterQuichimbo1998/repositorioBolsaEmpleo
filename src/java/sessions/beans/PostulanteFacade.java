/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import control.AccesoBean;
import modelo.Postulante;
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
public class PostulanteFacade extends AbstractFacade<Postulante> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostulanteFacade() {
        super(Postulante.class);
    }

    public List<Postulante> lista(Integer id) {
        List<Postulante> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM postulante WHERE persona_id_persona =" + id + " ORDER BY id_postulante DESC;", Postulante.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
    public List<Postulante> listaConfirmadas(Integer id) {
        List<Postulante> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM postulante WHERE persona_id_persona =" + id + " AND confirmacion=1 ORDER BY id_postulante DESC;", Postulante.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Postulante> lista_2(Integer id) {
        List<Postulante> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM postulante WHERE persona_id_persona =" + id + ";", Postulante.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Postulante> listaPostulantes(Integer n) {
        List<Postulante> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM postulante WHERE oferta_laboral_id_oferta =" + n + ";", Postulante.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Postulante> listaPostulantes2() {
        List<Postulante> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT id_postulante,oferta_laboral_id_oferta,persona_id_persona,estado_postulante,fecha_postulante,confirmacion,fecha_confirmacion,oferta_laboral.id_oferta,oferta_laboral.cargo_solicitado,empresa.id_empresa,empresa.nombre_comercial,persona.id_persona FROM postulante \n"
                    + "LEFT JOIN oferta_laboral ON oferta_laboral.id_oferta=postulante.oferta_laboral_id_oferta \n"
                    + "LEFT JOIN empresa ON empresa.id_empresa=oferta_laboral.id_empresa \n"
                    + "LEFT JOIN persona ON persona.id_persona=empresa.id_persona \n"
                    + "WHERE persona.id_persona=" + AccesoBean.obtenerIdPersona().getIdPersona().getIdPersona() + " ORDER BY postulante.id_postulante DESC;", Postulante.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Postulante> listaPostulantesEmpresa(Date d1, Date d2) {
        List<Postulante> lista = null;
        try {
            Integer id = AccesoBean.obtenerIdPersona().getIdPersona().getIdPersona();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String f1 = formato.format(d1);
            String f2 = formato.format(d2);
            Query q = em.createNativeQuery("SELECT id_postulante,oferta_laboral_id_oferta,persona_id_persona,estado_postulante,fecha_postulante,oferta_laboral.id_oferta,empresa.id_empresa,persona.id_persona FROM postulante \n"
                    + "LEFT JOIN oferta_laboral ON oferta_laboral.id_oferta=postulante.oferta_laboral_id_oferta \n"
                    + "LEFT JOIN empresa ON empresa.id_empresa=oferta_laboral.id_empresa \n"
                    + "LEFT JOIN persona ON persona.id_persona=empresa.id_persona \n"
                    + "WHERE persona.id_persona=" + id + " AND fecha_postulante BETWEEN '" + f1 + "' AND '" + f2 + "' ORDER BY postulante.fecha_postulante DESC;", Postulante.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Postulante> listaPostulantesOferta(Integer id, Date d1, Date d2) {
        List<Postulante> lista = null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String f1 = formato.format(d1);
            String f2 = formato.format(d2);
            Query q = em.createNativeQuery("SELECT id_postulante,oferta_laboral_id_oferta,persona_id_persona,estado_postulante,fecha_postulante,oferta_laboral.id_oferta,empresa.id_empresa,persona.id_persona FROM postulante \n"
                    + "LEFT JOIN oferta_laboral ON oferta_laboral.id_oferta=postulante.oferta_laboral_id_oferta \n"
                    + "LEFT JOIN empresa ON empresa.id_empresa=oferta_laboral.id_empresa \n"
                    + "LEFT JOIN persona ON persona.id_persona=empresa.id_persona \n"
                    + "WHERE oferta_laboral_id_oferta=" + id + " AND fecha_postulante BETWEEN '" + f1 + "' AND '" + f2 + "';", Postulante.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Postulante> totalPostulaciones(Integer n) {
        List<Postulante> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM postulante WHERE persona_id_persona =" + n + ";", Postulante.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Postulante> buscarPostulantes(Integer n) {
        List<Postulante> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM postulante WHERE oferta_laboral_id_oferta =" + n + ";", Postulante.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Postulante> masPostulaciones() {
        List<Postulante> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT *, count(*) AS Total FROM postulante GROUP BY oferta_laboral_id_oferta HAVING COUNT(*)>=0 ORDER BY Total DESC;", Postulante.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Postulante> comprobarRegistro(Date d1, Date d2) {
        List<Postulante> lista = null;
        try {
            Integer id = AccesoBean.obtenerIdPersona().getIdPersona().getIdPersona();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String f1 = formato.format(d1);
            String f2 = formato.format(d2);
            Query q = em.createNativeQuery("SELECT * FROM postulante WHERE persona_id_persona = " + id + " AND fecha_postulante BETWEEN '" + f1 + "' AND '" + f2 + "';", Postulante.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
}
