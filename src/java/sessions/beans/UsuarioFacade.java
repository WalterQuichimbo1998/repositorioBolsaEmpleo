/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import modelo.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario validarUsuario2(String user, String pass) {
        Usuario usuario = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM usuario WHERE BINARY usuario = '" + user + "' AND BINARY clave_cifrada ='" + pass + "';", Usuario.class);
            usuario = (Usuario) q.getSingleResult();
        } catch (Exception e) {
        }
        return usuario;
    }

    public List<Usuario> listaUsuario(Integer n) {
//        Query q = em.createNativeQuery("SELECT usuario,clave,rol,persona.id_persona,persona.nombre,persona.apellido FROM usuario WHERE id_usuario !=" + n + ";", Usuario.class);
        List<Usuario> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM usuario WHERE id_usuario != " + n + ";", Usuario.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
}
