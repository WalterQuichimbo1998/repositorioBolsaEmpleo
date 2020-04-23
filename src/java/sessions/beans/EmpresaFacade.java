/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.beans;

import control.AccesoBean;
import controller.Empresa;
import controller.Idioma;
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
public class EmpresaFacade extends AbstractFacade<Empresa> {

    @PersistenceContext(unitName = "EmpleoISTLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }
      public List<Empresa> listaEmpresa(){
        Query q=em.createNativeQuery("SELECT * FROM empresa WHERE id_persona ="+AccesoBean.obtenerIdPersona().getIdPersona().getIdPersona()+";", Empresa.class);
        List<Empresa> lista=q.getResultList();
        return lista;
    }
      public List<Empresa> comprobarEmpresas(Integer id,Date d1,Date d2){
          SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
          String f1=formato.format(d1);
          String f2=formato.format(d2);
        Query q=em.createNativeQuery("SELECT * FROM empresa WHERE id_persona = '"+id+"' AND fecha_creacion BETWEEN '"+f1+"' AND '"+f2+"';", Empresa.class);
        List<Empresa> lista=q.getResultList();
        return lista;
    }
      public List<Empresa> comprobarEmpresasAdmin(Date d1,Date d2){
          SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
          String f1=formato.format(d1);
          String f2=formato.format(d2);
        Query q=em.createNativeQuery("SELECT * FROM empresa WHERE fecha_creacion BETWEEN '"+f1+"' AND '"+f2+"';", Empresa.class);
        List<Empresa> lista=q.getResultList();
        return lista;
    }
    
}
