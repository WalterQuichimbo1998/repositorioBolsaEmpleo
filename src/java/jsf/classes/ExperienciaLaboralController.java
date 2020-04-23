package jsf.classes;

import control.AccesoBean;
import controller.ExperienciaLaboral;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.ExperienciaLaboralFacade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@Named("experienciaLaboralController")
@SessionScoped
public class ExperienciaLaboralController implements Serializable {

    @EJB
    private sessions.beans.ExperienciaLaboralFacade ejbFacade;
    private List<ExperienciaLaboral> items = null;
    private ExperienciaLaboral selected;

    public ExperienciaLaboralController() {
    }

    public ExperienciaLaboral getSelected() {
        return selected;
    }

    public void setSelected(ExperienciaLaboral selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ExperienciaLaboralFacade getFacade() {
        return ejbFacade;
    }

    public ExperienciaLaboral prepareCreate() {
        selected = new ExperienciaLaboral();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setIdHojaVidaEstudiante(AccesoBean.obtenerIdHojaVida());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ExperienciaLaboralCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            items = getFacade().lista();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ExperienciaLaboralUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ExperienciaLaboralDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items = getFacade().lista();
        }
    }

    public List<ExperienciaLaboral> getItems() {
        if (items == null) {
            items = getFacade().findAll();
            items = getFacade().lista();
        }
        return items;
    }

    public List<ExperienciaLaboral> llenarExperiencia() {
        if (items == null) {
//            items = getFacade().lista();
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

    public ExperienciaLaboral getExperienciaLaboral(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ExperienciaLaboral> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ExperienciaLaboral> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ExperienciaLaboral.class)
    public static class ExperienciaLaboralControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ExperienciaLaboralController controller = (ExperienciaLaboralController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "experienciaLaboralController");
            return controller.getExperienciaLaboral(getKey(value));
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
            if (object instanceof ExperienciaLaboral) {
                ExperienciaLaboral o = (ExperienciaLaboral) object;
                return getStringKey(o.getIdExperiencia());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ExperienciaLaboral.class.getName()});
                return null;
            }
        }

    }

    public String pasarNumero(int n) {
        String t = "";
        if (n == 1) {
            t = "Si";
        } else {
            t = "No";
        }
        return t;
    }

    public String fecha(Date d) {
        String resultado = "";
        if (d != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            resultado = formato.format(d);
        } else {
            resultado = "";
        }

        return resultado;
    }

}
