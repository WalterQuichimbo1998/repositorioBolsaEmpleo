package jsf.classes;

import control.AccesoBean;
import controller.Canton;
import controller.Parroquia;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.ParroquiaFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;

@Named("parroquiaController")
@SessionScoped
public class ParroquiaController implements Serializable {

    @EJB
    private sessions.beans.ParroquiaFacade ejbFacade;
    private List<Parroquia> items = null;
    private List<Parroquia> lista = null;   
    private Parroquia selected;
    private Canton selectedC;

    
    public ParroquiaController() {
    }

    public Parroquia getSelected() {
        return selected;
    }

    public void setSelected(Parroquia selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ParroquiaFacade getFacade() {
        return ejbFacade;
    }

      public Canton getSelectedC() {
        return selectedC;
    }

    public void setSelectedC(Canton selectedC) {
        this.selectedC = selectedC;
         lista=null;
    }
   
    public List<Parroquia> getLista() {
        if (lista == null) {
            lista = getFacade().listaParroquia(selectedC.getIdCanton());
        }
        return lista;
    }

    public void setLista(List<Parroquia> lista) {
        this.lista = lista;
    }


    public Parroquia prepareCreate() {
        selected = new Parroquia();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setIdCanton(selectedC); 
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ParroquiaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            lista = null;    // Invalidate list of items to trigger re-query.
            lista = getFacade().listaParroquia(selectedC.getIdCanton());
        }
    }

    public void update() {
        this.selected.setIdCanton(selectedC);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ParroquiaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ParroquiaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
             lista = null;    // Invalidate list of items to trigger re-query.
            lista = getFacade().listaParroquia(selectedC.getIdCanton());
        }
    }

    public List<Parroquia> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Parroquia getParroquia(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Parroquia> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Parroquia> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
//     public void llenarParroquia(AjaxBehaviorEvent event) {
//
//        this.id = AccesoBean.obtenerIdPersona().getIdPersona().getIdCanton().getIdCanton();
////        getListaParroquia();
//       
//    }
//     public List<Parroquia> getListaParroquia() {
////         
//         if (id == null) {
//            this.id = AccesoBean.obtenerIdPersona().getIdPersona().getIdCanton().getIdCanton();
//        }
//         
//        return getFacade().listaParroquia(id); 
//    }
 
    @FacesConverter(forClass = Parroquia.class)
    public static class ParroquiaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ParroquiaController controller = (ParroquiaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "parroquiaController");
            return controller.getParroquia(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Parroquia) {
                Parroquia o = (Parroquia) object;
                return getStringKey(o.getIdParroquia());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Parroquia.class.getName()});
                return null;
            }
        }

    }

}
