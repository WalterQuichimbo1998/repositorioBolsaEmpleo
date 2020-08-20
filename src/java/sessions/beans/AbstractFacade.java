/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import modelo.HojaVidaEstudiante;
import modelo.OfertaLaboral;
import modelo.Persona;
import modelo.Postulante;
import modelo.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author WALTER QUICHIMBO
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
    public Usuario validarUsuarioSesion(String user) {
        EntityManager em = getEntityManager();
        Usuario usuario = null;
        try {
            try {
                TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByUser", Usuario.class);
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
       public Usuario existeUsuarioRegistradoAdmin(String user) {
        EntityManager em = getEntityManager();
        Usuario usuario = null;
        try {
            try {
                TypedQuery<Usuario> query = em.createNamedQuery("Usuario.buscarUsuarioAdmin", Usuario.class);
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
       public Usuario existePersonaRegistradoAdmin(Persona p,String r) { 
        EntityManager em = getEntityManager();
        Usuario usuario = null;
        try {
            try {
                TypedQuery<Usuario> query = em.createNamedQuery("Usuario.buscarPersonaAdmin", Usuario.class);
                query.setParameter("persona", p);
                query.setParameter("rol", r);
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
        public Usuario existePersonaRegistradoAdmin2(Persona p,String r,Integer id) { 
        EntityManager em = getEntityManager();
        Usuario usuario = null;
        try {
            try {
                TypedQuery<Usuario> query = em.createNamedQuery("Usuario.buscarPersonaAdmin2", Usuario.class);
                query.setParameter("persona", p);
                query.setParameter("rol", r);
                query.setParameter("id_usuario", id);
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
       public Usuario existeUsuarioRegistrado(Integer id,String user) {
        EntityManager em = getEntityManager();
        Usuario usuario = null;
        try {
            try {
                TypedQuery<Usuario> query = em.createNamedQuery("Usuario.buscarUsuario", Usuario.class);
                query.setParameter("id_usuario", id);
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
      
     
       public Persona existeCorreoRegistrado(String correo) {
        EntityManager em = getEntityManager();
        Persona persona = null;
        try {
            try {
                TypedQuery<Persona> query = em.createNamedQuery("Persona.buscarCorreo", Persona.class);
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
       public Persona existeCorreoRegistrado2(Integer id,String correo) {
        EntityManager em = getEntityManager();
        Persona persona = null;
        try {
            try {
                TypedQuery<Persona> query = em.createNamedQuery("Persona.buscarCorreo2", Persona.class);
                query.setParameter("id_persona", id);
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
