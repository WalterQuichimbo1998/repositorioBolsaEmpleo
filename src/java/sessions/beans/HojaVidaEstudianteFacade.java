/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import modelo.HojaVidaEstudiante;
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
public class HojaVidaEstudianteFacade extends AbstractFacade<HojaVidaEstudiante> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HojaVidaEstudianteFacade() {
        super(HojaVidaEstudiante.class);
    }

    public List<HojaVidaEstudiante> listaHojaEstudiante(Integer n) {
        List<HojaVidaEstudiante> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM hoja_vida_estudiante WHERE id_persona =" + n + ";", HojaVidaEstudiante.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
    

}
