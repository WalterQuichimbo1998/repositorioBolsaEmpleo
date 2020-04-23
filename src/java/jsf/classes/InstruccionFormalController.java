package jsf.classes;

import control.AccesoBean;
import controller.InstruccionFormal;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.InstruccionFormalFacade;

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

@Named("instruccionFormalController")
@SessionScoped
public class InstruccionFormalController implements Serializable {

    @EJB
    private sessions.beans.InstruccionFormalFacade ejbFacade;
    private List<InstruccionFormal> items = null;
    private InstruccionFormal selected;

    public InstruccionFormalController() {
    }

    public InstruccionFormal getSelected() {
        return selected;
    }

    public void setSelected(InstruccionFormal selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InstruccionFormalFacade getFacade() {
        return ejbFacade;
    }

    public InstruccionFormal prepareCreate() {
        selected = new InstruccionFormal();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setIdHojaVidaEstudiante(AccesoBean.obtenerIdHojaVida());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("InstruccionFormalCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            items = getFacade().lista();
        }
    }


    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("InstruccionFormalUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("InstruccionFormalDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items = getFacade().lista();
        }
    }

    public List<InstruccionFormal> getItems() {
        if (items == null) {
//            items = getFacade().findAll();
            items = getFacade().lista();
        }
        return items;
    }
     public List<InstruccionFormal> llenarInstruccion() {
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

    public InstruccionFormal getInstruccionFormal(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<InstruccionFormal> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<InstruccionFormal> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = InstruccionFormal.class)
    public static class InstruccionFormalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InstruccionFormalController controller = (InstruccionFormalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "instruccionFormalController");
            return controller.getInstruccionFormal(getKey(value));
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
            if (object instanceof InstruccionFormal) {
                InstruccionFormal o = (InstruccionFormal) object;
                return getStringKey(o.getIdInstruccion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), InstruccionFormal.class.getName()});
                return null;
            }
        }

    }

}
