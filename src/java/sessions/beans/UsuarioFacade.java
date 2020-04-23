/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import controller.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
             Query q=em.createNativeQuery("SELECT * FROM usuario WHERE BINARY usuario = '"+user+"' AND BINARY clave_cifrada ='"+pass+"';", Usuario.class);
        usuario=(Usuario) q.getSingleResult();
        } catch (Exception e) {
        }
        return usuario;
    }
  
}
