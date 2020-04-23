/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import controller.Persona;
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
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    public List<Persona> listaPersona(Integer n) {
        Query q = em.createNativeQuery("SELECT * FROM persona WHERE id_persona =" + n + ";", Persona.class);
        List<Persona> lista = q.getResultList();
        return lista;
    }
    

    public List<Persona> listaEstudiantes() {
        Query q = em.createNativeQuery("SELECT * FROM persona\n"
                + " LEFT JOIN usuario ON usuario.id_persona=persona.id_persona \n"
                + "LEFT JOIN rol ON rol.id_rol=usuario.id_rol \n"
                + "WHERE rol.rol='ESTUDIANTE';", Persona.class);
        List<Persona> lista = q.getResultList();
        return lista;
    }

}
