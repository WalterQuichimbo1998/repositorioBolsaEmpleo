package jsf.classes;

import control.AccesoBean;
import modelo.BaulRecomendaciones;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.BaulRecomendacionesFacade;

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

@Named("baulRecomendacionesController")
@SessionScoped
public class BaulRecomendacionesController implements Serializable {

    @EJB
    private sessions.beans.BaulRecomendacionesFacade ejbFacade;
    private List<BaulRecomendaciones> items = null;
    private BaulRecomendaciones selected;
    private String texto;

    public BaulRecomendacionesController() {
    }

    public BaulRecomendaciones getSelected() {
        return selected;
    }

    public void setSelected(BaulRecomendaciones selected) {
        this.selected = selected;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
   
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BaulRecomendacionesFacade getFacade() {
        return ejbFacade;
    }

    public BaulRecomendaciones prepareCreate() {
        selected = new BaulRecomendaciones();
        initializeEmbeddableKey();
        return selected;
    }

    public void crearR() {
        selected = new BaulRecomendaciones();
        initializeEmbeddableKey();
        this.selected.setIdHojaVidaEstudiante(AccesoBean.obtenerIdHojaVida());
        this.selected.setRecomendacion(texto);
        persist(PersistAction.CREATE, "Recomendación guardada");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        texto="";
    }
    public void create() {
        this.selected.setIdHojaVidaEstudiante(AccesoBean.obtenerIdHojaVida());
        persist(PersistAction.CREATE, "Recomendación guardada");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Recomendación actualizada");
    }

    public void destroy() {
        persist(PersistAction.DELETE,  "Recomendación eliminada");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<BaulRecomendaciones> getItems() {
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

    public BaulRecomendaciones getBaulRecomendaciones(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<BaulRecomendaciones> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<BaulRecomendaciones> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = BaulRecomendaciones.class)
    public static class BaulRecomendacionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BaulRecomendacionesController controller = (BaulRecomendacionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "baulRecomendacionesController");
            return controller.getBaulRecomendaciones(getKey(value));
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
            if (object instanceof BaulRecomendaciones) {
                BaulRecomendaciones o = (BaulRecomendaciones) object;
                return getStringKey(o.getIdBaulRecomendaciones());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), BaulRecomendaciones.class.getName()});
                return null;
            }
        }
        

    }
    public Boolean existeTexto(String t) {
        boolean r = true;
        if ("".equals(t)) {
            r = false;
        }
        if (t.isEmpty()) {
            r = false;
        }
        return r;
    }
  public Boolean numReco(){
      boolean num=true;
      if(items.size()>4){
          num=false;
      }
      return num;
  }
}
