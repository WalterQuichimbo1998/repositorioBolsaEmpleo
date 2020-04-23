package jsf.classes;

import control.AccesoBean;
import controller.PreferenciaLaboral;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.PreferenciaLaboralFacade;

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

@Named("preferenciaLaboralController")
@SessionScoped
public class PreferenciaLaboralController implements Serializable {

    @EJB
    private sessions.beans.PreferenciaLaboralFacade ejbFacade;
    private List<PreferenciaLaboral> items = null;
    private PreferenciaLaboral selected;

    public PreferenciaLaboralController() {
    }

    public PreferenciaLaboral getSelected() {
        return selected;
    }

    public void setSelected(PreferenciaLaboral selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PreferenciaLaboralFacade getFacade() {
        return ejbFacade;
    }

    public PreferenciaLaboral prepareCreate() {
        selected = new PreferenciaLaboral();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setIdHojaVidaEstudiante(AccesoBean.obtenerIdHojaVida());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PreferenciaLaboralCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            items = getFacade().lista();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PreferenciaLaboralUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PreferenciaLaboralDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items = getFacade().lista();
        }
    }

    public List<PreferenciaLaboral> getItems() {
        if (items == null) {
//            items = getFacade().findAll();
            items = getFacade().lista();
        }
        return items;
    }
     public List<PreferenciaLaboral> llenarPreferencia() {
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

    public PreferenciaLaboral getPreferenciaLaboral(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PreferenciaLaboral> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PreferenciaLaboral> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PreferenciaLaboral.class)
    public static class PreferenciaLaboralControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PreferenciaLaboralController controller = (PreferenciaLaboralController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "preferenciaLaboralController");
            return controller.getPreferenciaLaboral(getKey(value));
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
            if (object instanceof PreferenciaLaboral) {
                PreferenciaLaboral o = (PreferenciaLaboral) object;
                return getStringKey(o.getIdPreferenciaLaboral());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PreferenciaLaboral.class.getName()});
                return null;
            }
        }

    }
     public String pasarNumero(int n) {
        String t = "";
       if (n==1) {
            t="Si";
        }else {
            t="No";
        }
        return t;
    }
}
