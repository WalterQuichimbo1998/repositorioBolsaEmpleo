package jsf.classes;

import control.AccesoBean;
import controller.LogroPersonal;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.LogroPersonalFacade;

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

@Named("logroPersonalController")
@SessionScoped
public class LogroPersonalController implements Serializable {

    @EJB
    private sessions.beans.LogroPersonalFacade ejbFacade;
    private List<LogroPersonal> items = null;
    private LogroPersonal selected;

    public LogroPersonalController() {
    }

    public LogroPersonal getSelected() {
        return selected;
    }

    public void setSelected(LogroPersonal selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LogroPersonalFacade getFacade() {
        return ejbFacade;
    }

    public LogroPersonal prepareCreate() {
        selected = new LogroPersonal();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setIdHojaVidaEstudiante(AccesoBean.obtenerIdHojaVida());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LogroPersonalCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            items = getFacade().lista();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LogroPersonalUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LogroPersonalDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items = getFacade().lista();
        }
    }

    public List<LogroPersonal> getItems() {
        if (items == null) {
//            items = getFacade().findAll();
            items = getFacade().lista();
        }
        return items;
    }
     public List<LogroPersonal> llenarLogroPersonal() {
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

    public LogroPersonal getLogroPersonal(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<LogroPersonal> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<LogroPersonal> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = LogroPersonal.class)
    public static class LogroPersonalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LogroPersonalController controller = (LogroPersonalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "logroPersonalController");
            return controller.getLogroPersonal(getKey(value));
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
            if (object instanceof LogroPersonal) {
                LogroPersonal o = (LogroPersonal) object;
                return getStringKey(o.getIdLogroPersonal());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LogroPersonal.class.getName()});
                return null;
            }
        }

    }

}
