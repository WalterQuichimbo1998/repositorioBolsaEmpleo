/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import control.AccesoBean;
import controller.ExperienciaLaboral;
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
public class ExperienciaLaboralFacade extends AbstractFacade<ExperienciaLaboral> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExperienciaLaboralFacade() {
        super(ExperienciaLaboral.class);
    }
     public List<ExperienciaLaboral> lista(){
        Query q=em.createNativeQuery("SELECT * FROM experiencia_laboral WHERE id_hoja_vida_estudiante ="+AccesoBean.obtenerIdHojaVida().getIdHojaVidaEstudiante()+";", ExperienciaLaboral.class);
        List<ExperienciaLaboral> lista=q.getResultList();
        return lista;
    }
    
}
