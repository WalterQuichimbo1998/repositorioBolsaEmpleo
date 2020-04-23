package jsf.classes;

import control.AccesoBean;
import controller.Idioma;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.IdiomaFacade;

import java.io.Serializable;
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

@Named("idiomaController")
@SessionScoped
public class IdiomaController implements Serializable {

    @EJB
    private sessions.beans.IdiomaFacade ejbFacade;
    private List<Idioma> items = null;
    private Idioma selected;

    public IdiomaController() {
    }

    public Idioma getSelected() {
        return selected;
    }

    public void setSelected(Idioma selected) {
        this.selected = selected;
    }

 
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IdiomaFacade getFacade() {
        return ejbFacade;
    }

    public Idioma prepareCreate() {
        selected = new Idioma();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setIdHojaVidaEstudiante(AccesoBean.obtenerIdHojaVida());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("IdiomaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
             items = getFacade().lista();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("IdiomaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("IdiomaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
             items = getFacade().lista();
           
        }
    }

    public List<Idioma> getItems() {
        if (items == null) {
//            items = getFacade().findAll();
            items = getFacade().lista();
        }
        return items;
    }

    public List<Idioma> llenarIdioma() {
         if (items == null) {
            items = getFacade().lista();
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

    public Idioma getIdioma(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Idioma> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Idioma> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
 

    @FacesConverter(forClass = Idioma.class)
    public static class IdiomaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IdiomaController controller = (IdiomaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "idiomaController");
            return controller.getIdioma(getKey(value));
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
            if (object instanceof Idioma) {
                Idioma o = (Idioma) object;
                return getStringKey(o.getIdIdioma());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Idioma.class.getName()});
                return null;
            }
        }

    }

}
