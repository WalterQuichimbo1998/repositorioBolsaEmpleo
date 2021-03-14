package jsf.classes;

import modelo.Mensaje;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.MensajeFacade;

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
import modelo.Postulante;
import org.primefaces.component.datatable.DataTable;

@Named("mensajeController")
@SessionScoped
public class MensajeController implements Serializable {

    @EJB
    private sessions.beans.MensajeFacade ejbFacade;
    private List<Mensaje> items = null;
    private Mensaje selected;
    private Postulante postulante;
    private Boolean v;

    public MensajeController() {
    }

    public Mensaje getSelected() {
        return selected;
    }

    public void setSelected(Mensaje selected) {
        this.selected = selected;
    }

    public Boolean getV() {
        return v;
    }

    public void setV(Boolean v) {
        this.v = v;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
        items = null;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MensajeFacade getFacade() {
        return ejbFacade;
    }

    public Mensaje prepareCreate() {
        selected = new Mensaje();
        initializeEmbeddableKey();
        v = true;
        return selected;
    }

    public void createEsutdiante() {
        if (v) {
            this.selected.setEmisor(1);
            this.selected.setEstado(1);
            this.selected.setFechaRegistro(new Date());
            this.selected.setIdPostulante(postulante);
            this.selected.setIdOferta(postulante.getOfertaLaboralIdOferta());
            persist(PersistAction.CREATE, "Mensaje enviado");
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
                v = false;
            }
        }
    }

    public void createEmpleador() {
        if (v) {
            this.selected.setEmisor(2);
            this.selected.setEstado(1);
            this.selected.setFechaRegistro(new Date());
            this.selected.setIdPostulante(postulante);
            this.selected.setIdOferta(postulante.getOfertaLaboralIdOferta());
            persist(PersistAction.CREATE, "Mensaje enviado");
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
                v = false;
            }
        }
    }

    public void recargar() {
        items=null;
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MensajeUpdated"));
    }

    public void eliminar() {
        this.selected.setEstado(0);
        persist(PersistAction.UPDATE, "Mensaje eliminado");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Mensaje eliminado");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Mensaje> getItems() {
        if (postulante != null) {
            if(items==null){
            items = getFacade().listaMensajes(postulante.getIdPostulante(), postulante.getOfertaLaboralIdOferta().getIdOferta());
        }
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

    public Mensaje getMensaje(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Mensaje> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Mensaje> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Mensaje.class)
    public static class MensajeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MensajeController controller = (MensajeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mensajeController");
            return controller.getMensaje(getKey(value));
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
            if (object instanceof Mensaje) {
                Mensaje o = (Mensaje) object;
                return getStringKey(o.getIdMensaje());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Mensaje.class.getName()});
                return null;
            }
        }

    }

    public String fechaEnvio(Date d) {
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm a dd-MM-yyyy");
        String fecha = formato.format(d);
        return fecha;
    }

    public void resetFilters() {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:list");
        if (dataTable != null) {
            dataTable.resetValue();
        }
    }
}
