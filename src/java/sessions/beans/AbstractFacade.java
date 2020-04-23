/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import controller.HojaVidaEstudiante;
import controller.Idioma;
import controller.OfertaLaboral;
import controller.Persona;
import controller.Postulante;
import controller.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author TOSHIBA
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    public Usuario validarUsuario(String user, String pass) {
        EntityManager em = getEntityManager();
        Usuario usuario = null;
        try {
            try {
                TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByUserPassword", Usuario.class);
                query.setParameter("usuario", user);
                query.setParameter("clave", pass);
                try {
                    usuario = query.getSingleResult();
                } catch (NoResultException e) {
                    usuario=null;
                }
            } catch (NullPointerException e) {
                usuario = null;
            }
        } catch (NoResultException e ) {
            usuario = null;
            System.out.println("Error: " + e);
        }
        return usuario;
    }
      public HojaVidaEstudiante buscarIdPersona(Persona id) {
        EntityManager em = getEntityManager();
        HojaVidaEstudiante hojaVidaEstudiante = null;
        try {
            try {
                TypedQuery<HojaVidaEstudiante> query = em.createNamedQuery("HojaVidaEstudiante.findByIdPersona", HojaVidaEstudiante.class);
                query.setParameter("id", id);
                try {
                    hojaVidaEstudiante = query.getSingleResult();
                } catch (NoResultException e) {
                    hojaVidaEstudiante=null;
                }
            } catch (NullPointerException e) {
                hojaVidaEstudiante = null;
            }
        } catch (NoResultException e ) {
            hojaVidaEstudiante = null;
            System.out.println("Error: " + e);
        }
        return hojaVidaEstudiante;
    }

       public Postulante existePostulante(OfertaLaboral idO,Persona idP) {
        EntityManager em = getEntityManager();
        Postulante postulante = null;
        try {
            try {
                TypedQuery<Postulante> query = em.createNamedQuery("Postulante.buscarIdPostulante", Postulante.class);
                query.setParameter("idO", idO);
                query.setParameter("idP", idP);
                try {
                    postulante = query.getSingleResult();
                } catch (NoResultException e) {
                    postulante=null;
                }
            } catch (NullPointerException e) {
                postulante = null;
            }
        } catch (NoResultException e ) {
            postulante = null;
            System.out.println("Error: " + e);
        }
        return postulante;
    }
       public Usuario existeUsuarioRegistrado(String user) {
        EntityManager em = getEntityManager();
        Usuario usuario = null;
        try {
            try {
                TypedQuery<Usuario> query = em.createNamedQuery("Usuario.buscarUsuario", Usuario.class);
                query.setParameter("usuario", user);
                try {
                    usuario = query.getSingleResult();
                } catch (NoResultException e) {
                    usuario=null;
                }
            } catch (NullPointerException e) {
                usuario = null;
            }
        } catch (NoResultException e ) {
            usuario = null;
            System.out.println("Error: " + e);
        }
        return usuario;
    }
       public Persona existePersonaRegistrada(String cedula,String correo) {
        EntityManager em = getEntityManager();
        Persona persona = null;
        try {
            try {
                TypedQuery<Persona> query = em.createNamedQuery("Persona.buscarPersona", Persona.class);
                query.setParameter("cedula", cedula);
                query.setParameter("correo", correo);
                try {
                    persona = query.getSingleResult();
                } catch (NoResultException e) {
                    persona=null;
                }
            } catch (NullPointerException e) {
                persona = null;
            }
        } catch (NoResultException e ) {
            persona = null;
            System.out.println("Error: " + e);
        }
        return persona;
    }
       public Persona buscarCorreo(String cedula,String correo) {
        EntityManager em = getEntityManager();
        Persona persona = null;
        try {
            try {
                TypedQuery<Persona> query = em.createNamedQuery("Persona.findByCorreo", Persona.class);
                query.setParameter("correo", correo);
                query.setParameter("cedula", cedula);
                try {
                    persona = query.getSingleResult();
                } catch (NoResultException e) {
                    persona=null;
                }
            } catch (NullPointerException e) {
                persona = null;
            }
        } catch (NoResultException e ) {
            persona = null;
            System.out.println("Error: " + e);
        }
        return persona;
    }
       public Usuario buscarUser(Persona id) {
        EntityManager em = getEntityManager();
        Usuario usuario = null;
        try {
            try {
                TypedQuery<Usuario> query = em.createNamedQuery("Usuario.buscarUsuarioClave", Usuario.class);
                query.setParameter("id", id);
                try {
                    usuario = query.getSingleResult();
                } catch (NoResultException e) {
                    usuario=null;
                }
            } catch (NullPointerException e) {
                usuario = null;
            }
        } catch (NoResultException e ) {
            usuario = null;
            System.out.println("Error: " + e);
        }
        return usuario;
    }
}
