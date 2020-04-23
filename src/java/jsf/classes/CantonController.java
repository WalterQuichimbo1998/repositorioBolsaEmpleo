package jsf.classes;

import control.AccesoBean; 
import controller.Canton;
import controller.Provincia;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.CantonFacade;

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
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;

@Named("cantonController")
@SessionScoped
public class CantonController implements Serializable {

    @EJB
    private sessions.beans.CantonFacade ejbFacade;
    private List<Canton> items = null;
    private List<Canton> lista = null;
    private Canton selected;
    private Provincia selectedP;
   

    public CantonController() {
    }

    public Canton getSelected() {
        return selected;
    }

    public void setSelected(Canton selected) {
        this.selected = selected;
    }

    public Provincia getSelectedP() {
        return selectedP;
    }

    public void setSelectedP(Provincia selectedP) {
        this.selectedP = selectedP;
        lista=null;
    }

    public List<Canton> getLista() {
       if (lista == null) {
            lista = getFacade().listaCanton(selectedP.getIdProvincia());
        }
        return lista;
    }

    public void setLista(List<Canton> lista) {
        this.lista = lista;
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CantonFacade getFacade() {
        return ejbFacade;
    }

    public Canton prepareCreate() {
        selected = new Canton();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setIdProvincia(selectedP); 
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CantonCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            lista = null;    // Invalidate list of items to trigger re-query.
            lista = getFacade().listaCanton(selectedP.getIdProvincia());
        }
    }

    public void update() {
        this.selected.setIdProvincia(selectedP);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CantonUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CantonDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            lista = null;    // Invalidate list of items to trigger re-query.
            lista = getFacade().listaCanton(selectedP.getIdProvincia());
        }
    }

    public List<Canton> getItems() {
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

    public Canton getCanton(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Canton> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Canton> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

  


    @FacesConverter(forClass = Canton.class)
    public static class CantonControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CantonController controller = (CantonController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cantonController");
            return controller.getCanton(getKey(value));
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
            if (object instanceof Canton) {
                Canton o = (Canton) object;
                return getStringKey(o.getIdCanton());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Canton.class.getName()});
                return null;
            }
        }

    }

}
